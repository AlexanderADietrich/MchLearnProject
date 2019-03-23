/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

import java.util.HashSet;
import java.util.Random;


/**
 *
 * @author voice
 */
public class NeuralNetworkII {
    /**
     * I've done some more study on neural networks and I'd like to make
     * a more "traditional" one.
     * 
     * This class also serves to show my "process" in developing an algorithm.
     * What it fails to show is my work on paper and research online, but at
     * the least it does show how I go from hardcode to algorithm in order to
     * best take advantage of my preference for "prospecting" instead of 
     * "judging."
     */
    
    /*
    I could reference NeuralNetwork.java but it's less work per reference to put 
    here and I'm not exactly concerned with space efficiency for this project.
    */
    private static final class DATA{
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
        
        /*public static long[] test = new long[]{
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
        };*/
        
        public static long[] test = new long[]{//high of seattle
            //45,
            50,
            47,
            51,
            48,
            42,
            47,
            49,
            53,
            55,
            56,
            
            59, 
            54, 
            69,
            59,
            60,
            56,
        };
        
        /*public static long[] test = new long[]{
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
        };*/
    }
    private static final class DATAII{
        public static String[] datenumber = {
            "1 30 2019",
            "2 12 16 29 54 6",
            "1 26 2019",
            "8 12 20 21 32 10",
            "1 23 2019",
            "23 25 47 48 50 24",
            "1 19 2019",
            "5 8 41 65 66 20",
            "1 16 2019",
            "14 29 31 56 61 1",
            "1 12 2019",
            "7 36 48 57 58 24",
            "1 09 2019",
            "6 19 37 49 59 22",
            "1 05 2019",
            "3 7 15 27 69 19",
            "1 02 2019",
            "8 12 42 46 56 12",
            "12 29 2018",
            "12 42 51 53 62 25",
            "12 26 2018",
            "5 25 38 52 67 24",
            "12 22 2018",
            "21 28 30 40 59 26",
            "12 19 2018",
            "15 29 31 37 43 16",
            "12 15 2018",
            "8 38 43 52 55 17",
            "12 12 2018",
            "4 9 21 29 64 26",
            "12 08 2018",
            "14 32 34 46 61 10",
            "12 05 2018",
            "9 11 36 37 38 11",
            "12 01 2018",
            "10 11 47 55 58 26",
            "11 28 2018",
            "4 19 59 68 69 21",
            "11 24 2018",
            "11 33 51 56 58 18",
            "11 21 2018",
            "7 14 23 38 55 18",
            "11 17 2018",
            "6 8 20 52 68 5",
            "11 14 2018",
            "7 42 49 62 69 23",
            "11 10 2018",
            "5 29 34 53 57 24",
            "11 07 2018",
            "26 28 34 42 50 25",
            "11 03 2018",
            "15 21 24 32 65 11",
            "10 31 2018",
            "7 25 39 40 47 20",
            "10 27 2018",
            "8 12 13 19 27 4",
            "10 24 2018",
            "3 21 45 53 56 22",
            "10 20 2018",
            "16 54 57 62 69 23",
            "10 17 2018",
            "3 57 64 68 69 15",
            "10 13 2018",
            "11 14 32 43 65 15",
            "10 10 2018",
            "8 23 27 42 60 7",
            "10 06 2018",
            "1 22 27 53 67 15",
            "10 03 2018",
            "41 53 59 63 66 3",
            "9 29 2018",
            "9 17 34 59 64 22",
            "9 26 2018",
            "1 2 7 30 50 8",
            "9 22 2018",
            "24 61 63 64 69 18",
            "9 19 2018",
            "4 39 48 50 51 11",
            "9 15 2018",
            "2 18 19 24 34 3",
            "9 12 2018",
            "6 28 48 63 64 24",
            "9 08 2018",
            "3 13 20 32 33 21",
            "9 05 2018",
            "6 15 50 59 60 13",
            "9 01 2018",
            "11 54 55 61 66 9",
            "8 29 2018",
            "25 41 53 57 67 12",
            "8 25 2018",
            "20 25 54 57 63 8",
            "8 22 2018",
            "1 7 45 47 69 13",
            "8 18 2018",
            "24 34 52 61 67 16",
            "8 15 2018",
            "12 15 28 47 48 16",
            "8 11 2018",
            "5 43 56 62 68 24",
            "8 08 2018",
            "10 21 30 43 63 17",
            "8 04 2018",
            "3 11 38 44 58 2",
            "8 01 2018",
            "5 22 32 38 58 26",
            "7 28 2018",
            "22 27 46 56 65 13",
            "7 25 2018",
            "2 18 41 44 64 26",
            "7 21 2018",
            "9 23 56 58 68 1",
            "7 18 2018",
            "1 10 27 28 36 12",
            "7 14 2018",
            "22 41 42 49 67 11",
            "7 11 2018",
            "19 21 27 46 47 7",
            "7 07 2018",
            "1 10 43 45 64 22",
            "7 04 2018",
            "4 7 15 41 44 10",
            "6 30 2018",
            "3 9 20 42 61 24",
            "6 27 2018",
            "7 28 37 62 63 15",
            "6 23 2018",
            "16 29 43 45 56 25",
            "6 20 2018",
            "4 14 23 27 56 13",
            "6 16 2018",
            "9 45 57 58 65 9",
            "6 13 2018",
            "13 20 38 45 55 1",
            "6 09 2018",
            "6 10 15 25 36 14",
            "6 06 2018",
            "23 28 41 53 56 14",
            "6 02 2018",
            "23 25 37 44 64 7",
            "5 30 2018",
            "17 23 26 46 68 20",
            "5 26 2018",
            "1 21 31 45 49 21",
            "5 23 2018",
            "20 54 56 61 64 7",
            "5 19 2018",
            "3 6 9 17 56 25",
            "5 16 2018",
            "17 19 21 22 51 19",
            "5 12 2018",
            "22 42 45 55 56 14",
            "5 09 2018",
            "11 16 38 50 69 19",
            "5 05 2018",
            "14 29 36 57 61 17",
            "5 02 2018",
            "5 14 31 40 50 6",
            "4 28 2018",
            "20 22 28 45 50 8",
            "4 25 2018",
            "17 18 39 56 64 12",
            "4 21 2018",
            "40 50 54 62 69 19",
            "4 18 2018",
            "9 10 12 17 23 9",
            "4 14 2018",
            "17 19 26 61 62 15",
            "4 11 2018",
            "16 18 27 55 67 18",
            "4 07 2018",
            "2 17 20 38 39 20",
            "4 04 2018",
            "8 24 42 54 64 24",
            "3 31 2018",
            "8 24 52 55 61 21",
            "3 28 2018",
            "6 8 26 52 53 21",
            "3 24 2018",
            "10 33 45 53 56 24",
            "3 21 2018",
            "3 4 18 29 61 25",
            "3 17 2018",
            "22 57 59 60 66 7",
            "3 14 2018",
            "6 12 24 41 68 9",
            "3 10 2018",
            "43 44 54 61 69 22",
            "3 07 2018",
            "6 13 19 36 51 18",
            "3 03 2018",
            "13 17 25 36 40 5",
            "2 28 2018",
            "12 30 59 65 69 16",
            "2 24 2018",
            "24 25 38 62 63 6",
            "2 21 2018",
            "7 15 31 34 36 8",
            "2 17 2018",
            "13 26 39 44 62 2",
            "2 14 2018",
            "37 39 44 46 69 26",
            "2 10 2018",
            "1 13 27 41 59 20",
            "2 07 2018",
            "23 34 35 40 47 10",
            "2 03 2018",
            "15 23 27 48 53 6",
            "1 31 2018",
            "4 7 14 46 59 22",
            "1 27 2018",
            "17 21 26 47 54 7",
            "1 24 2018",
            "5 9 11 33 64 21",
            "1 20 2018",
            "26 28 47 49 58 3",
            "1 17 2018",
            "3 33 37 51 57 21",
            "1 13 2018",
            "14 25 35 58 69 24",
            "1 10 2018",
            "7 24 33 49 50 4",
            "1 06 2018",
            "12 29 30 33 61 26",
            "1 03 2018",
            "2 18 37 39 42 12",
            "12 30 2017",
            "28 36 41 51 58 24",
            "12 27 2017",
            "3 9 16 56 60 3 ",
            "12 23 2017",
            "1 3 13 15 44 25",
            "12 20 2017",
            "1 20 61 64 69 20",
            "12 16 2017",
            "9 35 37 50 63 11",
            "12 13 2017",
            "2 24 28 51 58 7 ",
            "12 09 2017",
            "25 36 37 55 60 6 ",
            "12 06 2017",
            "19 20 50 55 62 9 ",
            "12 02 2017",
            "28 30 32 36 58 6 ",
            "11 29 2017",
            "24 26 28 59 63 16",
            "11 25 2017",
            "8 13 27 53 54 4 ",
            "11 22 2017",
            "35 37 46 51 61 13",
            "11 18 2017",
            "17 28 31 32 39 26",
            "11 15 2017",
            "23 32 44 48 50 25",
            "11 11 2017",
            "4 6 16 30 56 18",
            "11 08 2017",
            "12 14 20 21 34 22",
            "11 04 2017",
            "12 14 26 48 51 13",
            "11 01 2017",
            "3 6 19 26 44 1 ",
            "10 28 2017",
            "27 35 38 57 66 10",
            "10 25 2017",
            "18 22 29 54 57 8 ",
            "10 21 2017",
            "14 41 42 45 69 4 ",
            "10 18 2017",
            "30 49 54 66 69 8 ",
            "10 14 2017",
            "32 37 56 66 69 11",
            "10 11 2017",
            "1 3 13 19 69 23",
            "10 07 2017",
            "10 49 61 63 65 7 ",
            "10 04 2017",
            "22 23 62 63 66 24",
            "9 30 2017",
            "8 12 25 41 64 15",
            "9 27 2017",
            "8 10 21 23 25 22",
            "9 23 2017",
            "24 45 55 56 57 19",
            "9 20 2017",
            "39 48 53 67 68 26",
            "9 16 2017",
            "17 18 24 25 31 24",
            "9 13 2017",
            "17 24 35 57 63 19",
            "9 09 2017",
            "6 20 29 57 59 22",
            "9 06 2017",
            "8 14 32 58 67 17",
            "9 02 2017",
            "6 21 41 52 62 26",
            "8 30 2017",
            "19 28 43 67 69 7 ",
            "8 26 2017",
            "7 15 32 38 66 15",
            "8 23 2017",
            "6 7 16 23 26 4 ",
            "8 19 2017",
            "17 19 39 43 68 13",
            "8 16 2017",
            "9 15 43 60 64 4 ",
            "8 12 2017",
            "20 24 26 35 49 19",
            "8 09 2017",
            "12 30 36 47 62 9 ",
            "8 05 2017",
            "11 21 28 33 45 11",
            "8 02 2017",
            "1 16 54 63 69 18",
            "7 29 2017",
            "1 28 40 45 48 12",
            "7 26 2017",
            "7 19 21 42 69 12",
            "7 22 2017",
            "5 32 44 53 60 9 ",
            "7 19 2017",
            "50 51 59 61 63 4 ",
            "7 15 2017",
            "9 40 63 64 66 17",
            "7 12 2017",
            "1 2 18 23 61 9 ",
            "7 08 2017",
            "8 10 29 40 59 26",
            "7 05 2017",
            "4 9 16 54 68 21",
            "7 01 2017",
            "19 42 45 48 53 16",
            "6 28 2017",
            "29 37 46 53 68 8 ",
            "6 24 2017",
            "10 22 32 36 58 10",
            "6 21 2017",
            "14 46 61 65 68 13",
            "6 17 2017",
            "10 13 32 53 62 21",
            "6 14 2017",
            "5 22 43 57 63 24",
            "6 10 2017",
            "20 26 32 38 58 3 ",
            "6 07 2017",
            "5 21 57 66 69 13",
            "6 03 2017",
            "3 9 21 41 54 25",
            "5 31 2017",
            "4 33 39 46 60 6 ",
            "5 27 2017",
            "5 10 28 55 67 9 ",
            "5 24 2017",
            "28 32 33 38 62 15",
            "5 20 2017",
            "5 22 45 47 54 3 ",
            "5 17 2017",
            "4 11 39 45 48 9 ",
            "5 13 2017",
            "17 20 32 63 68 19",
            "5 10 2017",
            "29 31 46 56 62 8 ",
            "5 06 2017",
            "11 21 31 41 59 21",
            "5 03 2017",
            "17 18 49 59 66 9 ",
            "4 29 2017",
            "22 23 24 45 62 5 ",
            "4 26 2017",
            "1 15 18 26 51 26",
            "4 22 2017",
            "21 39 41 48 63 6 ",
            "4 19 2017",
            "1 19 37 40 52 15",
            "4 15 2017",
            "5 22 26 45 61 13",
            "4 12 2017",
            "8 14 61 63 68 24",
            "4 08 2017",
            "23 36 51 53 60 15",
            "4 05 2017",
            "8 20 46 53 54 13",
            "4 01 2017",
            "9 32 36 44 65 1 ",
            "3 29 2017",
            "8 15 31 36 62 11",
            "3 25 2017",
            "18 31 32 45 48 16",
            "3 22 2017",
            "2 9 27 29 42 9 ",
            "3 18 2017",
            "13 25 44 54 67 5 ",
            "3 15 2017",
            "16 30 41 48 53 16",
            "3 11 2017",
            "1 26 41 50 57 11",
            "3 08 2017",
            "23 33 42 46 59 4 ",
            "3 04 2017",
            "2 18 19 22 63 19",
            "3 01 2017",
            "10 16 40 52 55 17",
            "2 25 2017",
            "6 32 47 62 65 19",
            "2 22 2017",
            "10 13 28 52 61 2 ",
            "2 18 2017",
            "3 7 9 31 33 20",
            "2 15 2017",
            "5 28 33 38 42 19",
            "2 11 2017",
            "5 9 17 37 64 2 ",
            "2 08 2017",
            "14 20 42 49 66 5 ",
            "2 04 2017",
            "6 13 16 17 52 25",
            "2 01 2017",
            "9 43 57 60 64 10",
            "1 28 2017",
            "12 20 39 49 69 17",
            "1 25 2017",
            "18 28 62 66 68 22",
            "1 21 2017",
            "23 25 45 52 67 2 ",
            "1 18 2017",
            "9 40 41 53 58 12",
            "1 14 2017",
            "23 55 59 64 69 13",
            "1 11 2017",
            "1 3 13 16 43 24",
            "1 07 2017",
            "3 12 24 37 63 10",
            //"1 04 2017",
            //"16 17 29 41 42 4 ",
        };
        public static long[] days;
        public static long[] months;
        public static long[] years;
        //public static long[] val1;
        //public static long[] val2;
        //public static long[] val3;
        //public static long[] val4;
        //public static long[] val5;
        //public static long[] val6;
        
