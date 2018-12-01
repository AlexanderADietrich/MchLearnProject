/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashSet;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

//TODO: STORE WHETHER GENERATOR HAS CHANGED, AND CACHE VALUE

/**
 *
 * @author voice
 */
public class BaseMachine extends JFrame{
    public  String[] smalloperators = new String[]{
            "(digit)",
            "(NothingOne)",
            "(NothingTwo)",
            "(NothingThree)",
            "(NothingFour)",
            "(antidigit)",
            "(antidigit2)",
            "(antidigit3)",
            "(ex1.1)",
            "(T)",
            "(-T)",
            "(100T)",
            "(-100T)",
            "(/100T)",
            "(-/100T)",
            "(:1)",
            "(:-1)",
            "(:10)",
            "(:-10)",
            "(:100)",
            "(:-100)",
            "(:1000)",
            "(:-1000)",     
    };
    public  String[] operators = new String[]{
            "(digit)",
            "(NothingOne)",
            "(NothingTwo)",
            "(NothingThree)",
            "(NothingFour)",
            "(antidigit)",
            "(antidigit2)",
            "(antidigit3)",
            "(ex0.05)",
            "(ex0.1)",
            "(ex0.2)",
            "(ex0.3)",
            "(ex0.4)",
            "(ex0.6)",
            "(ex0.75)",
            "(ex1.33)",
            "(ex1.25)",
            "(ex1.5)",
            "(ex1.75)",
            "(ex2.25)",
            "(ex2.5)",
            "(ex2.75)",
            "(ex16)",
            "(ex32)",
            "(ex4)",
            "(ex3)",
            "(T)",
            "(-T)",
            "(T^3)",
            "(-T^3)",
            "(2^(sqrtT))",
            "(1.3^(sqrt(10000-T))",
            "(100T)",
            "(1000T)",
            "(10000T)",
            "(100000T)",
            "(1000000T)",
            "(-100T)",
            "(-1000T)",
            "(-10000T)",
            "(-100000T)",
            "(-1000000T)",
            "(/100T)",
            "(-/100T)",
            "(sqrt)",
            "(ex2)",
            "(0.5)",
            "(2)",
            "(:1)",
            "(:-1)",
            "(:10)",
            "(:-10)",
            "(:100)",
            "(:-100)",
            "(:1000)",
            "(:-1000)",
            "(:10000)",
            "(:-10000)",
            "(:100000)",
            "(:-100000)",
            "(:1000000)",
            "(:-1000000)",
            "(:10000000)",
            "(:-10000000)",
            "(:100000000)",
            "(:-100000000)",
            "(:1000000000)",
            "(:-1000000000)",
            "(:10000000000)",
            "(:-10000000000)",
        };
    public  String[] conditionals = new String[]{
            "<",
            ">"
        };
    public  Generator[] generators = new Generator[5];
    public  Random r = new Random();
    public  long max = 0; //maximum reward
    public  int maxindex = 0; //current index of max
    public  long currentVal = 0L; //current long
    public  long date;
    public  long winner;
    public  int[] consts;
    public  boolean run = false;
    public  int runs = 0;
    public  boolean disp = false;
    public  int bigflop;
    public  long[] dates = new long[]{
        2410, 
        2010, 
        1710, 
        1310, 
        1010, 
        610
    };
    public  long[] winners = new long[]{
        56534521322L, 
        621654576923L, 
        36469685715L,
        146511433215L,
        2760422387L,
        225327671L
    };
    public  int absmax = 0;
    public  String absmaxstring = "";
    public  HashSet<String> archives = new HashSet<>();
    public  JTextArea textOutputFrame;
    public  String firstPerfect 
            = "2x(10000T) (:-100001) sqrt (:10100000) 2 (:-10) (-100T) (-10000T) "
            + "(:10000000) (ex4) (:989990) (-100000T) (:9) (1000000T) (-T) "
            + "(:-9901001) 2 (:100000) (-T) (:10) (-1000000T) (-100T) "
            + "(:-1000000) (ex2) (T^3) (:-100000) (ex0.9375) (T) (:1000101) "
            + "(T^3) (:-10) (-100000T) (ex4) (1000000T) (-100000T) (:-100) "
            + "(-1000000T) sqrt (:100000) (1000T) 2 (-10000T) (:-9989) (ex2) "
            + "(:1) (ex4) (-/100T) (-100000T) (-T) (ex0.75) (:10000000) "
            + "(-10000T) (:100000) (-1000T) (ex0.1) (:-990) (100000T) (-T) "
            + "(-10000T) (-1000T) (-100000T) (100000T) (ex3) (-100T) 2x(-T^3) "
            + "(-T^3) (100T) (:-1001000) (1000T) (100000T) (-100000T) (ex1.33) "
            + "(/100T) (:10000) (100000T) (100T) (ex1.25) (:-10) (-1000T) "
            + "(:-1000000) (-/100T) (:-1000001) (ex2) (:-100) (ex0.013300000000000001) "
            + "(T^3) (10000T) 2 (ex3.75) (:10000) (100000T) sqrt (:-1000100) (ex2.25) "
            + "(/100T) 2x(100000T) (:-1000000) (ex4) (1000T) (-T^3) (:-999900) (ex3) "
            + "(:100000) sqrt (:-10) (-100000T) (:10) (ex3) (:1000000) (-T) 2 (:9) (100T) "
            + "(ex4) (:999800) (-1000T) (1000T) (100000T) (10000T) (1000000T) (-T) (:9000000) "
            + "(-1000T) (:-2) 2x2 (:10000) (T^3) sqrt (:-999901) (-T) (:100010) (-/100T) (:1100) "
            + "(ex1.25) (:1000) sqrt (:99) (-100000T) (-10000T) (:-10001000) (-1000T) (:-1001010) "
            + "(-100000T) 2 (:9999) (/100T) (10000T) (:-10000000) (10000T) (-100T) (:100) (10000T) (T) "
            + "(10000T) (:10) (10000T) (100T) (ex1.25) (10000T) (:-1000000) (ex3) (-1000000T) (ex0.1) "
            + "(:1000) 2 (-100T) (-1000000T) 2 0.5 (:990) ((genN>523865525)?_(:-10000000)_;_(ex2)) "
            + "(:10) (100000T) (:-30000999) (-1000000T) (100T) (:-9990000) (-100T) (-1000000T) "
            + "(:-2000) (1000000T) (:-2) ((genN<992044306)?_(:-10)_;_(:-10000)) (:-1003) (-1000000T)";
    public BaseMachine(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                setTitle("LOTTERY?");
                setSize(500, 300);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        archives = new HashSet<>();
        generators = new Generator[10];
        for (int i = 0; i < generators.length; i++){
            generators[i] = new Generator(10);
        }
    }
    private  class Generator{
        public String commands = "";
        public int reward = 0;
        public boolean changed = true;
        public Generator(){
            for (int i = 0; i < 6; i++)
                this.mutate();
            commands = commands + " " +  makeSmartTree(6) + " ";
        }
        public Generator(int n){
            for (int i = 0; i < n; i++){
                this.mutate();
            }
            commands = commands + " " +  makeSmartTree(6) + " ";
        }
        public Generator(String s){
            this.commands = s;
        }
        public int ginttemp;
        public void addConditional(){
            commands = commands + "((genN" + conditionals[(int)(Math.random()*conditionals.length)] 
                    + ((long) (Math.random()*Integer.MAX_VALUE))
                    + ")?_" + (operators[(int) (operators.length*Math.random())])
                    + "_;_" + (operators[(int) (operators.length*Math.random())]) + ") ";
        }
        
        
        public void interTree(int n){
            ginttemp = (commands.indexOf(" ", (int) (Math.random()*commands.length())));
            if (ginttemp == -1) ginttemp = 0;
            commands = commands.substring(0, ginttemp) + " " +
                    (makeSmallTree(n)) + " " +
                    commands.substring(ginttemp);
        }
        public void interSmartTree(int n){
            ginttemp = (commands.indexOf(" ", (int) (Math.random()*commands.length())));
            if (ginttemp == -1) ginttemp = 0;
            commands = commands.substring(0, ginttemp) + " " + makeSmartTree(n) + " " + 
                    commands.substring(ginttemp);
        }
        public void shiftTreeR(){
            if (commands.contains("y") && commands.contains("#")) {}
            else return;
            
            
            String[] sarrtemp = commands.split(" ");
            int sent = 0;
            while (true){
                if (sarrtemp[sent].contains("y") && sarrtemp[sent].contains("#") && r.nextBoolean()) break;
                else sent++;
                if (sent >= sarrtemp.length) sent = 0;
            }
            String smartTree = sarrtemp[sent];
            
            smartTree = smartTree.substring(2, smartTree.length()-1);
            String[]    splitter = smartTree.split("y");
            String[]    snumarray = splitter[0].split("#");
            long[]       numarray = new long[snumarray.length];
            
            long shift = (long) (r.nextInt()*123);//Pseudorandom shift
            
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
        }
        public void mutateTreeOperators(){
            if (commands.contains("y") && commands.contains("#")) {}
            else return;
            
            
            String[] sarrtemp = commands.split(" ");
            int sent = 0;
            while (true){
                if (sarrtemp[sent].contains("y") && sarrtemp[sent].contains("#") && r.nextBoolean()) break;
                else sent++;
                if (sent >= sarrtemp.length) sent = 0;
            }
            String smartTree = sarrtemp[sent];
            
            smartTree = smartTree.substring(2, smartTree.length()-1);
            String[]    splitter = smartTree.split("y");
            String[]    resarray = splitter[1].split("#");
            resarray[(int) (Math.random()*resarray.length)] = operators[(int) (operators.length*Math.random())];
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
            
        }
        
        
        public void intermutate(){
            ginttemp = (commands.indexOf(" ", (int) (Math.random()*commands.length())));
            if (ginttemp == -1) ginttemp = 0;
            commands = commands.substring(0, ginttemp) + " " +
                    (operators[(int) (operators.length*Math.random())]) + " " +
                    commands.substring(ginttemp);
            
            
        }
        public String[] arrtemp;
        public void delmutate(){
            arrtemp = commands.split(" ");
            if (arrtemp.length > 0){
                arrtemp[(int)(Math.random()*arrtemp.length)] = " ";
                commands = "";
                for (String s : arrtemp){
                    commands = commands + s + " ";
                }
            }
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
        }
        public void mutate(){
            commands = commands + (operators[(int) (operators.length*Math.random())]) + " ";
        }
        public void smutate(){
            commands = commands + " " + (smalloperators[(int) (smalloperators.length*Math.random())]) + " ";
        }
        public int times;
        public long[] vals = new long[dates.length];
        
