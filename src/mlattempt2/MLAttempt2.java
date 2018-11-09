/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Random;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author voice
 */
public class MLAttempt2 extends JFrame{
    public static String[] smalloperators = new String[]{
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
    public static String[] operators = new String[]{
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
            "sqrt",
            "(ex2)",
            "0.5",
            "2",
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
    public static String[] conditionals = new String[]{
            "<",
            ">"
        };
    public static Generator[] generators = new Generator[10];
    public static Random r = new Random();
    public static int max = 0; //maximum reward
    public static int maxindex = 0; //current index of max
    public static long currentVal = 0L; //current long
    public static long date;
    public static long winner;
    public static long[] dates = new long[]{
        2410, 
        2010, 
        1710, 
        1310, 
        1010, 
        610
    };
    public static long[] winners = new long[]{
        56534521322L, 
        621654576923L, 
        36469685715L,
        146511433215L,
        2760422387L,
        225327671L
    };
    public static int absmax = 0;//Don't waste time with trash
    public static HashSet<String> archives = new HashSet<>();
    public static JTextArea textOutputFrame;
    public static String firstPerfect 
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
    public MLAttempt2(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                setTitle("LOTTERY?");
                setSize(500, 300);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
    }
    private static class Generator{
        public String commands = "";
        public int reward = 0;
        public Generator(){
            for (int i = 0; i < 6; i++)
                this.mutate();
            commands = commands + " " +  makeTree(6) + " ";
        }
        public Generator(int n){
            for (int i = 0; i < n; i++){
                this.mutate();
                commands = commands + " " +  makeTree(4) + " ";
            }
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
        public void mutate(){
            commands = commands + (operators[(int) (operators.length*Math.random())]) + " ";
        }
        public void smutate(){
            commands = commands + (smalloperators[(int) (smalloperators.length*Math.random())]) + " ";
        }
        public int times;
        public long getValOld(){
            return getVal(date);
        }
        public long getVal(long date){ 
            Long current = 0x0L;
            
            for (String s : this.commands.split(" ")){
                times = 1;
                
                while (s.startsWith("2x")){
                    times = times*2;
                    s = s.substring(2);
                }
                for (int i = 0; i < times ; i++){
                    if (s.contains("_")){
                        s = handleConditional(s, current);
                    }
                    
                    if (s.equals("(T)")){
                        current = current + date;
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
                    } else if (s.equals("sqrt")){
                        current = (long) Math.sqrt(current);
                    } else if (s.equals("(ex2)")){
                        current = current*current;
                    } else if (s.equals("(T^3)")){
                        current = current + (date*date*date);
                    } else if (s.equals("(-T^3)")){
                        current = current - (date*date*date);
                    } else if (s.equals("0.5")){
                        current = current/2;
                    } else if (s.equals("2")) {
                        current = current*2;
                    } else if (s.startsWith("(ex")){
                        try {
                            current = (long) Math.pow(current, Double.parseDouble(s.substring(3, s.length()-1)));
                        } catch (Exception ex){
                            System.out.println(s.substring(3, s.length()-1));
                            System.out.println(s);
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
                            System.out.println("line313 " + s);
                        }
                    }
                }
            }
            return current;
        }
    }    
    /**
     * Makes a tree of slight changes of n height
     * @param n height
     * @return a tree of slight changes of n height.
     */
    public static String makeSTree(int n){
        if (n == 0) return smalloperators[(int) (smalloperators.length*Math.random())];
        else return "((genN" + conditionals[(int)(Math.random()*conditionals.length)] 
                + ((long) (Math.random()*Math.random()*Math.random()*Math.random()*Math.random()*Integer.MAX_VALUE))
                + ")?_" + (makeTree(n-1)) 
                + "_;_" + (makeTree(n-1)) + ")";

    }
    /**
     * Makes a tree of n height     
     * @param n height
     * @return a tree of conditionals of n height.
     */
    public static String makeTree(int n){
        if (n == 0) return operators[(int) (operators.length*Math.random())];
        else return "((genN" + conditionals[(int)(Math.random()*conditionals.length)] 
                + ((long) (Math.random()*Math.random()*Math.random()*Integer.MAX_VALUE))
                + ")?_" + (makeTree(n-1)) 
                + "_;_" + (makeTree(n-1)) + ")";

    }
    /**
     * Makes a conditional statement.
     * @return a conditional statement.
     */
    public static String makeConditional(){
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
    public static String handleConditional(String s, long current){
        long temp = System.currentTimeMillis();
        while (s.contains("_")){
            if (System.currentTimeMillis()-temp > 3000){
                System.out.println("Issue; " + s);
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
    public static int getCloserIndex(int b, String s){
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
    public static Generator combine(Generator g, Generator g2, boolean mode){
        if (mode){
            String ret = "";
            int sent = 0;
            String[] arr1 = g.commands.split(" ");
            String[] arr2 = g2.commands.split(" ");
            
            for (sent = 0; (sent < arr1.length/2) && (sent < arr2.length/2); sent++){
                ret = ret + arr1[sent] + " ";
                ret = ret + arr2[sent] + " ";
            }
            if (sent < g.commands.length()){
                for (int i = sent; i < arr1.length/2; i++){
                    ret = ret + arr1[i] + " ";
                }
            }
            if (sent < g2.commands.length()){
                for (int i = sent; i < arr2.length/2; i++){
                    ret = ret + arr2[i] + " ";
                }
            }
            return new Generator(ret);
        } else {
            return new Generator(g.commands+g2.commands);
        }
    }
    /** Restarts the main loop's data, saving some of it.
     * 
     */
    public static void restart(){
        System.out.println("\nRESTART\n");
        generators = new Generator[5];
        int sent = 0;
        //Sometimes engage in a 'fight' of the 'fittest'.
        //Rarely 'nuke' if there are more than three items, always >6.
        if ((r.nextBoolean() && archives.size() > 3) || archives.size() > 6){
            generators = new Generator[archives.size()+3];
            for (String s : archives){
                if (sent == generators.length) break;
                generators[sent++] = new Generator(s);
            }
            if (Math.random() < 0.5 || archives.size() > 6){
                for (int i = 0; i < 5; i++)
                    System.out.println("EXTINCTION");
                //extinction event leaves the best of the best alive, speeds up
                archives.clear();
            }
        }
        //Fill in with random generators.
        for (int i = sent; i < generators.length; i++){
            generators[i] = new Generator(10);
        }
        
    }
    
    
    public static void main(String[] args) {
        String stringin = makeTree(2);
        System.out.println(stringin);
        System.out.println(handleConditional(stringin, 1740199905));
        
        
        MLAttempt2 rt = new MLAttempt2();
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
        rt.add(textOutput);
        rt.setVisible(true);
        
        
        for (int i = 0; i < generators.length; i++){
            generators[i] = new Generator();
        }
        int flop = 0;
        int bigflop = 0;
        int weightEarly = 0;
        int numRight = 0;
        int inttemp = 0;
        String temp = "";
        String outemp = "";
        String temp2 = "";
        String[] sarrtemp;
        restart();
        while (true){
            if (max < absmax/2 && bigflop > 1999){
                System.out.println("TRASHCOMPACTOR");
                archives.add(" " + Math.random());
                flop = 0;
                bigflop = 0;
                weightEarly = 0;
                numRight = 0;
                inttemp = 0;
                temp = "";
                outemp = "";
                temp2 = "";
                sarrtemp = new String[0];
                bigflop = 0;
                restart();
            }
            if (bigflop >= 10000){
                System.out.println("?: " + max +" "+ absmax);
                System.out.println(bigflop);
                archives.add(generators[maxindex].commands);
                flop = 0;
                bigflop = 0;
                weightEarly = 0;
                numRight = 0;
                inttemp = 0;
                temp = "";
                outemp = "";
                temp2 = "";
                sarrtemp = new String[0];
                bigflop = 0;
                restart();
            } else {
                bigflop++;
            }
            
            //Determine rewards.
            //if (flop == 99) System.out.println();
            max = 0;
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
                            generators[i].commands = 
                                    generators[i].commands.replace(sarrtemp[g-1] + " " + sarrtemp[g],
                                    "(ex" + 
                                    (Double.parseDouble(sarrtemp[g].substring(3, sarrtemp[g].length()-1))
                                    *Double.parseDouble(sarrtemp[g-1].substring(3, sarrtemp[g-1].length()-1))) + ")");
                        }
                        else if (sarrtemp[g].startsWith("(:") && sarrtemp[g-1].startsWith("(:")){
                            /*generators[i].commands = generators[i].commands.replace(sarrtemp[g] + " " + sarrtemp[g-1], "(:" + (Integer.parseInt(sarrtemp[g].substring(2, sarrtemp[g].length()-1))+Integer.parseInt(sarrtemp[g-1].substring(2, sarrtemp[g-1].length()-1))) + ") ");*/
                            generators[i].commands = generators[i].commands.replace(sarrtemp[g-1] + " " + sarrtemp[g], "(:" + (Long.parseLong(sarrtemp[g].substring(2, sarrtemp[g].length()-1))+Long.parseLong(sarrtemp[g-1].substring(2, sarrtemp[g-1].length()-1))) + ") ");
                        }
                    }
                }
                generators[i].commands = generators[i].commands.replaceAll("  ", " ");
                generators[i].commands = generators[i].commands.replace("(-100T) (100T) ", "  ");
                generators[i].commands = generators[i].commands.replace("(100T) (-100T) ", "  ");
                generators[i].commands = generators[i].commands.replace("(T) (-T) ", "  ");
                generators[i].commands = generators[i].commands.replace("(-T) (T) ", "  ");
                generators[i].commands = generators[i].commands.replace("(/100T) (-/100T)", "  ");
                generators[i].commands = generators[i].commands.replace("(-/100T) (/100T)", "  ");
                generators[i].commands = generators[i].commands.replace("(T^3) (-T^3) ", "  ");
                generators[i].commands = generators[i].commands.replace("(-T^3) (T^3) ", "  ");
                generators[i].commands = generators[i].commands.replace("(exInfinity) ", "  ");
                
                if (flop == 999) 
                    System.out.println(generators[i].commands);
                generators[i].reward = 0;
                
                
                
                    
                for (int c = 0; c < winners.length; c++){
                    winner = winners[c];
                    date = dates[c];
                    
                    numRight = 0;
                    weightEarly = 0;
                    
                    currentVal = generators[i].getValOld();
                    //for how many numbers are identical reward
                    //also subtract for having the number be far away
                    for (int g = 0; (g < (""+winner).length()) && (g < (currentVal + "").length()); g++){
                        
                        if ((currentVal + "").charAt(g)==(winner + "").charAt(g)){
                            weightEarly += ((""+winner).length()) < ((currentVal + "").length()) ? (currentVal + "").length() - g : (winner + "").length() - g;
                            numRight++;
                        }
                        
                    }
                    
                    
                    generators[i].reward += Math.pow(weightEarly, 2);
                    generators[i].reward += Math.pow(numRight, 3);
                    
                    //if the number is in the ballpark reward
                    generators[i].reward += 100*(currentVal+"").length();
                    generators[i].reward -= 1000*Math.abs(((("" + winner).length() - ("" + currentVal).length())));
                    generators[i].reward -= 10*((Math.abs(currentVal-winner))/(Math.pow(10, Math.max((winner+"").length(), (currentVal+"").length()))));
                    //generators[i].reward -= generators[i].commands.length()/1000;
                    //generators[i].reward -= (currentVal+"").length();
                    //System.out.println((Math.abs(1000*(winner-currentVal))/Math.pow(10, (winner+"").length())));
                    if (currentVal < 0) generators[i].reward -= 10000; 
                    
                    
                    //if the number is equal greatly reward
                    if (winner == currentVal){
                        if (flop == 999) textOutputFrame.append("PERFECT\n");
                        if (flop == 999) System.out.println("Perfect run" + generators[i].commands);
                        generators[i].reward+= 1000;
                    }
                    
                }
                
                
                
                
                
                //if (flop == 99) System.out.println(generators[i].reward);
                if (generators[i].reward > max){
                    max = generators[i].reward;
                    maxindex = i;
                }
                if (generators[i].reward > absmax){
                    absmax = generators[i].reward;
                }
            }        
            
            outemp = "";
            //if (flop == 99) System.out.println();
            if (flop >= 999){
                for (int i = 0; i < winners.length; i++){
                    System.out.println("WINNER:\t" + winners[i] + "\tDATE: " + dates[i]);
                    outemp = outemp + "WINNER:\t" + winners[i] + "\tDATE: " + dates[i] + "\n";
                    winner = winners[i];
                    date = dates[i];
                    currentVal = generators[maxindex].getValOld();
                    System.out.println("GUESS:\t" + currentVal + "\n");
                    outemp = outemp + "GUESS:\t" + currentVal + "\n";
                }
                System.out.println("Dist: " + (Math.abs(("" + winner).length() - ("" + currentVal).length())));
                outemp = outemp + "Dist: " + (Math.abs(("" + winner).length() - ("" + currentVal).length())) + "\n";
                System.out.println("Max: " + "\t\t" +currentVal +" "+ generators[maxindex].commands);
                outemp = outemp + "Max: " + "\t\t" +currentVal +" "+ generators[maxindex].commands + "\n";
                System.out.println("Max Reward: " + max);
                textOutputFrame.append(outemp);
                
                inttemp = 0;
                for (String s : textOutputFrame.getText().split("\n")){
                    if (s.length() > inttemp) inttemp = s.length();
                }
                textOutputFrame.setPreferredSize(new Dimension(inttemp*40, 20*(textOutputFrame.getText().length()-textOutputFrame.getText().replaceAll("\n", "").length())));

                flop = 0;
                
            } else {
                flop++;
            }
            //Mutate based on rewards
            for (int i = 0; i < generators.length; i++){
                if (generators[i].reward < max/16){
                    if (r.nextBoolean())
                        generators[i] = combine(generators[i], generators[maxindex], true);
                    else
                        generators[i] = new Generator(generators[maxindex].commands);
                }
                if (i != maxindex){
                    if (generators[i].commands.equals(generators[maxindex].commands)){

                            if (r.nextBoolean()) generators[i].smutate();
                            else generators[i].commands = generators[i].commands + " " + makeSTree((int) (Math.random()*6)) + " ";
             
                    } else {
                        if (r.nextBoolean()) generators[i].commands = generators[i].commands + " " + makeTree((int) (Math.random()*6)) + " ";
                        if (r.nextBoolean()) generators[i].intermutate();
                        if (r.nextBoolean()) generators[i].delmutate();
                    }
                }
            }
        }
    }              
}