        public static long[][] vals;
        public static long[][] shiftVals;
        
        public static void toArray(){
            vals = new long[6][];
            days = new long[datenumber.length/2];
            months = new long[datenumber.length/2];
            years = new long[datenumber.length/2];
            vals[0] = new long[datenumber.length/2];
            vals[1] = new long[datenumber.length/2];
            vals[2] = new long[datenumber.length/2];
            vals[3] = new long[datenumber.length/2];
            vals[4] = new long[datenumber.length/2];
            vals[5] = new long[datenumber.length/2];
            
            String[] temp;
            String[] temp2;
            for (int i = 0; i < vals[0].length; i++){
                temp = datenumber[i*2].split(" ");
                temp2= datenumber[1+i*2].split(" ");
                months[i] = Long.parseLong(temp[0]);//month
                days[i] = Long.parseLong(temp[1]);//day
                years[i] = Long.parseLong(temp[2])-2000;//year normalized to reference point of 2000

                vals[0][i] = Long.parseLong(temp2[0]);//ball 1
                vals[1][i] = Long.parseLong(temp2[1]);//ball 2
                vals[2][i] = Long.parseLong(temp2[2]);//ball 3
                vals[3][i] = Long.parseLong(temp2[3]);//ball 4
                vals[4][i] = Long.parseLong(temp2[4]);//ball 5
                vals[5][i] = Long.parseLong(temp2[5]);//powerball
            }
            
            shiftVals = new long[6][vals[0].length];
            for (int i = 0; i < 6; i++){
                for (int r = 1; r < vals[0].length; r++){
                    shiftVals[i][r-1] = vals[i][r];// as recent-> past
                }
            }
            shiftVals[0][vals[0].length-1] = 16;
            shiftVals[1][vals[0].length-1] = 17;
            shiftVals[2][vals[0].length-1] = 29;
            shiftVals[3][vals[0].length-1] = 41;   
            shiftVals[4][vals[0].length-1] = 42;  
            shiftVals[5][vals[0].length-1] = 4;
            
        }
    }
    public Random r = new Random();
    public static class SigmNode{
        public double bias;//add to input
        public double currentInput;//current input
        public SigmNode[] outputs;//nodes that are outputs
        public double[] weights;//for the outputs
        public double deltafeed;
        public SigmNode(SigmNode[] outputs){
            bias = Math.random()*0;
            this.outputs=outputs;
            weights = new double[outputs.length];
            for (int i = 0; i < outputs.length; i++){
                weights[i] = Math.random();
            }
            deltafeed = 0;
        }
        public void disp(){
            //System.out.print("Bias " + bias + " ");
            //System.out.print("Weights:\t");
            for (double d : weights){
                //System.out.print(d+" ");
            }
            //System.out.println();
        }
        public void resetForward(){
            if (this.bias == Double.MAX_VALUE) return;
            this.deltafeed=0;
            for (SigmNode n : outputs){
                n.deltafeed = 0;
            }
            outputs[0].resetForward();
        }
        public void feedForward(){
            if (this.bias == Double.MAX_VALUE) return;
            for (int i = 0; i < weights.length; i++){
                //System.out.println("PASSED " + this.deltafeed*deltasigm(this.currentInput)*weights[i]);
                outputs[i].deltafeed += this.deltafeed*deltasigm(this.currentInput)*weights[i];
                outputs[i].feedForward();
            }
        }
        public void input(Double d){
            currentInput += d;
            ////System.out.println("Current Input Is " + currentInput);
        }
        public void reset(){
            currentInput = 0;
        }
        public void pass(){
            ////System.out.println("Before Sigm " + currentInput);
            double editInput= 1/(1+Math.pow(Math.E, -currentInput));
            ////System.out.println("After  Sigm " + editInput);
            for (int i = 0; i < outputs.length; i++){
                /*//System.out.println("Node   " + i);
                //System.out.println("Bias   " + bias);
                //System.out.println("Weight " + weights[i]);
                //System.out.println("Passed " + (bias + weights[i]*editInput));*/
                outputs[i].input(bias + weights[i]*editInput);
            }
            ////System.out.println();
        }
        public double getRes(){
            return currentInput;
        }
    }
    public static class ReLuNode {
        public double bias;//add to input
        public double currentInput;//current input
        public ReLuNode[] outputs;//nodes that are outputs
        public double[] weights;//for the outputs
        public double deltafeed;
        public ReLuNode(ReLuNode[] outputs){
            bias = Math.random()*0;
            this.outputs=outputs;
            weights = new double[outputs.length];
            for (int i = 0; i < outputs.length; i++){
                weights[i] = (Math.random()*2.0)-1.0;
            }
            deltafeed = 0;
        }
        public void disp(){
            //System.out.print("Bias " + bias + " ");
            //System.out.print("Weights:\t");
            for (double d : weights){
                //System.out.print(d+" ");
            }
            //System.out.println();
        }
        public void resetForward(){
            if (this.bias == Double.MAX_VALUE) return;
            this.deltafeed=0;
            for (ReLuNode n : outputs){
                n.deltafeed = 0;
            }
            outputs[0].resetForward();
        }
        public void feedForward(){
            if (this.bias == Double.MAX_VALUE) return;
            for (int i = 0; i < weights.length; i++){
                //System.out.println("PASSED " + this.deltafeed*deltasigm(this.currentInput)*weights[i]);
                outputs[i].deltafeed += this.deltafeed*((this.currentInput < 0) ? 0 : 1)*weights[i];
                outputs[i].feedForward();
            }
        }
        public void input(Double d){
            currentInput += d;
            ////System.out.println("Current Input Is " + currentInput);
        }
        public void reset(){
            currentInput = 0;
        }
        public void pass(){
            ////System.out.println("Before Sigm " + currentInput);
            double editInput= Math.max(0, this.currentInput);
            ////System.out.println("After  Sigm " + editInput);
            for (int i = 0; i < outputs.length; i++){
                /*System.out.println("Node   " + i);
                System.out.println("Bias   " + bias);
                System.out.println("Weight " + weights[i]);
                System.out.println("Passed " + (bias + weights[i]*editInput));
                System.out.println();*/
                outputs[i].input(bias + weights[i]*editInput);
            }
            ////System.out.println();
        }
        public double getRes(){
            return currentInput;
        }
    }
    public static class ReLuStructure{
        public ReLuNode[][] lists;
        public double error;
        public double[][] storage;
        public long[][] inputLists;
        public long[][] desiredOutput;
        public long[] input;
        public double dw;
        public boolean allowNegatives = true;
        public boolean allowNegativeAnswers = true;
        