        public long getValNew(int pos){
            //If algorithm has been changed reload vals
            if (changed) {
                int sent = 0;
                for (long fldate : dates){
                    vals[sent++] = getVal(fldate);
                }
                changed = false;
            }
            return vals[pos];
        }
        public long getValOld(){
            return getVal(date);
        }
        public long getVal(long date){ 
            Long current = 0x0L;
            
            for (String s : this.commands.split(" ")){
                if (s.contains("y") && s.contains("#")){
                    s = handleSmartTree(current, s);
                }
                if (s.contains("_")){
                    s = handleConditional(s, current);
                }

                if (s.equals("(T)")){
                    current = current + date;
                } else if (s.equals("(2^(sqrtT))")){
                    current = current + (long) Math.pow(2, Math.sqrt(date));
                } else if (s.equals("(1.3^(sqrt(10000-T))")){
                    current = current + (long) Math.pow(1.3, Math.sqrt(10000-date));
                } else if (s.equals("(antidigit)")){
                    current = current / 10;
                } else if (s.equals("(antidigit2)")){
                    current = current / 100;
                } else if (s.equals("(antidigit3)")){
                    current = current / 1000;
                } else if (s.equals("(digit)")){
                    current = (current * 10) + Long.parseLong(""+(date+"").charAt(0));
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
                } else if (s.equals("(/100T)")){
                    current = current + (date/100L);
                } else if (s.equals("(-/100T)")){
                    current = current - (date/100L);
                } else if (s.equals("(sqrt)")){
                    current = (long) Math.sqrt(current);
                } else if (s.equals("(ex2)")){
                    current = current*current;
                } else if (s.equals("(T^3)")){
                    current = current + (date*date*date);
                } else if (s.equals("(-T^3)")){
                    current = current - (date*date*date);
                } else if (s.equals("(0.5)")){
                    current = current/2;
                } else if (s.equals("(2)")) {
                    current = current*2;
                } else if (s.startsWith("(ex")){
                    try {
                        current = (long) Math.pow(current, Double.parseDouble(s.substring(3, s.length()-1)));
                    } catch (Exception ex){
                        //System.out.println(s.substring(3, s.length()-1));
                        //System.out.println(s);
                    }
                /*} else if (s.equals("^0.75")){
                    current = (long) Math.pow(current, 0.75);
                } else if (s.equals("^1.33")){
                    current = (long) Math.pow(current, 1.33);
                } else if (s.equals("^1.25")){
                    current = (long) Math.pow(current, 1.25);
                } else if (s.equals("^0.8")){
                    current = (long) Math.pow(current, 0.8);*/
                } else if (s.startsWith("(:")){
                    try {
                        current = current + Long.parseLong(s.substring(2, s.length()-1));
                    } catch (Exception ex) {
                        //System.out.println("line313 " + s);
                    }
                }
            }
            return current;
        }
        
        
        private void setCommands(String string) {
            this.commands=string;
            changed=true;
        }
    }
    public  String makeSmartTree(int n){
        /*
        n=1
        num1
            
            num1
        
        n=2
        num1<num2<num3
        
            num2
        num1    num3
        
        n=3
        num1<num2<num3<num4<num5<num6<num7
        
                            num4
                    num2            num6
                num1    num3    num5    num7
        
        n=4
        1<2<3<4<5<6<7<8<9<10<11<12<13<14<15
        
                    8
                4       12
              2   6   10  14
             1 3 5 7 9  11  15
        
        general
        n=n
        number of numbers: 2^n -1
        
        
        
        
        
        
        */
        
        if (n == 0) return "";
        int b = (int)(Math.pow(2, n)-1);
        int[] indexarr = new int[b];
        long[] numarray = new long[b];
        int sent = 0;
        for (int i = 1; i <= n; i++){
            int num = (int)(Math.pow(2, i));
            for (int c = 1; c < num; c+=2)
                indexarr[sent++] = (b*c)/num;
        }
        
        long max = 0;
        long current = 0;
        sent = 0;
        current = 0;
        while (true){
            current = current + Math.abs(r.nextInt()*10000);
            if (current > max){
                numarray[sent++] = current;
                max = current;
            }
            if (sent >= numarray.length) break;
        }
        
        String retVal = "(y";
        
        
        sent = 0;
        long[] rearrange = new long[numarray.length];
        for (int inte : indexarr){
            rearrange[inte] = numarray[sent++];
        }
        for (long inte: rearrange){
            retVal = retVal + inte + "#";
        }
        retVal = retVal + "y";
        
        String[] res = new String[(int)(Math.pow(2, n-1))+1];
        for (int c = 0; c < res.length; c++){
            res[c] = operators[(int) (operators.length*Math.random())];
            retVal = retVal + res[c] + "#";
        }
        retVal = retVal + "y" + n + "y)";
        
        
        
        
        
        return retVal;
    }    
    public  String handleSmartTree(long val, String smartTree){
        smartTree = smartTree.substring(2, smartTree.length()-1);
        String[]    splitter = smartTree.split("y");
        
        String[]    sNumarray = splitter[0].split("#");
        long[]      numarray = new long[sNumarray.length];
        for (int i = 0; i < sNumarray.length; i++){
            numarray[i] = Long.parseLong(sNumarray[i]);
        }
        String[]    resarray = splitter[1].split("#");
        
        
        int n = Integer.parseInt(splitter[2]);
        int sent = 1;
        int times = 0;
        long current;
        while (true){
            current = numarray[sent-1];
            times++;
            if (!(times < n)) break;
            if (val < current) sent = sent*2;
            else sent = 1 + (sent*2);
        }
        
        if (val < current){
            return resarray[sent- ((int)(Math.pow(2, n-1)))];
        } else {
            return resarray[sent- ((int)(Math.pow(2, n-1)))+1];
        }        
    }    
    /**
     * Makes a tree of slight changes of n height
     * @param n height
     * @return a tree of slight changes of n height.
     */
    public  String makeSmallTree(int n){
        if (n == 0) return smalloperators[(int) (smalloperators.length*Math.random())];
        else return "((genN" + conditionals[(int)(Math.random()*conditionals.length)] 
                + ((long) ((Math.pow(Math.random(), 16)*Long.MAX_VALUE)))
                + ")?_" + (makeSmallTree(n-1)) 
                + "_;_" + (makeSmallTree(n-1)) + ")";
    }
    /**
     * Makes a tree of n height     
     * @param n height
     * @return a tree of conditionals of n height.
     */
    public  String makeTree(int n){
        if (n == 0) return operators[(int) (operators.length*Math.random())];
        else return "((genN" + conditionals[(int)(Math.random()*conditionals.length)] 
                + ((long) ((Math.pow(Math.random(), 16)*Long.MAX_VALUE)))
                + ")?_" + (makeTree(n-1)) 
                + "_;_" + (makeTree(n-1)) + ")";

    }
    /**
     * Makes a conditional statement.
     * @return a conditional statement.
     */
    public  String makeConditional(){
        return "((genN" + conditionals[(int)(Math.random()*conditionals.length)] 
                + ((long) (Math.random()*Integer.MAX_VALUE))
                + ")?_" + (operators[(int) (operators.length*Math.random())])
                + "_;_" + (operators[(int) (operators.length*Math.random())]) + ") ";
    }
    /**
     * Checks which operator a conditional should execute.
     * @param s
     * @param current
     * @return the operator to execute.
     */
    public  String handleConditional(String s, long current){
        long temp = System.currentTimeMillis();
        while (s.contains("_")){
            if (System.currentTimeMillis()-temp > 3000){
                //System.out.println("Issue; " + s);
                return "";
            }
            if (s.charAt(6)=='<'){
                if (current < Long.parseLong(s.substring(7, s.indexOf(")")))){
                    //Do First Command
                    s = s.substring(s.indexOf("_")+1, 1+getCloserIndex(s.indexOf("_")+1, s));
                } else {
                    //Do Second Command
                    s = s.substring(s.indexOf("_", s.indexOf(";"))+1,
                            1+getCloserIndex(s.indexOf("_", s.indexOf(";"))+1, s));
                }
            } else if (s.charAt(6)=='>'){
                if (current > Long.parseLong(s.substring(7, s.indexOf(")")))){
                    //Do First Command
                    s = s.substring(s.indexOf("_")+1, 1+getCloserIndex(s.indexOf("_")+1, s));
                } else {
                    //Do Second Command
                    s = s.substring(s.indexOf("_", s.indexOf(";"))+1,
                            1+getCloserIndex(s.indexOf("_", s.indexOf(";"))+1, s));
                }
            }
        }
        return s;
        
    }
    /**
    * Gets the index of a closing parentheses.
    * @param b location of opening parentheses
    * @param s
    * @return -1 if not found
    */
    public  int getCloserIndex(int b, String s){
        int opening = 0;
        int closing = 0;
        for (int i = b; i < s.length(); i++){
            //System.out.println(opening + " " + closing + " in method");
            if (s.charAt(i) == '(') opening++;
            if (s.charAt(i) == ')') closing++;
            if (opening == closing) return i;
        }
        return -1;
    }
    /**
     * Combines two generators, with two options for the algorithm.
     * @param g
     * @param g2
     * @param mode true=complex false=simple
     * @return the combined generator
     */
    public  Generator combine(Generator g, Generator g2, boolean mode){
        if (mode){
            String ret = "";
            int sent = 0;
            String[] arr1 = g.commands.split(" ");
            String[] arr2 = g2.commands.split(" ");
            
            for (sent = 0; (sent < arr1.length/4) && (sent < arr2.length/4); sent++){
                ret = ret + arr1[sent] + " ";
                ret = ret + arr2[sent] + " ";
            }
            if (sent < g.commands.length()){
                for (int i = sent; i < arr1.length/4; i++){
                    ret = ret + arr1[i] + " ";
                }
            }
            if (sent < g2.commands.length()){
                for (int i = sent; i < arr2.length/4; i++){
                    ret = ret + arr2[i] + " ";
                }
            }
            return new Generator(ret);
        } else {
            return new Generator(g.commands+g2.commands);
        }
    }
    public  boolean bestRun = false;
    /** Restarts the main loop's data, saving some of it.
     * 
     */
    public  void restart(){
        //System.out.println("\nRESTART\n");
        generators = new Generator[10];
        int sent = 0;
        bestRun = false;
        //Sometimes engage in a 'fight' of the 'fittest'.
        //Rarely 'nuke' if there are more than three items, always >6.
        
        if ((r.nextBoolean() && archives.size() > 3) || archives.size() > 6){
            bestRun = true;
            generators = new Generator[archives.size()+3];
            for (String s : archives){
                if (sent == generators.length) break;
                generators[sent++] = new Generator(s);
            }
            if (Math.random() < 0.5 || archives.size() > 6){
                //System.out.println("EXTINCTION");
                //extinction event leaves the best of the best alive, speeds up
                archives.clear();
            }
        }
        //Fill in with random generators.
        for (int i = sent; i < generators.length; i++){
            generators[i] = new Generator(5);
        }
        generators[0] = supermaximize(generators[0]);
        resetTimeVars();
    }
    public  int getReward(){
        return absmax;
    }
    public  boolean isRunning(){
        return run || runs > 0;
    }
    public  void resetTimeVars(){
        flop = 0;
        bigflop = 0;
    }
    /** Clear non timer variables, kill the GUI
     * 
     */
    public  void kill(){
        maxindex = 0;
        max = 0;
        absmax = 0;
        run = false;
        runs = 0;
        archives.clear();
        for (Generator g : generators){
            g = null;
        }
        if (rt != null){
            
            rt.setVisible(false);
            rt.dispose();
            rt = null;
        }
    }
    public  BaseMachine rt;
    public  void initGUI(){
        rt = new BaseMachine();
        rt.setSize(500, 400);
        rt.setResizable(true);
        textOutputFrame = new JTextArea("3");
        textOutputFrame.setBounds(0, 20, rt.getWidth(), (rt.getHeight()));
        textOutputFrame.setLocation(0, 20);
        textOutputFrame.setPreferredSize(new Dimension(rt.getWidth(), rt.getHeight()));
        textOutputFrame.setEnabled(true);
        
        textOutputFrame.setLineWrap(false);
        textOutputFrame.setEditable(true);
        textOutputFrame.setVisible(true);
        
        JScrollPane textOutput = new JScrollPane(textOutputFrame);
        textOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textOutput.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textOutput.setPreferredSize(new Dimension(rt.getWidth(), rt.getHeight()));
        
        textOutputFrame.setText("");
        Font f = new Font(Font.MONOSPACED, Font.PLAIN, 10);
        rt.setFont(f);
        textOutputFrame.setFont(f);
        
        rt.add(textOutput);
        rt.setVisible(true);
    }
    public  void detReward(Generator gen){
        gen.reward = 1000000;
        int numRight = 0;
        int weightDist = 0;
        int minNumRight = Integer.MAX_VALUE;
        
        
        for (int c = 0; c < winners.length; c++){
            winner = winners[c];
            date = dates[c];

            //numRight = 0;
            //weightDist = 0;
            //int a;
            //int b;
            
            currentVal = gen.getValNew(c);
            /*
            //for how many numbers are identical reward
            //also subtract for having the number be far away
            for (int g = 0; (g < (""+winner).length()) && (g < (currentVal + "").length()); g++){
                a = (currentVal + "").charAt(g);
                b = (winner + "").charAt(g);
                if (a==b){
                    numRight++;
                }
                weightDist += Math.abs(a-b);

            }

            if (numRight < minNumRight){//Reward cohesion
                minNumRight = numRight;
            }
            */
            
            //gen.reward -= 5*weightDist;
            //if the number is in the ballpark reward
            //gen.reward -= 10*Math.abs(((("" + winner).length() - ("" + currentVal).length())));
            //gen.reward -= ((Math.abs(currentVal-winner))/1000000000L);
            //System.out.println((long) (100*Math.log(((Math.abs(currentVal-winner))+1))));
            gen.reward -= (int) (1000*Math.log(((Math.abs(currentVal-winner))+1)));
            
            if (currentVal < 0) gen.reward -= 10000;//discount negative answers 

            //if the number is equal greatly reward
            if (winner == currentVal){
                if (flop == 999) textOutputFrame.append("PERFECT\n");
                //if (flop == 999) //System.out.println("Perfect run" + gen.commands);
                gen.reward+= 10;
            }
        }
        //gen.reward += minNumRight*2000;
    }
    public  Generator supermaximize(Generator g){
        Generator temp = g;
        Generator temp2;
        detReward(temp);
        String tester = g.commands;
        while (true){
            //System.out.println("(" + temp.reward + ")" + temp.commands);
            temp = maxmutate(g);
            detReward(temp);
            //System.out.println("(" + temp.reward + ")" + temp.commands);
            temp2 = maxdelete(g);
            detReward(temp2);
            if (temp2.reward >= temp.reward){
                temp = temp2;
            }
            
            //System.out.println("(" + temp.reward + ")" + temp.commands);
            
            if (tester.equals(temp.commands)){
                return temp;
            } else {
                tester = temp.commands;
            }
        }
    }
    
