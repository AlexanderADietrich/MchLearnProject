/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author voice
 */
public class GeneticRefiner {
    public long[] inputs;
    public Random r = new Random();
    public simpGenerator[] gens;
    public GeneticRefiner(long[] inputs){
        this.inputs=inputs;
        this.gens = new simpGenerator[10];
        for (int i = 0; i < 10; i++){
            gens[i] = new simpGenerator();
            for (int c = 0; c < 5; c++)
                gens[i].mutate();
            gens[i].update();
        }
    }
    public String combine(String s1, String s2){
        String[] arrtemp = s1.split(" ");
        String[] arrtemp2= s2.split(" ");
        int len = (arrtemp.length+arrtemp2.length)/2;
        String retVal = "";
        for (int i = 0; i < len; i++){
            if (r.nextBoolean() && i < arrtemp.length){
                retVal = retVal + arrtemp[i] + " ";
            } else if (i < arrtemp2.length){
                retVal = retVal + arrtemp2[i] + " ";
            }
        }
        return retVal;
    }
    public static String handleDigTree(String digTree, long val){
        String[] choices = digTree.substring(2, digTree.length()-1).split("_");
        //System.out.println(digTree);
        //System.out.println(Arrays.toString(choices));
        return choices[(int)Math.abs(val % 10)];
    }
    
    /**
     * A very very cheesy efficient algorithm.
     * @param genes
     * @param maxError
     * @return 
     */
    public String logDelete(String genes, long maxError){
        String[] arrtemp = genes.split(" ");
        int times = (int) (Math.log1p(arrtemp.length));
        String bestGenes = genes;
        String[] copy = new String[arrtemp.length];
        int index = 0;
        int sent = 0;
        while (sent < times){
            if (r.nextBoolean()) continue;
            index = (index+5)%arrtemp.length;
            System.arraycopy(arrtemp, 0, copy, 0, arrtemp.length);
            copy[index] = "";
            String testGenes = "";
            for (String s : copy){
                testGenes = testGenes + s + " ";
            }
            simpGenerator testGenerator = new simpGenerator(testGenes);
            if (testGenerator.errorTotal < maxError){
                maxError = testGenerator.errorTotal;
                bestGenes = testGenerator.genes;
            }
            sent++;
        }
        return bestGenes;
    }
    public String maxDelete(String genes, long maxError){
        String[] arrtemp = genes.split(" ");
        String bestGenes = genes;
        String[] copy = new String[arrtemp.length];
        for (int i = 0; i < arrtemp.length; i++){
            System.arraycopy(arrtemp, 0, copy, 0, arrtemp.length);
            copy[i] = "";
            String testGenes = "";
            for (String s : copy){
                testGenes = testGenes + s + " ";
            }
            simpGenerator testGenerator = new simpGenerator(testGenes);
            if (testGenerator.errorTotal < maxError){
                maxError = testGenerator.errorTotal;
                bestGenes = testGenerator.genes;
            }
        }
        return bestGenes;
    }
    public String maxMutate(String genes, long maxError){
        String bestGenes = genes;
        String copy = genes + "";
        for (int i = 0; i < DATA.operators.length; i++){
            copy = genes + "";
            copy = genes + DATA.operators[i];
            simpGenerator testGenerator = new simpGenerator(copy);
            if (testGenerator.errorTotal < maxError){
                maxError = testGenerator.errorTotal;
                bestGenes = testGenerator.genes;
            }
        }
        return bestGenes;
    }
    public void run(int times){
        for (int ti = 0; ti < times; ti++){
            //find best
            int ind = -1;
            long best = Long.MAX_VALUE;
            for (int i = 0; i < gens.length;i++){
                if (gens[i].getTotErr() < best){
                    best = gens[i].getTotErr();
                    ind = i;
                }
            }
            System.out.println("Run " + ti + "Least Error: " + best + " Refiner: " + gens[ind].genes);
            for (int datPos = 0; datPos < DATA.dates.length; datPos++){
                System.out.print(  "Month: \t" + DATA.months[datPos] + " ");
                System.out.print(  "Day:   \t" + DATA.dates[datPos] + " ");
                System.out.print(  "Input: \t" + inputs[datPos] + " "); 
                System.out.print(  "Val:   \t" + gens[ind].getVal(datPos) + " ");
                System.out.print(  "Err:   \t" + (gens[ind].getErr(datPos)*1.0)/(DATA.test[datPos]*1.0) + " ");
                System.out.println("Actual:\t" + DATA.test[datPos]);
            }
            
            
            //mutate/combine
            for (int i = 0; i < gens.length; i++){
                if (i != ind){
                    if (r.nextBoolean() && r.nextBoolean()){
                        gens[i] = new simpGenerator(combine(gens[i].genes, gens[(i+2)%10].genes));
                    }
                    if (Math.random() < 0.05){
                        gens[i] = new simpGenerator();
                    }
                    for (int d = 0; d < 5; d++){
                        gens[i].mutate();
                        if (r.nextBoolean()){
                            gens[i].delMutate();
                        }
                        if (Math.random() < 0.3){
                            gens[i].addDigTree();
                        }
                    }
                }
                
                if (Math.random() < 0.01){
                    gens[(ind+1)%10] = new simpGenerator(maxDelete(gens[ind].genes, gens[ind].errorTotal));
                }
                
                else if (Math.random() < 0.2){
                    gens[(ind+1)%10] = new simpGenerator(logDelete(gens[ind].genes, gens[ind].errorTotal));
                }
                else if (Math.random() < 0.3){
                    gens[(ind+2)%10] = new simpGenerator(maxMutate(gens[ind].genes, gens[ind].errorTotal));
                }
            }
        }
    }
    public String makeDigTree(){
        String retVal = "(_";
        for (int i = 0; i < 10; i++)
            retVal = retVal + DATA.operators[r.nextInt(DATA.operators.length)] + "_";
        retVal = retVal + ")";
        return retVal;
    }
    public class simpGenerator{
        String genes;
        long[] vals;
        long[] errors;
        long errorTotal;
        boolean changed;
        