        public ReLuStructure(int hiddenLayers, int[] layersSizes,
                long[][] inputLists, long[][] desiredOutput, int outputs,
                boolean allowNegatives, boolean allowNegativeAnswers){
            this.allowNegatives = allowNegatives;
            this.allowNegativeAnswers = allowNegativeAnswers;
            long total = 0;
            for (long lon[] : desiredOutput){
                for (long l : lon){
                    total += l;
                }
            }
            // (desiredOutput.length*desiredOutput[0].length)
            //System.out.println("Total" + total);
            //System.out.println(Math.pow((desiredOutput.length*desiredOutput[0].length), 2.71));
            
            //dw = (sum of x) / (n^(layers+1.71)) 
            
            
            
            dw = 0.000001;//placeholder
            
            this.inputLists = inputLists;
            this.desiredOutput = desiredOutput;
            
            this.lists = new ReLuNode[hiddenLayers+2][];
            this.lists[0] = new ReLuNode[inputLists.length];//input
            for (int i = 1; i < lists.length-1; i++){
                lists[i] = new ReLuNode[layersSizes[i-1]];//hidden
            }
            lists[lists.length-1] = new ReLuNode[outputs];//output
            
            for (int i = 0; i < lists[lists.length-1].length; i++){
                lists[lists.length-1][i] = new ReLuNode(new ReLuNode[]{});
                lists[lists.length-1][i].bias = Double.MAX_VALUE;
            }
            
            for (int i = lists.length-2; i >= 0; i--){
                for (int g = 0; g < lists[i].length; g++){
                    lists[i][g] = new ReLuNode(lists[i+1]);
                    lists[i][g].bias = Math.random()*0;
                }
            }
            this.storage = new double[desiredOutput.length][desiredOutput[0].length];
            calcError();
        }
        
