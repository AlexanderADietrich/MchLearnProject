/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

/**
 *
 * @author voice
 */
public class Generator {
        public BaseMachineII bm2;
        public String commands;
        public long[] vals;
        public String[] arrtemp;
        public int reward;
        public boolean changed;
        public int ginttemp;
        public Generator(BaseMachineII bm2){
            this.bm2=bm2;
            vals = new long[bm2.dates.length];
            commands = "";
            changed = true;
            reward = 0;
            
            
            for (int i = 0; i < 6; i++)
                this.mutate();
            commands = commands + " " +  bm2.makeSmartTree(6) + " ";
        }
        public Generator(BaseMachineII bm2, int n){
            this.bm2=bm2;
            vals = new long[bm2.dates.length];
            commands = "";
            changed = true;
            reward = 0;
            
            for (int i = 0; i < n; i++){
                this.mutate();
            }
            commands = commands + " " +  bm2.makeSmartTree(6) + " ";
        }
        public Generator(BaseMachineII bm2, String s){
            this.bm2=bm2;
            vals = new long[bm2.dates.length];
            changed = true;
            reward = 0;
            
            this.commands = s;
            
        }
        public void addTree(int n){
            commands = commands + " " + bm2.makeSmartTree(n) + " ";
            changed = true;
        }
        public void interSmartTree(int n){
            ginttemp = (commands.indexOf(" ", (int) (Math.random()*commands.length())));
            if (ginttemp == -1) ginttemp = 0;
            commands = commands.substring(0, ginttemp) + " " + bm2.makeSmartTree(n) + " " + 
                    commands.substring(ginttemp);
            changed = true;
        }
        public void shiftTree(){
            if (commands.contains("y") && commands.contains("#")) {}
            else return;
            
            
            String[] sarrtemp = commands.split(" ");
            int sent = 0;
            while (true){
                if (sarrtemp[sent].contains("y") && sarrtemp[sent].contains("#") && bm2.r.nextBoolean()) break;
                else sent++;
                if (sent >= sarrtemp.length) sent = 0;
            }
            String smartTree = sarrtemp[sent];
            
            smartTree = smartTree.substring(2, smartTree.length()-1);
            String[]    splitter = smartTree.split("y");
            String[]    snumarray = splitter[0].split("#");
            long[]       numarray = new long[snumarray.length];
            
            long shift = (long) (bm2.r.nextInt()*123);//Pseudorandom shift
            
            String temp = "(y";
            for (int b = 0; b < snumarray.length; b++){
                temp = temp + (Long.parseLong(snumarray[b])+shift) + "#";
            }
            temp = temp + "y" + splitter[1] +"y" + splitter[2] +"y)";
            
            //System.out.println("test\t" + sarrtemp[sent]);
            sarrtemp[sent] = temp;
            //System.out.println("test\t" + sarrtemp[sent]);
            //System.out.println();
            //System.out.println(commands);
            commands = "";
            for (String st : sarrtemp){
                commands = commands + st + " ";
            }
            //System.out.println(commands);
            //System.out.println();
            changed = true;
        }
        public void mutateTreeOperators(){
            if (commands.contains("y") && commands.contains("#")) {}
            else return;
            
            
            String[] sarrtemp = commands.split(" ");
            int sent = 0;
            while (true){
                if (sarrtemp[sent].contains("y") && sarrtemp[sent].contains("#") && bm2.r.nextBoolean()) break;
                else sent++;
                if (sent >= sarrtemp.length) sent = 0;
            }
            String smartTree = sarrtemp[sent];
            
            smartTree = smartTree.substring(2, smartTree.length()-1);
            String[]    splitter = smartTree.split("y");
            String[]    resarray = splitter[1].split("#");
            resarray[(int) (Math.random()*resarray.length)] = bm2.operators[(int) (bm2.operators.length*Math.random())];
            String temp = "";
            temp = temp + "(y" + splitter[0] + "y";
            for (String st : resarray){
                temp = temp + st + "#";
            }
            temp = temp + "y" + splitter[2] + "y)";
            
            sarrtemp[sent] = temp;
            commands = "";
            for (String st : sarrtemp){
                commands = commands + st + " ";
            }
            
            changed = true;
        }
        public void intermutate(){
            ginttemp = (commands.indexOf(" ", (int) (Math.random()*commands.length())));
            if (ginttemp == -1) ginttemp = 0;
            commands = commands.substring(0, ginttemp) + " " +
                    (bm2.operators[(int) (bm2.operators.length*Math.random())]) + " " +
                    commands.substring(ginttemp);
            
            changed = true;
        }
        public void delmutate(){
            arrtemp = commands.split(" ");
            if (arrtemp.length > 0){
                arrtemp[(int)(Math.random()*arrtemp.length)] = " ";
                commands = "";
                for (String s : arrtemp){
                    commands = commands + s + " ";
                }
            }
            changed = true;
        }
        /** Same purpose as delmutate but more efficient for multiple deletions.
         * 
         * @param n 
         */
        public void delmutate(int n){
            arrtemp = commands.split(" ");
            if (arrtemp.length > 0){
                for (int i = 0; i < n; i++)
                    arrtemp[(int)(Math.random()*arrtemp.length)] = " ";
                commands = "";
                for (String s : arrtemp){
                    commands = commands + s + " ";
                }
            }
            changed = true;
        }
        public void mutate(){
            commands = commands + (bm2.operators[(int) (bm2.operators.length*Math.random())]) + " ";
            changed = true;
        }
        