        public simpGenerator(String s){
            genes = s;
            vals = new long[DATA.dates.length];
            errors = new long[DATA.dates.length];
            
            changed = true;
            update();
        }
        public simpGenerator(){
            genes = "" + DATA.operators[r.nextInt(DATA.operators.length)];
            vals = new long[DATA.dates.length];
            errors = new long[DATA.dates.length];
            
            changed = true;
            update();
        }
        public void addDigTree(){
            genes = genes + " " + makeDigTree();
            changed = true;
        }
        public void mutate(){
            genes = genes + " " + DATA.operators[r.nextInt(DATA.operators.length)];
            changed = true;
        }
        public void delMutate(){
            String[] arrtemp = genes.split(" ");
            arrtemp[r.nextInt(arrtemp.length)] = "";
            genes = "";
            for (String s : arrtemp){
                genes = genes + s + " ";
            }
        }
        public long getVal(int pos){
            if (changed == true) update();
            return vals[pos];
        }
        public long getErr(int pos){
            if (changed == true) update();
            return errors[pos];
        }
        public long getTotErr(){
            if (changed == true) update();
            return errorTotal;
        }
        public void update(){
            errorTotal = 0;
            for (int i = 0; i < DATA.dates.length; i++){
                vals[i] = inputs[i];
                String[] arrtemp = genes.split(" ");
                for (String s : arrtemp){
                    //System.out.println("Operator: " + s + " Val " + vals[i]);
                    vals[i] = handleOperator(vals[i], i, s);
                    //System.out.println("Final: " + vals[i] + "\n");
                }
                errors[i] = (long) Math.abs(vals[i]-DATA.test[i]);
                if (errors[i] < 0) System.out.println(vals[i] + " " + DATA.test[i] + "?");
                errorTotal += errors[i];
            }
            changed = false;
        }
    }
    private static class DATA{
        public static String[] operators = new String[]{
            "(antidigit)",
            "(antidigit2)",
            "(antidigit3)",
            //"(ex0.5)",
            //"(ex4)",
            "(M)",
            "(T)",
            "(-M)",
            "(-T)",
            "(M^3)",
            "(T^3)",
            "(-M^3)",
            "(-T^3)",
            "(2^M)",
            "(2^T)",
            "(1.3^(100-M))",
            "(1.3^(100-T))",
            "(100T)",
            "(1000T)",
            "(-100T)",
            "(-1000T)",
            "(/100T)",
            "(-/100T)",
            "(100M)",
            "(1000M)",
            "(-100M)",
            "(-1000M)",
            "(/100M)",
            "(-/100M)",
            //"(sqrt)",
            //"(ex2)",
            //"(0.5)",
            "(2)",
            "(:1)",
            "(:-1)",
            "(:10)",
            "(:-10)",
        };
        public static long[] months = new long[]{
            //12,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            10,
            10,
            
            
            10, 
            10, 
            10, 
            10, 
            10, 
            10
        };
        