        public ReLuStructure(int layers, int[] layersSizes, long[][] inputLists, long[][] desiredOutput){//layers not including output
            int total = 0;
            for (long lon : desiredOutput[0]){
                total += (lon+"").length();
            }
            dw = 1/ Math.pow(10, total/desiredOutput.length);
            
            this.inputLists = inputLists;
            this.desiredOutput = desiredOutput;
            
            this.lists = new ReLuNode[layers+1][];
            this.lists[0] = new ReLuNode[inputLists.length];//input
            for (int i = 1; i < lists.length-1; i++){
                lists[i] = new ReLuNode[layersSizes[i]];//hidden
            }
            lists[lists.length-1] = new ReLuNode[1];//output
            lists[lists.length-1][0] = new ReLuNode(new ReLuNode[1]);
            lists[lists.length-1][0].bias = Double.MAX_VALUE;
            
            for (int i = lists.length-2; i >= 0; i--){
                for (int g = 0; g < lists[i].length; g++){
                    lists[i][g] = new ReLuNode(lists[i+1]);
                    lists[i][g].bias = Math.random()*10;
                }
            }
            
            this.storage = new double[desiredOutput[0].length][desiredOutput.length];
        }
        public ReLuStructure(int layers, int inputs){//layers not including output
            
            
            
            //dw = 0.00000001;//2 digits to 8 digits
            //dw = 0.000000000000000000000000000001;//Average of 10 digits to 29 digits
            //System.out.println(dw);
            //System.out.println(1/ Math.pow(10, (DATA.test[0]+"").length()*3));
            dw = 1000000000/ Math.pow(10, (desiredOutput[0]+"").length());
            inputLists = new long[][]{DATA.dates, DATA.months};
            this.desiredOutput = new long[0][];
            this.desiredOutput[0] = DATA.test;
            
            this.lists = new ReLuNode[layers+1][];
            this.lists[0] = new ReLuNode[inputs];//input
            for (int i = 1; i < lists.length-1; i++){
                lists[i] = new ReLuNode[(int) (1024/layers)];//hidden
            }
            lists[lists.length-1] = new ReLuNode[1];//output
            lists[lists.length-1][0] = new ReLuNode(new ReLuNode[1]);
            lists[lists.length-1][0].bias = Double.MAX_VALUE;
            
            for (int i = lists.length-2; i >= 0; i--){
                for (int g = 0; g < lists[i].length; g++){
                    lists[i][g] = new ReLuNode(lists[i+1]);
                }
            }
            
            this.storage = new double[DATA.test.length][0];
        }
        public void reset(){
            error = 0;
        }
        public void input(long[] input){
            this.input=input;
        }
        public void calcError(){
            long[] inputs = new long[inputLists.length];
            for (int b = 0; b < inputLists[0].length; b++){
                for (int i = 0; i < inputLists.length; i++){
                    inputs[i] = inputLists[i][b];
                }
                input(inputs);
                calc(b);
            }
        }
        public void calc(int b){
            for (int i = 0; i < lists[0].length; i++){
                lists[0][i].currentInput = inputLists[i][b];
            }
            //reset
            for (int i = 1; i < lists.length; i++){//first hidden to output
                for (ReLuNode n : lists[i]){
                    n.reset();
                }
            }
            //calculate
            for (int i = 0; i < lists.length-1; i++){
                for (ReLuNode n : lists[i]){
                    n.pass();
                }
            }
            
            for (int i = 0; i < desiredOutput.length; i++){
                storage[i][b] = lists[lists.length-1][i].currentInput;
                // proof is for (1/2n)*(answer-prediction)^2 but 2 is negligible
                error += (1.0/(desiredOutput[i].length*desiredOutput.length))*Math.pow((lists[lists.length-1][i].currentInput - desiredOutput[i][b]), 2);
            }
            
            
        }
        public void updateWeights(){
            //input
            //hidden 1
            //hidden 2
            //hidden 3...
            //hidden n-1 where n is number of layers not including output
            //System.out.println(lists.length);
            //for (ReLuNode[] list : lists){
             //   System.out.println("\t"+list.length);
            //}
            
            
            for (int b = 0; b < lists.length-2; b++){
                for (int nodeIt = 0; nodeIt < lists[b].length; nodeIt++){
                    for (int weightIt = 0; weightIt < lists[b][nodeIt].weights.length; weightIt++){
                        lists[b+1][weightIt].resetForward();
                        lists[b+1][weightIt].deltafeed = Math.max(0, lists[b][nodeIt].currentInput);
                        lists[b+1][weightIt].feedForward();
                        //System.out.println(((sigm(lists[lists.length-1][0].currentInput))/10000000000000.0));
                        //Math.pow(0.1, lists.length-b)
                        
                        //System.out.println("\nHere");
                        for (int i = 0; i < lists[lists.length-1].length; i++)
                            lists[b][nodeIt].weights[weightIt] -= Math.pow(0.1, lists.length-b)*dw*error*lists[lists.length-1][i].deltafeed*(1/lists[lists.length-1].length);
                        
                        if (allowNegatives){
                            if (lists[b][nodeIt].weights[weightIt] < -1.0) lists[b][nodeIt].weights[weightIt] = -1;
                        }
                        else {
                            if (lists[b][nodeIt].weights[weightIt] < 0.0) lists[b][nodeIt].weights[weightIt] = 0;
                        }
                        if (lists[b][nodeIt].weights[weightIt] > 1.0) lists[b][nodeIt].weights[weightIt] = 1;
                    }
                }
            }
            //last non-output node
            for (int nodeIt = 0; nodeIt < lists[lists.length-2].length; nodeIt++){
                for (int weightIt = 0; weightIt < lists[lists.length-2][nodeIt].weights.length; weightIt++){
                    //System.out.println(nodeIt + " " + weightIt + " here");
                    
                    lists[lists.length-2][nodeIt].weights[weightIt] -= dw*error*Math.max(0, lists[lists.length-2][nodeIt].currentInput);
                    
                    if (allowNegatives && allowNegativeAnswers){
                        if (lists[lists.length-2][nodeIt].weights[weightIt] < -1.0) lists[lists.length-2][nodeIt].weights[weightIt] = -1;
                    }
                    else {
                        if (lists[lists.length-2][nodeIt].weights[weightIt] < 0.0) lists[lists.length-2][nodeIt].weights[weightIt] = 0;
                    }
                    if (lists[lists.length-2][nodeIt].weights[weightIt] > 1.0) lists[lists.length-2][nodeIt].weights[weightIt] = 1;
                }
            }
        }
        public void dispWeights(){
            System.out.println(allowNegatives);
            for (int b = 0; b < lists.length-2; b++){
                for (int nodeIt = 0; nodeIt < lists[b].length; nodeIt++){
                    for (int weightIt = 0; weightIt < lists[b][nodeIt].weights.length; weightIt++){
                        System.out.println(lists[b][nodeIt].weights[weightIt]);
                    }
                }
            }
            System.out.println();
            //last non-output node
            for (int nodeIt = 0; nodeIt < lists[lists.length-2].length; nodeIt++){
                for (int weightIt = 0; weightIt < lists[lists.length-2][nodeIt].weights.length; weightIt++){
                    System.out.println(lists[lists.length-2][nodeIt].weights[weightIt]);
                }
            }
            System.out.println();
        }
        public void adjustBias(){
            //last non-output node
            for (int nodeIt = 0; nodeIt < lists[lists.length-2].length; nodeIt++){
                lists[lists.length-2][nodeIt].bias -= dw*error/lists[lists.length-2].length;
            }
        }
        public void disp(){
            for (int b = 0; b < desiredOutput[0].length; b++){
                System.out.println("DATE: " + inputLists[1][b] + " / " + inputLists[0][b]);
                for (int i = 0; i < desiredOutput.length; i++){
                    long transfer = (long) this.storage[i][b];
                    System.out.println("VAL:   \t" + desiredOutput[i][b]
                            + "\tOUTPUT:\t" + transfer + ((desiredOutput[i][b] == transfer) ? "\tCHK" : ""));
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    public static void runLvl6(){
        DATAII.toArray();
        double prevError = 0;
        HashSet<Double> memory = new HashSet<>();
        long[][] totalOutputs = DATAII.vals;
        double exitAccuracy = 500;
        while (true){

            ReLuStructure network = new ReLuStructure(1,
                    new int[]{128},
                    new long[][]{DATAII.days, DATAII.months,
                        DATAII.shiftVals[0], DATAII.shiftVals[1],
                        DATAII.shiftVals[2], DATAII.shiftVals[3],
                        DATAII.shiftVals[4], DATAII.shiftVals[5]},
                    totalOutputs, 6, true, false);
            network.updateWeights();
            
            
            int times = Integer.MAX_VALUE;
            while (times-- > 0){
                prevError = network.error;
                network.reset();
                
                for (int b = 0; b < DATAII.days.length; b++){
                    network.input(new long[]{DATAII.days[b], DATAII.months[b], 
                        DATAII.shiftVals[0][b], DATAII.shiftVals[1][b],
                        DATAII.shiftVals[2][b], DATAII.shiftVals[3][b],
                        DATAII.shiftVals[4][b], DATAII.shiftVals[5][b]});
                    network.calc(b);
                }
                network.updateWeights();
                
                network.dw = 1.0/(10000.0+10000.0*Math.pow(2.71, -Math.abs(prevError-network.error)));
                
                //System.out.println(network.error+"\t"+prevError+"\t"+network.dw);
                
                if (Math.abs(network.error) < exitAccuracy) break;
                if (!memory.add(network.error)) break;
                //System.out.println("Error\t"+network.error);
            }
            memory.clear();
            if (Math.abs(network.error) < exitAccuracy){
                System.out.println("Success");
                System.out.println(network.error);
                System.out.println(network.dw);
                System.out.println(times);
                network.dispWeights();
                network.disp();
                break;
            }
            else {
                
                //network.disp();
                System.out.println("Fail");
               // network.disp();
                System.out.println("Error\t" + network.error);
                System.out.println("DW\t"+network.dw);
                System.out.println();
            }
        }
    }

    public static void runLvl5(){
        DATAII.toArray();
        double prevError = 0;
        double prevInternalError;
        long[][] totalOutputs = DATAII.vals;
        while (true){
            /*
            The input->16384 step allows pattern recognition, while
            the 16384->24 step forces output to be in ballpark (reduces outliers
            like negative numbers or 100s place numbers)
            
            For this data set 3 layers seems to always lead to overtraining.
            */
            ReLuStructure network = new ReLuStructure(2, new int[]{(int)Math.pow(2, 16), 24}, new long[][]{DATAII.days, DATAII.months}, totalOutputs, 6, false, false);
            int times = 10000;
            prevInternalError = 0;
            
            while (times-- > 0){
                network.reset();
                for (int b = 0; b < DATAII.days.length; b++){
                    network.input(new long[]{DATAII.days[b], DATAII.months[b]});
                    network.calc(b);
                }
                network.updateWeights();

                network.dw = network.dw * 0.999;
                if (prevInternalError != network.error) prevInternalError = network.error;
                else break;
            }
            if (Math.abs(network.error) < 1.2E-14){
                System.out.println("Success");
                System.out.println(network.error);
                System.out.println(network.dw);
                System.out.println(times);
                network.disp();
                break;
            }
            else {
                
                //network.disp();
                System.out.println("Fail");
                System.out.println("Error\t" + network.error);
                System.out.println("DW\t"+network.dw);
                System.out.println();
            }
        }
    }
    public static void runLvl4(){
        ReLuStructure network = new ReLuStructure(1, new int[]{ 32}, new long[][]{DATA.dates, DATA.months}, new long[][]{DATA.test});
        int times = 100;
        while (times-- > 0){
            network.reset();
            for (int b = 0; b < DATA.test.length; b++){
                network.input(new long[]{DATA.dates[b], DATA.months[b]});
                network.calc(b);
            }
            network.adjustBias();
        }
        times = 10000;
        while (times-- > 0){
            network.reset();
            for (int b = 0; b < DATA.test.length; b++){
                network.input(new long[]{DATA.dates[b], DATA.months[b]});
                network.calc(b);
            }
            network.updateWeights();
            
            System.out.println(network.dw);
            System.out.println(times);
            network.disp();
            System.out.println();
            System.out.println();
            network.dw = network.dw * 0.999;
        }
    }
    public static void runLvl3ReLuPrevInp(){
        ReLuNode[] outputLayer = new ReLuNode[1];
        outputLayer[0] = new ReLuNode(new ReLuNode[1]);
        outputLayer[0].bias = Double.MAX_VALUE;
        
        ReLuNode[] hiddenLayer2 = new ReLuNode[256];
        for (int i = 0; i < hiddenLayer2.length; i++){
            hiddenLayer2[i] = new ReLuNode(outputLayer);
        }
        
        ReLuNode[] hiddenLayer = new ReLuNode[256];
        for (int i = 0; i < hiddenLayer.length; i++){
            hiddenLayer[i] = new ReLuNode(hiddenLayer2);
        }
        
        ReLuNode[] inputLayer = new ReLuNode[3];
        inputLayer[0] = new ReLuNode(hiddenLayer);
        inputLayer[1] = new ReLuNode(hiddenLayer);
        inputLayer[2] = new ReLuNode(hiddenLayer);
        
        inputLayer[0].currentInput =DATA.dates[1];
        inputLayer[1].currentInput =DATA.months[1];
        inputLayer[2].currentInput =DATA.test[0];
        
        int times = 100000;

        while (times-- != 0){
            double error = 0;
            for (int b = 1; b < DATA.test.length; b++){
                //System.out.println("B " +b);
                //reset hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].reset();
                }
                //reset hidden2
                for (int i = 0; i < hiddenLayer2.length; i++){
                    hiddenLayer2[i].reset();
                }
                //reset output
                for (int i = 0; i < outputLayer.length; i++){
                    outputLayer[i].reset();
                }
                inputLayer[0].currentInput = DATA.dates[b];
                inputLayer[1].currentInput = DATA.months[b];
                inputLayer[2].currentInput = DATA.test[b-1];
                //pass from input
                for (int i = 0; i < inputLayer.length; i++){
                    inputLayer[i].pass();
                }
                //pass from hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].pass();
                }
                //pass from hidden2
                for (int i = 0; i < hiddenLayer2.length; i++){
                    hiddenLayer2[i].pass();
                }
                //Calculate error
                error += 0.5*(outputLayer[0].currentInput-DATA.test[b]);
                //System.out.println("ERROR Total:  " + error);
                //System.out.println("ERROR Current:" + 0.5*(outputLayer[0].currentInput-DATA.test[b]));
                //System.out.println("ERROR Value:  " + outputLayer[0].currentInput);
                //System.out.println("ERROR Value2:  " + DATA.test[b]);
                
                
                //inputLayer[0].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(hiddenLayer[0].currentInput);
                //inputLayer[1].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(hiddenLayer[1].currentInput);
                
                /*for (int w = 0; w < hiddenLayer.length; w++){
                    if (inputLayer[0].weights[w] < 0) inputLayer[0].weights[w] = 0;
                    if (inputLayer[1].weights[w] < 0) inputLayer[1].weights[w] = 0;
                    if (inputLayer[0].weights[w] > 1) inputLayer[0].weights[w] = 1;
                    if (inputLayer[1].weights[w] > 1) inputLayer[1].weights[w] = 1;
                    if (hiddenLayer[w].weights[0] < 0) hiddenLayer[w].weights[0] = 0;
                    if (hiddenLayer[w].weights[0] > 1) hiddenLayer[w].weights[0] = 1;
                }*/
                
                /*if (hiddenLayer[0].weights[0] < 0) hiddenLayer[0].weights[0] = 0;
                if (hiddenLayer[1].weights[0] < 0) hiddenLayer[1].weights[0] = 0;
                
                if (hiddenLayer[0].weights[0] > 1) hiddenLayer[0].weights[0] = 1;
                if (hiddenLayer[1].weights[0] > 1) hiddenLayer[1].weights[0] = 1;*/
                long transfer = (long) outputLayer[0].currentInput;
                
                if (times%100==0) System.out.println("Real:" + DATA.test[b] + "\tVal: " + transfer + "\tDif: " + (DATA.test[b]-transfer) + "\tRun: " + times);
                ////System.out.println("Err: " + 0.5*Math.pow((outputLayer[0].getRes()-DATA.test[0]), 2));
                
            }
            
            //adjust weights
            for (int nodeIt = 0; nodeIt < inputLayer.length; nodeIt++){
                for (int weightIt = 0; weightIt < inputLayer[nodeIt].weights.length; weightIt++){
                    //System.out.println(inputLayer[nodeIt].weights[weightIt]);
                    hiddenLayer[weightIt].resetForward();
                    hiddenLayer[weightIt].deltafeed = Math.max(0, inputLayer[nodeIt].currentInput);
                    hiddenLayer[weightIt].feedForward();
                    //System.out.println("Output Delta: " + outputLayer[0].deltafeed);
                    //System.out.println(inputLayer[nodeIt].weights[weightIt]);
                    inputLayer[nodeIt].weights[weightIt] -= 0.00000000001*error*outputLayer[0].deltafeed;
                    //System.out.println(inputLayer[nodeIt].weights[weightIt] + "\n");
                    
                    if (inputLayer[nodeIt].weights[weightIt] < 0) inputLayer[nodeIt].weights[weightIt] = 0;
                    if (inputLayer[nodeIt].weights[weightIt] > 1) inputLayer[nodeIt].weights[weightIt] = 1;
                }
            }
            
            //adjust weights II
            for (int nodeIt = 0; nodeIt < hiddenLayer.length; nodeIt++){
                for (int weightIt = 0; weightIt < hiddenLayer[nodeIt].weights.length; weightIt++){
                    hiddenLayer2[weightIt].resetForward();
                    hiddenLayer2[weightIt].deltafeed = Math.max(0, hiddenLayer[nodeIt].currentInput);
                    hiddenLayer2[weightIt].feedForward();
                    //System.out.println(outputLayer[0].deltafeed);
                    //System.out.println(hiddenLayer[nodeIt].weights[weightIt]);
                    hiddenLayer[nodeIt].weights[weightIt] -= 0.00000000001*error*outputLayer[0].deltafeed;
                    //System.out.println(hiddenLayer[nodeIt].weights[weightIt] + "\n");
                    
                    if (hiddenLayer[nodeIt].weights[weightIt] < 0) hiddenLayer[nodeIt].weights[weightIt] = 0;
                    if (hiddenLayer[nodeIt].weights[weightIt] > 1) hiddenLayer[nodeIt].weights[weightIt] = 1;
                }
            }
            //adjust weights III
            for (int nodeIt = 0; nodeIt < hiddenLayer2.length; nodeIt++){
                for (int weightIt = 0; weightIt < hiddenLayer2[nodeIt].weights.length; weightIt++){
                    //System.out.println(error);
                    //System.out.println(sigm(hiddenLayer2[nodeIt].currentInput));
                    //System.out.println(hiddenLayer2[nodeIt].weights[weightIt]);
                    hiddenLayer2[nodeIt].weights[weightIt] -= 0.00000000001*error*Math.max(0, hiddenLayer2[nodeIt].currentInput);
                    //System.out.println(hiddenLayer2[nodeIt].weights[weightIt] + "\n");
                    
                    if (hiddenLayer2[nodeIt].weights[weightIt] < 0) hiddenLayer2[nodeIt].weights[weightIt] = 0;
                    if (hiddenLayer2[nodeIt].weights[weightIt] > 1) hiddenLayer2[nodeIt].weights[weightIt] = 1;
                }
            }
            
            
            
            
            if (times%100==0) System.out.println();
        }
        
    }
    public static void runLvl3ReLu(){
        ReLuNode[] outputLayer = new ReLuNode[1];
        outputLayer[0] = new ReLuNode(new ReLuNode[1]);
        outputLayer[0].bias = Double.MAX_VALUE;
        
        ReLuNode[] hiddenLayer2 = new ReLuNode[64];
        for (int i = 0; i < hiddenLayer2.length; i++){
            hiddenLayer2[i] = new ReLuNode(outputLayer);
        }
        
        ReLuNode[] hiddenLayer = new ReLuNode[64];
        for (int i = 0; i < hiddenLayer.length; i++){
            hiddenLayer[i] = new ReLuNode(hiddenLayer2);
        }
        
        ReLuNode[] inputLayer = new ReLuNode[2];
        inputLayer[0] = new ReLuNode(hiddenLayer);
        inputLayer[1] = new ReLuNode(hiddenLayer);
        
        inputLayer[0].currentInput =DATA.dates[0];
        inputLayer[1].currentInput =DATA.months[0];
        
        int times = 100000;

        while (times-- != 0){
            double error = 0;
            for (int b = 0; b < DATA.test.length; b++){
                //System.out.println("B " +b);
                //reset hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].reset();
                }
                //reset hidden2
                for (int i = 0; i < hiddenLayer2.length; i++){
                    hiddenLayer2[i].reset();
                }
                //reset output
                for (int i = 0; i < outputLayer.length; i++){
                    outputLayer[i].reset();
                }
                inputLayer[0].currentInput = DATA.dates[b];
                inputLayer[1].currentInput = DATA.months[b];
                //pass from input
                for (int i = 0; i < inputLayer.length; i++){
                    inputLayer[i].pass();
                }
                //pass from hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].pass();
                }
                //pass from hidden2
                for (int i = 0; i < hiddenLayer2.length; i++){
                    hiddenLayer2[i].pass();
                }
                //Calculate error
                error += 0.5*(outputLayer[0].currentInput-DATA.test[b]);
                //System.out.println("ERROR Total:  " + error);
                //System.out.println("ERROR Current:" + 0.5*(outputLayer[0].currentInput-DATA.test[b]));
                //System.out.println("ERROR Value:  " + outputLayer[0].currentInput);
                //System.out.println("ERROR Value2:  " + DATA.test[b]);
                
                
                //inputLayer[0].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(hiddenLayer[0].currentInput);
                //inputLayer[1].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(hiddenLayer[1].currentInput);
                
                /*for (int w = 0; w < hiddenLayer.length; w++){
                    if (inputLayer[0].weights[w] < 0) inputLayer[0].weights[w] = 0;
                    if (inputLayer[1].weights[w] < 0) inputLayer[1].weights[w] = 0;
                    if (inputLayer[0].weights[w] > 1) inputLayer[0].weights[w] = 1;
                    if (inputLayer[1].weights[w] > 1) inputLayer[1].weights[w] = 1;
                    if (hiddenLayer[w].weights[0] < 0) hiddenLayer[w].weights[0] = 0;
                    if (hiddenLayer[w].weights[0] > 1) hiddenLayer[w].weights[0] = 1;
                }*/
                
                /*if (hiddenLayer[0].weights[0] < 0) hiddenLayer[0].weights[0] = 0;
                if (hiddenLayer[1].weights[0] < 0) hiddenLayer[1].weights[0] = 0;
                
                if (hiddenLayer[0].weights[0] > 1) hiddenLayer[0].weights[0] = 1;
                if (hiddenLayer[1].weights[0] > 1) hiddenLayer[1].weights[0] = 1;*/
                long transfer = (long) outputLayer[0].currentInput;
                
                if (times%100==0) System.out.println("Real:" + DATA.test[b] + "\tVal: " + transfer + "\tDif: " + (DATA.test[b]-transfer) + "\tRun: " + times);
                ////System.out.println("Err: " + 0.5*Math.pow((outputLayer[0].getRes()-DATA.test[0]), 2));
                
            }
            
            //adjust weights
            for (int nodeIt = 0; nodeIt < inputLayer.length; nodeIt++){
                for (int weightIt = 0; weightIt < inputLayer[nodeIt].weights.length; weightIt++){
                    //System.out.println(inputLayer[nodeIt].weights[weightIt]);
                    hiddenLayer[weightIt].resetForward();
                    hiddenLayer[weightIt].deltafeed = Math.max(0, inputLayer[nodeIt].currentInput);
                    hiddenLayer[weightIt].feedForward();
                    //System.out.println("Output Delta: " + outputLayer[0].deltafeed);
                    //System.out.println(inputLayer[nodeIt].weights[weightIt]);
                    inputLayer[nodeIt].weights[weightIt] -= 0.00000001*error*outputLayer[0].deltafeed;
                    //System.out.println(inputLayer[nodeIt].weights[weightIt] + "\n");
                    
                    if (inputLayer[nodeIt].weights[weightIt] < 0) inputLayer[nodeIt].weights[weightIt] = 0;
                    if (inputLayer[nodeIt].weights[weightIt] > 1) inputLayer[nodeIt].weights[weightIt] = 1;
                }
            }
            
            //adjust weights II
            for (int nodeIt = 0; nodeIt < hiddenLayer.length; nodeIt++){
                for (int weightIt = 0; weightIt < hiddenLayer[nodeIt].weights.length; weightIt++){
                    hiddenLayer2[weightIt].resetForward();
                    hiddenLayer2[weightIt].deltafeed = Math.max(0, hiddenLayer[nodeIt].currentInput);
                    hiddenLayer2[weightIt].feedForward();
                    //System.out.println(outputLayer[0].deltafeed);
                    //System.out.println(hiddenLayer[nodeIt].weights[weightIt]);
                    hiddenLayer[nodeIt].weights[weightIt] -= 0.00000001*error*outputLayer[0].deltafeed;
                    //System.out.println(hiddenLayer[nodeIt].weights[weightIt] + "\n");
                    
                    if (hiddenLayer[nodeIt].weights[weightIt] < 0) hiddenLayer[nodeIt].weights[weightIt] = 0;
                    if (hiddenLayer[nodeIt].weights[weightIt] > 1) hiddenLayer[nodeIt].weights[weightIt] = 1;
                }
            }
            //adjust weights III
            for (int nodeIt = 0; nodeIt < hiddenLayer2.length; nodeIt++){
                for (int weightIt = 0; weightIt < hiddenLayer2[nodeIt].weights.length; weightIt++){
                    //System.out.println(error);
                    //System.out.println(sigm(hiddenLayer2[nodeIt].currentInput));
                    //System.out.println(hiddenLayer2[nodeIt].weights[weightIt]);
                    hiddenLayer2[nodeIt].weights[weightIt] -= 0.00000001*error*Math.max(0, hiddenLayer2[nodeIt].currentInput);
                    //System.out.println(hiddenLayer2[nodeIt].weights[weightIt] + "\n");
                    
                    if (hiddenLayer2[nodeIt].weights[weightIt] < 0) hiddenLayer2[nodeIt].weights[weightIt] = 0;
                    if (hiddenLayer2[nodeIt].weights[weightIt] > 1) hiddenLayer2[nodeIt].weights[weightIt] = 1;
                }
            }
            
            
            
            if (times%100==0) System.out.println();
        }
        
    }
    public static void runLvl3(){
        SigmNode[] outputLayer = new SigmNode[1];
        outputLayer[0] = new SigmNode(new SigmNode[1]);
        outputLayer[0].bias = Double.MAX_VALUE;
        
        SigmNode[] hiddenLayer2 = new SigmNode[32];
        for (int i = 0; i < hiddenLayer2.length; i++){
            hiddenLayer2[i] = new SigmNode(outputLayer);
        }
        
        SigmNode[] hiddenLayer = new SigmNode[16];
        for (int i = 0; i < hiddenLayer.length; i++){
            hiddenLayer[i] = new SigmNode(hiddenLayer2);
        }
        
        SigmNode[] inputLayer = new SigmNode[2];
        inputLayer[0] = new SigmNode(hiddenLayer);
        inputLayer[1] = new SigmNode(hiddenLayer);
        
        inputLayer[0].currentInput =DATA.dates[0];
        inputLayer[1].currentInput =DATA.months[0];
        
        int times = 100000;

        while (times-- != 0){
            double error = 0;
            for (int b = 0; b < DATA.test.length; b++){
                //System.out.println("B " +b);
                //reset hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].reset();
                }
                //reset hidden2
                for (int i = 0; i < hiddenLayer2.length; i++){
                    hiddenLayer2[i].reset();
                }
                //reset output
                for (int i = 0; i < outputLayer.length; i++){
                    outputLayer[i].reset();
                }
                inputLayer[0].currentInput = DATA.dates[b];
                inputLayer[1].currentInput = DATA.months[b];
                //pass from input
                for (int i = 0; i < inputLayer.length; i++){
                    inputLayer[i].pass();
                }
                //pass from hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].pass();
                }
                //pass from hidden2
                for (int i = 0; i < hiddenLayer2.length; i++){
                    hiddenLayer2[i].pass();
                }
                //Calculate error
                error += 0.5*(outputLayer[0].currentInput-DATA.test[b]);
                //System.out.println("ERROR Total:  " + error);
                //System.out.println("ERROR Current:" + 0.5*(outputLayer[0].currentInput-DATA.test[b]));
                //System.out.println("ERROR Value:  " + outputLayer[0].currentInput);
                //System.out.println("ERROR Value2:  " + DATA.test[b]);
                
                
                //inputLayer[0].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(hiddenLayer[0].currentInput);
                //inputLayer[1].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(hiddenLayer[1].currentInput);
                
                /*for (int w = 0; w < hiddenLayer.length; w++){
                    if (inputLayer[0].weights[w] < 0) inputLayer[0].weights[w] = 0;
                    if (inputLayer[1].weights[w] < 0) inputLayer[1].weights[w] = 0;
                    if (inputLayer[0].weights[w] > 1) inputLayer[0].weights[w] = 1;
                    if (inputLayer[1].weights[w] > 1) inputLayer[1].weights[w] = 1;
                    if (hiddenLayer[w].weights[0] < 0) hiddenLayer[w].weights[0] = 0;
                    if (hiddenLayer[w].weights[0] > 1) hiddenLayer[w].weights[0] = 1;
                }*/
                
                /*if (hiddenLayer[0].weights[0] < 0) hiddenLayer[0].weights[0] = 0;
                if (hiddenLayer[1].weights[0] < 0) hiddenLayer[1].weights[0] = 0;
                
                if (hiddenLayer[0].weights[0] > 1) hiddenLayer[0].weights[0] = 1;
                if (hiddenLayer[1].weights[0] > 1) hiddenLayer[1].weights[0] = 1;*/
                long transfer = (long) outputLayer[0].currentInput;
                
                if (times%100==0) System.out.println("Real:" + DATA.test[b] + "\tVal: " + transfer + "\tDif: " + (DATA.test[b]-transfer) + "\tRun: " + times);
                ////System.out.println("Err: " + 0.5*Math.pow((outputLayer[0].getRes()-DATA.test[0]), 2));
                
            }
            
