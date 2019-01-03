/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

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
        public double[] storage;
        public long[] input;
        public double dw;
        public ReLuStructure(int layers, int[] layersSizes, int inputs){//layers not including output
            dw = 1/ Math.pow(10, (DATA.test[0]+"").length());
            
            
            this.lists = new ReLuNode[layers+1][];
            this.lists[0] = new ReLuNode[inputs];//input
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
            
            this.storage = new double[DATA.test.length];
        }
        public ReLuStructure(int layers, int inputs){//layers not including output
            
            
            
            //dw = 0.00000001;//2 digits to 8 digits
            //dw = 0.000000000000000000000000000001;//Average of 10 digits to 29 digits
            //System.out.println(dw);
            //System.out.println(1/ Math.pow(10, (DATA.test[0]+"").length()*3));
            dw = 1000000000/ Math.pow(10, (DATA.test[0]+"").length());
            
            
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
            
            this.storage = new double[DATA.test.length];
        }
        public void resetLearningRate(){
            dw = 1/ Math.pow(10, (DATA.test[0]+"").length()*3);
        }
        public void reset(){
            error = 0;
        }
        public void input(long[] input){
            this.input=input;
        }
        public void calc(int b){
            for (int i = 0; i < lists[0].length; i++){
                lists[0][i].currentInput = input[i];
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
            storage[b] = lists[lists.length-1][0].currentInput;
            error += (1.0/DATA.test.length)*(lists[lists.length-1][0].currentInput - DATA.test[b]);
        }
        public void updateWeights(){
            //input
            //hidden 1
            //hidden 2
            //hidden 3...
            //hidden n-1 where n is number of layers not including output
            
            
            for (int b = 0; b < lists.length-2; b++){
                for (int nodeIt = 0; nodeIt < lists[b].length; nodeIt++){
                    for (int weightIt = 0; weightIt < lists[b][nodeIt].weights.length; weightIt++){
                        lists[b+1][weightIt].resetForward();
                        lists[b+1][weightIt].deltafeed = Math.max(0, lists[b][nodeIt].currentInput);
                        lists[b+1][weightIt].feedForward();
                        //System.out.println(((sigm(lists[lists.length-1][0].currentInput))/10000000000000.0));
                        lists[b][nodeIt].weights[weightIt] -= Math.pow(0.1, lists.length-b)*dw*error*lists[lists.length-1][0].deltafeed;
                        
                        if (lists[b][nodeIt].weights[weightIt] < -1) lists[b][nodeIt].weights[weightIt] = -1;
                        if (lists[b][nodeIt].weights[weightIt] > 1) lists[b][nodeIt].weights[weightIt] = 1;
                    }
                }
            }
            //last non-output node
            for (int nodeIt = 0; nodeIt < lists[lists.length-2].length; nodeIt++){
                for (int weightIt = 0; weightIt < lists[lists.length-2][nodeIt].weights.length; weightIt++){

                    lists[lists.length-2][nodeIt].weights[weightIt] -= dw*error*Math.max(0, lists[lists.length-2][nodeIt].currentInput);
                    
                    if (lists[lists.length-2][nodeIt].weights[weightIt] < -1) lists[lists.length-2][nodeIt].weights[weightIt] = -1;
                    if (lists[lists.length-2][nodeIt].weights[weightIt] > 1) lists[lists.length-2][nodeIt].weights[weightIt] = 1;
                }
            }
        }
        public void adjustBias(){
            //last non-output node
            for (int nodeIt = 0; nodeIt < lists[lists.length-2].length; nodeIt++){
                lists[lists.length-2][nodeIt].bias -= dw*error/lists[lists.length-2].length;
            }
        }
        public void disp(){
            for (int i = 0; i < DATA.test.length; i++){
                long transfer = (long) this.storage[i];
                System.out.println("VAL:   \t" + DATA.test[i] + "\tOUTPUT:\t" + transfer);
            }
            System.out.println();
        }
    }
    public static void runLvl4(){
        ReLuStructure network = new ReLuStructure(1, new int[]{ 32}, 2);
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