        public void update(){
            for (int i = 0; i < bm2.dates.length; i++){
                vals[i] = getValDateMonth(bm2.dates[i], bm2.months[i]);
            }
            changed = false;
        }
        public long getVal(int pos){
            //If algorithm has been changed reload vals
            if (changed) {
                update();
            }
            return vals[pos];
        }
        
        public void reloadCommands(){
            String[] arrtemp = commands.split(" ");
            commands = "";
            for (String s : arrtemp){
                if (!(s.equals("")) && !(s.contains(" "))) commands = commands + s + " ";
            }
        }
        
        public long getValDateMonth(long date, long month){ 
           
            Long current = 0L;
            //System.out.println("\n" + commands);
            String[] arrtemp = this.commands.split(" ");
            for (String s : arrtemp){
                if (s.equals("") || s.equals(" ")) continue;//Ignore null values
                if (s.contains("y") && s.contains("#")){
                    s = bm2.handleSmartTree(current, s);
                }
                
                if (s.equals("(M)")){
                    current = current + month;
                }
                else if (s.equals("(T)")){
                    current = current + date;
                    
                } else if (s.equals("(2^M)")){
                    current = current + (long) Math.pow(2, month);
                } else if (s.equals("(2^T)")){
                    current = current + (long) Math.pow(2, date);
                } else if (s.equals("(1.3^(100-T)")){
                    current = current + (long) Math.pow(1.3, 100-date);
                } else if (s.equals("(1.3^(100-M)")){
                    current = current + (long) Math.pow(1.3, 100-month);
                } else if (s.equals("(antidigit)")){
                    current = current / 10L;
                } else if (s.equals("(antidigit2)")){
                    current = current / 100L;
                } else if (s.equals("(antidigit3)")){
                    current = current / 1000L;
                } else if (s.equals("(digit)")){
                    current = (current * 10L) + Long.parseLong(""+(date+"").charAt(0));
                } else if (s.equals("(-T)")){
                    current = current - date;
                } else if (s.equals("(100T)")){
                    current = current + (date*100L);
                } else if (s.equals("(-100T)")){
                    current = current - (date*100L);
                } else if (s.equals("(1000T)")){
                    current = current + (date*1000L);
                } else if (s.equals("(-1000T)")){
                    current = current - (date*1000L);
                } else if (s.equals("(10000T)")){
                    current = current + (date*10000L);
                } else if (s.equals("(-10000T)")){
                    current = current - (date*10000L);
                } else if (s.equals("(100000T)")){
                    current = current + (date*100000L);
                } else if (s.equals("(-100000T)")){
                    current = current - (date*100000L);
                } else if (s.equals("(1000000T)")){
                    current = current + (date*1000000L);
                } else if (s.equals("(-1000000T)")){
                    current = current - (date*1000000L);
                } else if (s.equals("(/100T)")){
                    current = current + (date/100L);
                } else if (s.equals("(-/100T)")){
                    current = current - (date/100L);
                } else if (s.equals("(-M)")){
                    current = current - month;
                } else if (s.equals("(100M)")){
                    current = current + (month*100L);
                } else if (s.equals("(-100M)")){
                    current = current - (month*100L);
                } else if (s.equals("(1000M)")){
                    current = current + (month*1000L);
                } else if (s.equals("(-1000M)")){
                    current = current - (month*1000L);
                } else if (s.equals("(10000M)")){
                    current = current + (month*10000L);
                } else if (s.equals("(-10000M)")){
                    current = current - (month*10000L);
                } else if (s.equals("(100000M)")){
                    current = current + (month*100000L);
                } else if (s.equals("(-100000M)")){
                    current = current - (month*100000L);
                } else if (s.equals("(1000000M)")){
                    current = current + (month*1000000L);
                } else if (s.equals("(-1000000M)")){
                    current = current - (month*1000000L);
                } else if (s.equals("(/100M)")){
                    current = current + (month/100L);
                } else if (s.equals("(-/100M)")){
                    current = current - (month/100L);
                } else if (s.equals("(sqrt)")){
                    current = (long) Math.sqrt(current);
                } else if (s.equals("(ex2)")){
                    current = current*current;
                } else if (s.equals("(T^3)")){
                    current = current + (date*date*date);
                } else if (s.equals("(-T^3)")){
                    current = current - (date*date*date);
                } else if (s.equals("(M^3)")){
                    current = current + (month*month*month);
                } else if (s.equals("(-M^3)")){
                    current = current - (month*month*month);
                } else if (s.equals("(0.5)")){
                    current = current/2L;
                } else if (s.equals("(2)")) {
                    current = current*2L;
                } else if (s.startsWith("(ex")){
                    try {
                        //System.out.println("\t\t\t" + current);
                        current = (long) Math.pow(current, Double.parseDouble(s.substring(3, s.length()-1)));
                        //System.out.println("\t\t\t" + current);
                    } catch (Exception ex){
                        //System.out.println(s.substring(3, s.length()-1));
                        //System.out.println(s);
                    }
                } else if (s.startsWith("(:")){
                    try {
                        current = current + Long.parseLong(s.substring(2, s.length()-1));
                    } catch (Exception ex) {
                        //System.out.println("line249 " + s);
                    }
                }
                //System.out.println("\t\t" + current); 
            }
            return current;
        }
        private void setCommands(String string) {
            this.commands=string;
            changed=true;
        }
}