        public static long[] dates = new long[]{
            //1,
            28,
            24,
            21,
            17,
            14,
            10,
            7,
            3,
            31,
            27,
            
            
            24, 
            20, 
            17, 
            13, 
            10, 
            6
        };
        public static long[] test = new long[]{
            //105847115526L,
            46859691921L, 
            563358511118L,
            14233875518L,
            866820525L,
            49624276923L,
            53295573424L,
            502834422625L,
            246521321511L,
            40394772520L,
            1312827194L,
            
            56534521322L, 
            621654576923L, 
            36469685715L,
            146511433215L,
            2760422387L,
            225327671L
        };
    }
    public static long handleOperator(long input, int datPos, String operator){
        long retVal = input;
        if (operator.contains("_")){
            operator = handleDigTree(operator, input);
        }
        
        
        if ("(antidigit)".equals(operator)){
            retVal = retVal /10L;
        } else if ("(antidigit2)".equals(operator)){
            retVal = retVal / 100L;
        } else if ("(antidigit3)".equals(operator)){
            retVal = retVal / 1000L;
        } else if ("(ex0.5)".equals(operator)){
            retVal = (long) Math.pow(retVal, 0.5);
        } else if ("(ex4)".equals(operator)){
            retVal = (long) Math.pow(retVal, 4);
        } else if ("(M)".equals(operator)){
            retVal = retVal + DATA.months[datPos];
        } else if ("(T)".equals(operator)){
            retVal = retVal + DATA.dates[datPos];
        } else if ( "(-M)".equals(operator)){
            retVal = retVal - DATA.months[datPos];
        } else if ("(-T)".equals(operator)){
            retVal = retVal - DATA.dates[datPos];
        } else if ("(M^3)".equals(operator)){
            retVal = retVal + (long) Math.pow(DATA.months[datPos], 3);
        } else if ("(T^3)".equals(operator)){
            retVal = retVal + (long) Math.pow(DATA.dates[datPos], 3);
        } else if ("(-M^3)".equals(operator)){
            retVal = retVal - (long) Math.pow(DATA.months[datPos], 3);
        } else if ("(-T^3)".equals(operator)){
            retVal = retVal - (long) Math.pow(DATA.dates[datPos], 3);
        } else if ("(2^M)".equals(operator)){
            retVal = retVal + (long) Math.pow(2, DATA.months[datPos]);
        } else if ("(2^T)".equals(operator)){
            retVal = retVal + (long) Math.pow(2, DATA.dates[datPos]);
        } else if ("(1.3^(100-M))".equals(operator)){
            retVal = retVal + (long) Math.pow(1.3, 100-DATA.months[datPos]);
        } else if ("(1.3^(100-T))".equals(operator)){
            retVal = retVal + (long) Math.pow(1.3, 100-DATA.dates[datPos]);
        } else if ( "(100T)".equals(operator)){
            retVal = retVal + 100*DATA.dates[datPos];
        } else if ("(1000T)".equals(operator)){
            retVal = retVal + 1000*DATA.dates[datPos];
        } else if ("(-100T)".equals(operator)){
            retVal = retVal - 100*DATA.dates[datPos];
        } else if ("(-1000T)".equals(operator)){
            retVal = retVal - 1000*DATA.dates[datPos];
        } else if ("(/100T)".equals(operator)){
            retVal = retVal + DATA.dates[datPos]/100;
        } else if ("(-/100T)".equals(operator)){
            retVal = retVal - DATA.dates[datPos]/100;
        } else if ( "(100M)".equals(operator)){
            retVal = retVal + 100*DATA.months[datPos];
        } else if ("(1000M)".equals(operator)){
            retVal = retVal + 1000*DATA.months[datPos];
        } else if ("(-100M)".equals(operator)){
            retVal = retVal - 100*DATA.months[datPos];
        } else if ("(-1000M)".equals(operator)){
            retVal = retVal - 1000*DATA.months[datPos];
        } else if ("(/100M)".equals(operator)){
            retVal = retVal + DATA.months[datPos]/100;
        } else if ("(-/100M)".equals(operator)){
            retVal = retVal - DATA.months[datPos]/100;
        } else if ("(sqrt)".equals(operator)){
            retVal = (long) Math.sqrt(retVal);
        } else if ("(ex2)".equals(operator)){
            retVal = retVal*retVal;
        } else if ("(0.5)".equals(operator)){
            retVal = retVal/2L;
        } else if ("(2)".equals(operator)){
            retVal = retVal*2L;
        } else if ("(:1)".equals(operator)){
            retVal = retVal+1L;
        } else if ("(:-1)".equals(operator)){
            retVal = retVal-1L;
        } else if ("(:10)".equals(operator)){
            retVal = retVal+10L;
        } else if ("(:-10)".equals(operator)){
            retVal = retVal-10L;
        }
        
        return retVal;
    }
    
    
}