    public  Generator maxdelete(Generator g){
        String temp = g.commands;
        while (true){
            //System.out.println(g.commands.substring(Math.max(0, g.commands.length()-100)));
            g = exhdelete(g);
            if (temp.equals(g.commands)){
                return g;
            }
            temp = g.commands;
        }
    }
    public  Generator exhdelete(Generator g){
        boolean disp = false;
        
        
        
        Generator maxgenerator = new Generator(g.commands);
        detReward(maxgenerator);
        
        
        Generator g2 = new Generator(g.commands);
        detReward(g2);
        
        String[] sarrtemp = g.commands.split(" ");
        String stemp = "";
        
        for (int i = 0; i < sarrtemp.length; i++){
            sarrtemp = g.commands.split(" ");
            sarrtemp[i] = " ";
            stemp = "";
            for (String s : sarrtemp){
                stemp = stemp + s + " ";
            }
            g2 = new Generator(stemp);
            detReward(g2);
            
            if (g2.reward > maxgenerator.reward){

                maxgenerator = g2;
                detReward(maxgenerator);
            }
        }
        

        return maxgenerator;
    }
    
    
    public  Generator maxmutate(Generator g){
        String temp = g.commands;
        while (true){
            //System.out.println(g.commands.substring(Math.max(0, g.commands.length()-100)));
            g = exhmutate(g);
            if (temp.equals(g.commands)){
                return g;
            }
            temp = g.commands;
        }
    }
    