            //adjust weights
            for (int nodeIt = 0; nodeIt < inputLayer.length; nodeIt++){
                for (int weightIt = 0; weightIt < inputLayer[nodeIt].weights.length; weightIt++){
                    hiddenLayer[weightIt].resetForward();
                    hiddenLayer[weightIt].deltafeed = sigm(inputLayer[nodeIt].currentInput);
                    hiddenLayer[weightIt].feedForward();
                    //System.out.println("Output Delta: " + outputLayer[0].deltafeed);
                    //System.out.println(inputLayer[nodeIt].weights[weightIt]);
                    inputLayer[nodeIt].weights[weightIt] -= 0.00001*error*outputLayer[0].deltafeed;
                    //System.out.println(inputLayer[nodeIt].weights[weightIt] + "\n");
                    
                    if (inputLayer[nodeIt].weights[weightIt] < 0) inputLayer[nodeIt].weights[weightIt] = 0;
                    if (inputLayer[nodeIt].weights[weightIt] > 1) inputLayer[nodeIt].weights[weightIt] = 1;
                }
            }
            
            //adjust weights II
            for (int nodeIt = 0; nodeIt < hiddenLayer.length; nodeIt++){
                for (int weightIt = 0; weightIt < hiddenLayer[nodeIt].weights.length; weightIt++){
                    hiddenLayer2[weightIt].resetForward();
                    hiddenLayer2[weightIt].deltafeed = sigm(hiddenLayer[nodeIt].currentInput);
                    hiddenLayer2[weightIt].feedForward();
                    //System.out.println(outputLayer[0].deltafeed);
                    //System.out.println(hiddenLayer[nodeIt].weights[weightIt]);
                    hiddenLayer[nodeIt].weights[weightIt] -= 0.00001*error*outputLayer[0].deltafeed;
                    //System.out.println(hiddenLayer[nodeIt].weights[weightIt] + "\n");
                    
                    if (hiddenLayer[nodeIt].weights[weightIt] < 0) hiddenLayer[nodeIt].weights[weightIt] = 0;
                    if (hiddenLayer[nodeIt].weights[weightIt] > 1) hiddenLayer[nodeIt].weights[weightIt] = 1;
                }
            }
            //adjust weights III
            for (int nodeIt = 0; nodeIt < hiddenLayer2.length; nodeIt++){
                for (int weightIt = 0; weightIt < hiddenLayer2[nodeIt].weights.length; weightIt++){
                    //System.out.println(error);
                    //System.out.println(sigm(hiddenLayer2[nodeIt].currentInput));
                    //System.out.println(hiddenLayer2[nodeIt].weights[weightIt]);
                    hiddenLayer2[nodeIt].weights[weightIt] -= 0.00001*error*sigm(hiddenLayer2[nodeIt].currentInput);
                    //System.out.println(hiddenLayer2[nodeIt].weights[weightIt] + "\n");
                    
                    if (hiddenLayer2[nodeIt].weights[weightIt] < 0) hiddenLayer2[nodeIt].weights[weightIt] = 0;
                    if (hiddenLayer2[nodeIt].weights[weightIt] > 1) hiddenLayer2[nodeIt].weights[weightIt] = 1;
                }
            }
            
            
            
            if (times%100==0) System.out.println();
        }
        
    }
    public static void runLvl2(){
        SigmNode[] outputLayer = new SigmNode[1];
        outputLayer[0] = new SigmNode(new SigmNode[1]);
        outputLayer[0].bias = Double.MAX_VALUE;
        
        SigmNode[] hiddenLayer = new SigmNode[64];
        for (int i = 0; i < hiddenLayer.length; i++){
            hiddenLayer[i] = new SigmNode(outputLayer);
        }
        
        
        SigmNode[] inputLayer = new SigmNode[2];
        inputLayer[0] = new SigmNode(hiddenLayer);
        inputLayer[1] = new SigmNode(hiddenLayer);
        
        inputLayer[0].currentInput =DATA.dates[0];
        inputLayer[1].currentInput =DATA.months[0];
        
        int times = 100000;
        double tester = 0;
        int timesSame = 0;
        while (times-- != 0){
            for (int b = 0; b < DATA.test.length; b++){
                //reset hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].reset();
                }
                //reset output
                for (int i = 0; i < outputLayer.length; i++){
                    outputLayer[i].reset();
                }
                inputLayer[0].currentInput = DATA.dates[b];
                inputLayer[1].currentInput = DATA.months[b];
                //pass from input
                for (int i = 0; i < inputLayer.length; i++){
                    inputLayer[i].pass();
                }
                //pass from hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].pass();
                }
                //adjust weights
                
                
                
                for (int w = 0; w < hiddenLayer.length; w++){
                    inputLayer[0].weights[w] -= 0.000001*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[w].weights[0]*(deltasigm(hiddenLayer[w].currentInput))*sigm(inputLayer[0].currentInput);
                    inputLayer[1].weights[w] -= 0.000001*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[w].weights[0]*(deltasigm(hiddenLayer[w].currentInput))*sigm(inputLayer[1].currentInput);
                    hiddenLayer[w].weights[0] -=0.000001*(outputLayer[0].getRes()-DATA.test[b])*(1/(Math.pow(Math.E, -hiddenLayer[w].currentInput)));//change of error with respect to weight
                    //hiddenLayer[w].bias -= 0.001*(outputLayer[0].getRes()-DATA.test[b]);//change of error with respect to bias
                }
                //inputLayer[0].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(hiddenLayer[0].currentInput);
                //inputLayer[1].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(hiddenLayer[1].currentInput);
                
                for (int w = 0; w < hiddenLayer.length; w++){
                    if (inputLayer[0].weights[w] < 0) inputLayer[0].weights[w] = 0;
                    if (inputLayer[1].weights[w] < 0) inputLayer[1].weights[w] = 0;
                    if (inputLayer[0].weights[w] > 1) inputLayer[0].weights[w] = 1;
                    if (inputLayer[1].weights[w] > 1) inputLayer[1].weights[w] = 1;
                    if (hiddenLayer[w].weights[0] < 0) hiddenLayer[w].weights[0] = 0;
                    if (hiddenLayer[w].weights[0] > 1) hiddenLayer[w].weights[0] = 1;
                }
                
                /*if (hiddenLayer[0].weights[0] < 0) hiddenLayer[0].weights[0] = 0;
                if (hiddenLayer[1].weights[0] < 0) hiddenLayer[1].weights[0] = 0;
                
                if (hiddenLayer[0].weights[0] > 1) hiddenLayer[0].weights[0] = 1;
                if (hiddenLayer[1].weights[0] > 1) hiddenLayer[1].weights[0] = 1;*/
                long transfer = (long) outputLayer[0].currentInput;
                
                if (times%100==0) System.out.println("Real:" + DATA.test[b] + "\tVal: " + transfer + "\tDif: " + (DATA.test[b]-transfer) + "\tRun: " + times);
                //System.out.println("Err: " + 0.5*Math.pow((outputLayer[0].getRes()-DATA.test[0]), 2));
                
            }
            if (times%100==0) System.out.println();
            if (tester == outputLayer[0].currentInput) break;
            else tester = outputLayer[0].currentInput;
        }
    }
    public static void runLvl1(){
        SigmNode[] outputLayer = new SigmNode[1];
        outputLayer[0] = new SigmNode(new SigmNode[1]);
        outputLayer[0].bias = Double.MAX_VALUE;
        
        SigmNode[] hiddenLayer = new SigmNode[2];
        hiddenLayer[0] = new SigmNode(outputLayer);
        hiddenLayer[1] = new SigmNode(outputLayer);
        
        SigmNode[] inputLayer = new SigmNode[2];
        inputLayer[0] = new SigmNode(hiddenLayer);
        inputLayer[1] = new SigmNode(hiddenLayer);
        
        inputLayer[0].currentInput =DATA.dates[0];
        inputLayer[1].currentInput =DATA.months[0];
        
        while (true){
            for (int b = 0; b < DATA.test.length; b++){
                //reset hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].reset();
                }
                //reset output
                for (int i = 0; i < outputLayer.length; i++){
                    outputLayer[i].reset();
                }
                inputLayer[0].currentInput = DATA.dates[b];
                inputLayer[1].currentInput = DATA.months[b];
                //pass from input
                for (int i = 0; i < inputLayer.length; i++){
                    inputLayer[i].pass();
                }
                //pass from hidden
                for (int i = 0; i < hiddenLayer.length; i++){
                    hiddenLayer[i].pass();
                }
                //adjust weights
                inputLayer[0].weights[0] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[0].weights[0]*(deltasigm(hiddenLayer[0].currentInput))*sigm(inputLayer[0].currentInput);
                inputLayer[1].weights[0] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[0].weights[0]*(deltasigm(hiddenLayer[0].currentInput))*sigm(inputLayer[1].currentInput);
                inputLayer[0].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(inputLayer[0].currentInput);
                inputLayer[1].weights[1] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*hiddenLayer[1].weights[0]*(deltasigm(hiddenLayer[1].currentInput))*sigm(inputLayer[1].currentInput);
                hiddenLayer[0].weights[0] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*(1/(Math.pow(Math.E, -hiddenLayer[0].currentInput)));//change of error with respect to weight
                hiddenLayer[1].weights[0] -= 0.01*(outputLayer[0].getRes()-DATA.test[b])*(1/(Math.pow(Math.E, -hiddenLayer[1].currentInput)));
                hiddenLayer[0].bias -= 0.01*(outputLayer[0].getRes()-DATA.test[b]);//change of error with respect to bias
                hiddenLayer[1].bias -= 0.01*(outputLayer[0].getRes()-DATA.test[b]);

                if (inputLayer[0].weights[0] < 0) inputLayer[0].weights[0] = 0;
                if (inputLayer[1].weights[0] < 0) inputLayer[1].weights[0] = 0;
                if (inputLayer[0].weights[1] < 0) inputLayer[0].weights[1] = 0;
                if (inputLayer[1].weights[1] < 0) inputLayer[1].weights[1] = 0;
                if (hiddenLayer[0].weights[0] < 0) hiddenLayer[0].weights[0] = 0;
                if (hiddenLayer[1].weights[0] < 0) hiddenLayer[1].weights[0] = 0;
                if (inputLayer[0].weights[0] > 1) inputLayer[0].weights[0] = 1;
                if (inputLayer[1].weights[0] > 1) inputLayer[1].weights[0] = 1;
                if (inputLayer[0].weights[1] > 1) inputLayer[0].weights[1] = 1;
                if (inputLayer[1].weights[1] > 1) inputLayer[1].weights[1] = 1;
                if (hiddenLayer[0].weights[0] > 1) hiddenLayer[0].weights[0] = 1;
                if (hiddenLayer[1].weights[0] > 1) hiddenLayer[1].weights[0] = 1;
                long transfer = (long) outputLayer[0].currentInput;
                
                System.out.println("Real:" + DATA.test[b]);
                System.out.println("Val: " + transfer);
                System.out.println("Err: " + 0.5*Math.pow((outputLayer[0].getRes()-DATA.test[0]), 2));
                
                System.out.println();
            }
        }
    }
    public static double sigm(double d){
        return 1/(1+Math.pow(Math.E, -d));
    }
    public static double deltasigm(double d){
        return (Math.pow(Math.E, -d)/Math.pow((1+Math.pow(Math.E, -d)), 2));
    }
    public static void runBasic(){
        SigmNode[] outputLayer = new SigmNode[1];
        outputLayer[0] = new SigmNode(new SigmNode[1]);
        outputLayer[0].bias = Double.MAX_VALUE;
        
        SigmNode inp = new SigmNode(outputLayer);
        SigmNode inp2= new SigmNode(outputLayer);
        
        
        
        while (true){
            for (int i = 0; i < 2; i++){
                inp.currentInput = DATA.dates[i];
                inp2.currentInput= DATA.months[i];
                outputLayer[0].reset();
                inp.pass();
                inp2.pass();
                long transfer = (long) outputLayer[0].getRes();
                System.out.println("guess\t"+transfer);
                System.out.println("real \t"+DATA.test[i]);
                
                inp.weights[0] -= 0.01*(outputLayer[0].getRes()-DATA.test[i])*(1/(Math.pow(Math.E, -inp.currentInput)));//change of error with respect to weight
                inp2.weights[0] -= 0.01*(outputLayer[0].getRes()-DATA.test[i])*(1/(Math.pow(Math.E, -inp2.currentInput)));
                inp.bias -= 0.01*(outputLayer[0].getRes()-DATA.test[i]);//change of error with respect to bias
                inp2.bias -= 0.01*(outputLayer[0].getRes()-DATA.test[i]);
                
                if (inp.weights[0] < 0) inp.weights[0] = 0;
                if (inp.weights[0] > 1) inp.weights[0] = 1;
                if (inp2.weights[0] < 0) inp2.weights[0] = 0;
                if (inp2.weights[0] > 1) inp2.weights[0] = 1;
            }
            
            
            
        }
        
    }
    public static void run(){
        SigmNode[] hiddenLayer = new SigmNode[4];
        SigmNode[] outputLayer = new SigmNode[8];/*
        Doesn't need to be exponential, but little reason for it not to be.
        */
        SigmNode inp = new SigmNode(hiddenLayer);
        SigmNode inp2 = new SigmNode(hiddenLayer);
        
        for (int i = 0; i < hiddenLayer.length; i++){
            hiddenLayer[i] = new SigmNode(outputLayer);
        }
        
        SigmNode[] fakeOutputs = new SigmNode[8];
        
        for (int i = 0; i < outputLayer.length; i++){
            outputLayer[i] = new SigmNode(fakeOutputs);
        }
        
        /*
        inp.disp();
        inp2.disp();
        System.out.println();
        for (Node n : hiddenLayer){
            n.disp();
        }
        System.out.println();
        for (Node n : outputLayer){
            n.disp();
        }
        */
        
        inp.currentInput = DATA.dates[0];
        inp2.currentInput= DATA.months[0];
        for (SigmNode n : hiddenLayer){
            n.reset();
        }
        inp.pass();
        inp2.pass();
        //System.out.println();
        
        for (SigmNode n : hiddenLayer){
            n.pass();
        }
        //System.out.println();
        
        double total = 0;
        for (SigmNode n : outputLayer){
            total += n.currentInput*Math.pow(n.bias, 10);
        }
        long transfer = (long) total;
        System.out.println(transfer);
    }
}