    public  Generator exhmutate(Generator g){
        detReward(g);
        int internalmax = g.reward;
        
        Generator maxgenerator = new Generator(g.commands);
        detReward(maxgenerator);
        
        Generator g2 = new Generator(g.commands);
        detReward(g2);
        
        for (String s : operators){
            g2 = new Generator(g.commands + " " + s + " ");
            detReward(g2);
            if (g2.reward > internalmax){
                //System.out.println("Improved by " + (g2.reward-internalmax));
                internalmax = g2.reward;
                maxgenerator = g2;
            }
        }
        //System.out.println("Total Improvement: " + (maxgenerator.reward-g.reward));
        return maxgenerator;
    }
    public  boolean flag = false;
    public  void midrestart(){
        //System.out.println("COMPACTOR");
        flag = true;
        runs = 0;
        run = false;
        restart();
    }
    public  int flop;
    public  void go(){
        flop = 0;
        int weightEarly = 0;
        int numRight = 0;
        int inttemp = 0;
        String temp = "";
        String outemp = "";
        String temp2 = "";
        String[] sarrtemp;
        /*String s2 = makeSmartTree(4);
        //System.out.println(handleSmartTree(1000000L, s2));
        //System.out.println(handleSmartTree(10000000L, s2));
        //System.out.println(handleSmartTree(100000000L, s2));
        //System.out.println(handleSmartTree(1000000000L, s2));
        //System.out.println(handleSmartTree(10000000000L, s2));
        //System.out.println(handleSmartTree(100000000000L, s2));
        //System.out.println(handleSmartTree(1000000000000L, s2));
        //System.out.println(handleSmartTree(10000000000000L, s2));
        //System.out.println(handleSmartTree(100000000000000L, s2));
        //System.out.println(handleSmartTree(1000000000000000L, s2));
                                           
        //System.out.println(makeTree(3));
        System.exit(0);*/
        
        while (run || runs > 0){
            if (runs > 0) runs--;
            if (bigflop >= 9999){
                //System.out.println("?: " + max +" "+ absmax);
                //System.out.println(bigflop);
                archives.add(generators[maxindex].commands);
                flop = 0;
                bigflop = 0;
                weightEarly = 0;
                numRight = 0;
                inttemp = 0;
                maxindex = 0;
                temp = "";
                outemp = "";
                temp2 = "";
                sarrtemp = new String[0];
                restart();
            } else {
                bigflop++;
            }
            
            if (max < absmax/2 && bigflop > 1999){
                for (int i = 0; i < 5; i++){//Try and prevent the death
                    generators[maxindex] = maxmutate(generators[maxindex]);
                    detReward(generators[maxindex]);
                    generators[maxindex] = maxdelete(generators[maxindex]);
                    detReward(generators[maxindex]);
                }
                //System.out.println("Here");
                if (generators[maxindex].reward < absmax/2){
                    //System.out.println("HERE");
                    midrestart();
                    break;
                } else {
                    //System.out.println("SAVED");
                }
            }
            
            
            max = 0;
            maxindex = 0;
            for (int i = 0; i < generators.length; i++){
                //Increase Efficiency
                sarrtemp = generators[i].commands.split(" ");
                for (int g = 1; g < sarrtemp.length; g++){
                    if (sarrtemp[g] != null && !sarrtemp[g].equals("")){
                        if (sarrtemp[g].startsWith("(ex") && sarrtemp[g-1].startsWith("(ex")){
                            /*generators[i].commands = 
                                    generators[i].commands.replace(sarrtemp[g] + " " + sarrtemp[g-1],
                                    "(ex" + 
                                    (Double.parseDouble(sarrtemp[g].substring(3, sarrtemp[g].length()-1))
                                    *Double.parseDouble(sarrtemp[g-1].substring(3, sarrtemp[g-1].length()-1))) + ")");*/
                            generators[i].setCommands(
                                    generators[i].commands.replace(sarrtemp[g-1] + " " + sarrtemp[g],
                                    "(ex" + 
                                    (Double.parseDouble(sarrtemp[g].substring(3, sarrtemp[g].length()-1))
                                    *Double.parseDouble(sarrtemp[g-1].substring(3, sarrtemp[g-1].length()-1))) + ")")
                            );
                        }
                        else if (sarrtemp[g].startsWith("(:") && sarrtemp[g-1].startsWith("(:")){
                            /*generators[i].commands = generators[i].commands.replace(sarrtemp[g] + " " + sarrtemp[g-1], "(:" + (Integer.parseInt(sarrtemp[g].substring(2, sarrtemp[g].length()-1))+Integer.parseInt(sarrtemp[g-1].substring(2, sarrtemp[g-1].length()-1))) + ") ");*/
                            generators[i].setCommands(
                                    generators[i].commands.replace(sarrtemp[g-1] + " " + sarrtemp[g], "(:" + (Long.parseLong(sarrtemp[g].substring(2, sarrtemp[g].length()-1))+Long.parseLong(sarrtemp[g-1].substring(2, sarrtemp[g-1].length()-1))) + ") ")
                            );
                        }
                    }
                }
                
                //Increase Efficiency II
                sarrtemp = generators[i].commands.split(" ");
                if (sarrtemp.length > 0){
                    generators[i].setCommands("");
                    int r;
                    for (r = 1; r < sarrtemp.length; r++){
                        if (
                            (sarrtemp[r-1].equals("(-100T)") && sarrtemp[r].equals("(100T)"))
                            || (sarrtemp[r-1].equals("(100T)") && sarrtemp[r].equals("(-100T)"))
                            || (sarrtemp[r-1].equals("(-100T)") && sarrtemp[r].equals("(100T)"))
                            || (sarrtemp[r-1].equals("(-T)") && sarrtemp[r].equals("(T)"))
                            || (sarrtemp[r-1].equals("(T)") && sarrtemp[r].equals("(-T)"))
                            || (sarrtemp[r-1].equals("(-/100T)") && sarrtemp[r].equals("(/100T)"))
                            || (sarrtemp[r-1].equals("(/100T)") && sarrtemp[r].equals("(-/100T)"))
                            || (sarrtemp[r-1].equals("(-T^3)") && sarrtemp[r].equals("(T^3)"))
                            || (sarrtemp[r-1].equals("(T^3)") && sarrtemp[r].equals("(-T^3)"))
                        ){
                            sarrtemp[r-1] = "";
                            sarrtemp[r] = "";
                        }
                        generators[i].setCommands(generators[i].commands + sarrtemp[r-1] + " ");
                    }
                    generators[i].setCommands(generators[i].commands + sarrtemp[r-1] + " ");

                    generators[i].setCommands(generators[i].commands.replaceAll("  ", " "));
                    generators[i].setCommands(generators[i].commands.replaceAll("(exInfinity) ", "  "));
                }
                
                
                if (flop == 999 && disp) 
                    //System.out.println(generators[i].commands);
                
                
                //Reward Section
                generators[i].reward = 0;
                detReward(generators[i]);
                
                
                
                
                
                if (generators[i].reward > max){
                    max = generators[i].reward;
                    maxindex = i;
                }
                if (generators[i].reward > absmax){
                    absmax = generators[i].reward;
                    absmaxstring = generators[i].commands;
                }
                
            }        
            
            outemp = "";
            //if (flop == 99) //System.out.println();
            if (flop >= 999 && disp){
                for (int i = 0; i < winners.length; i++){
                    //System.out.println("WINNER:\t" + winners[i] + "\tDATE: " + dates[i]);
                    outemp = outemp + "WINNER:\t" + winners[i] + "\tDATE: " + dates[i] + "\n";
                    winner = winners[i];
                    date = dates[i];
                    currentVal = generators[maxindex].getValOld();
                    //System.out.println("GUESS:\t" + currentVal + "\n");
                    outemp = outemp + "GUESS:\t" + currentVal + "\n";
                }
                //System.out.println("Dist: " + (Math.abs(("" + winner).length() - ("" + currentVal).length())));
                outemp = outemp + "Dist: " + (Math.abs(("" + winner).length() - ("" + currentVal).length())) + "\n";
                //System.out.println("Max: " + "\t\t" +currentVal +" "+ generators[maxindex].commands);
                outemp = outemp + "Max: " + "\t\t" +currentVal +" "+ generators[maxindex].commands + "\n";
                //System.out.println("Max Reward: " + max);
                //System.out.println("Absolute Max Reward: " + absmax);
                
                
                if (rt != null){                    
                    textOutputFrame.append(outemp);   
                    textOutputFrame.append("ABSMAXS: \t" + absmaxstring + "\n");
                    textOutputFrame.append("ABSMAX: " + absmax + "\n");
                    inttemp = 0;
                    for (String s : textOutputFrame.getText().split("\n")){
                        if (s.length() > inttemp) inttemp = s.length();
                    }
                    textOutputFrame.setPreferredSize(new Dimension(inttemp*40, 15*(textOutputFrame.getText().length()-textOutputFrame.getText().replaceAll("\n", "").length())));
                }
                
                flop = 0;
                
            } else {
                flop++;
            }
            //Mutate based on rewards
            for (int i = 0; i < generators.length; i++){
                if (generators[i].reward < max/16){
                    if (r.nextBoolean() && r.nextBoolean() && r.nextBoolean() && r.nextBoolean() && r.nextBoolean() && r.nextBoolean() && r.nextBoolean() && r.nextBoolean() && r.nextBoolean())
                        generators[i] = new Generator(generators[maxindex].commands);
                    else
                        generators[i].delmutate(4);
                }
                if (i != maxindex){
                    if (generators[i].commands.equals(generators[maxindex].commands)){
                        //Increase mutation rate of clones when original is very strong
                        for (int r = 0; r < Math.max(1, absmax/1000); r++){
                            if (Math.random() < consts[0]/10){
                                generators[i].shiftTreeR();
                            }
                            if (Math.random() < consts[0]/10){
                                generators[i].mutateTreeOperators();
                            }
                            if (Math.random() < consts[0]/100){
                                generators[i] = maxmutate(generators[i]);
                            } 
                            if (Math.random() < consts[0]/100){
                                generators[i] = maxdelete(generators[i]);
                            } 
                            if (Math.random() < consts[0]/100){
                                generators[i] = supermaximize(generators[i]);
                            } 
                            if (Math.random() < consts[0]/10){
                                generators[i].smutate();
                            }
                            if (Math.random() < consts[0]/10){
                                generators[i].setCommands(generators[i].commands + " " + makeSmartTree((int) (Math.random()*10)) + " ");
                            }
                            if (Math.random() < consts[0]/10){
                                generators[i].interSmartTree((int) (Math.random()*6));
                            }
                        }
                    } else {
                        if (Math.random() < 0.1){
                            generators[i] = new Generator(10 + generators[maxindex].commands.split(" ").length /2);
                        }
                        if (Math.random() < 0.01){
                            generators[i] = maxmutate(generators[i]);
                        } 
                        if (Math.random() < 0.01){
                            generators[i] = maxdelete(generators[i]);
                        } 
                        if (Math.random() < consts[1]/10){
                            generators[i].setCommands(generators[i].commands + " " + makeSmartTree((int) (Math.random()*6)) + " ");
                        }
                        if (Math.random() < consts[2]/10){
                            generators[i].intermutate();
                        }
                        if (Math.random() < consts[3]/10){
                            generators[i].delmutate(consts[3]);
                        }
                        if (Math.random() < consts[4]/10){
                            for (int ii = 0; ii < consts[5]; ii++)
                                generators[i].mutate();
                        }
                    }
                }
            }
        }
    }              
}
