/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author voice
 */
public class WebNetwork {
    /**
     * The issue I find with finding mathematical relations between inputs and
     * outputs is that it's not very close to how the human brain works (in most
     * situations). From self reflection, most data finding in my mind takes
     * the form of context+internal monologue+stimuli-> choosing from a set
     * of memorized contexts-> choosing from a set of memorized stimuli. To put
     * concisely, the neural network seeks to 'memorize' inputs to a theoretic-
     * ally infinite number of outputs, useful for certain contexts but not all
     * contexts. This also leads to the neural network having few limits on
     * how much space it needs, a detriment to most computing.
     * 
     * This, the web network is a specialized classifier of input-> output based
     * on neural network architecture. Why not build a decision tree? Again,
     * that's not how brains work. The human brain determines contexts by
     * comparing input to many multitudes of 'patterns' or 'classes' of objects.
     * This is coincidentally how creativity works in most contexts; attributes
     * of one class of object being combined with another class of object
     * to produce a new, novel object with possibly unforeseen features. This
     * web should only produce one answer, but will compare it to many others
     * in the way that the inputs for one answer will inevitably activate other
     * neurons, just less. If one were to introduce another network to interpret
     * the activations of the neurons in this web to something intelligible,
     * one could achieve something close to machine metacognition; the machine
     * thinking about the way the machine thinks.
     */
    public static class InputNode{
        public HashMap<WebNode, Weight> map;//WebNode, Weight
        public long currentVal;
        public InputNode(){
            map = new HashMap<>();
        }
        public void input(long val){
            currentVal = val;
        }
        public void spreadData(){
            Iterator it = map.keySet().iterator();
            WebNode temp;
            while (it.hasNext()){
                temp = ((WebNode)(it.next()));
                temp.passData((long) (currentVal*map.get(temp).d));
            }
        }
    }
    public static class WebNode{
        public HashMap<WebNode, Weight> map;
        public boolean exhau;//If neuron has passed info this run
        public boolean fired;//If neuron has exceeded its threshold this run
        public long thresh;
        public long amountActivated;
        public long value;
        
        public long nsigm(long input){
            return (long) (100.0/(1.0+Math.pow(Math.E, input)));
        }
        public WebNode(long val){
            value=val;
            exhau = false;
            fired = false;
            map = new HashMap<>();
            thresh = 0;
            amountActivated = 0;
        }
        public void reset(){
            exhau = false;
            fired = false;
            amountActivated = 0;
        }
        public boolean check(){
            if (amountActivated > thresh) fired = true;
            return fired;
        }
        public void passData(long data){
            amountActivated += data*nsigm(data);
        }
        public void spreadData(){
            if (!exhau){
                Iterator it = map.keySet().iterator();
                WebNode temp;
                while (it.hasNext()){
                    temp = (WebNode) it.next();
                    temp.passData((long) (amountActivated*((Weight)map.get(temp)).d));
                }
                exhau = true;
            }
        }
    }
    /**
     * Container class for references.
     */
    public static class Weight{
        double d;
        public Weight(double d){
            this.d=d;
        }
    }
    public static class WebStructure{
        public HashSet<Weight> weights;
        public InputNode[] inputs;
        public WebNode[] webnodes;
        public long[][] inputLists;
        public long[][] outputs;
        /**
         * Inputs: (4 nodes, 5 data points)
         * 1* 1 2 3 4 5
         * 2* 1 2 3 4 5
         * 3* 1 2 3 4 5
         * 4* 1 2 3 4 5
         * Outputs:
         * 1* 1 2 3 4 5 matched to inputs
         * @param inputLists
         * @param outputs
         */
        public WebStructure(long[][] inputLists, long[][] outputs){
            this.inputLists=inputLists;
            this.outputs=outputs;
            this.inputs=new InputNode[inputLists.length];
            
            HashSet<Long> testRepeats = new HashSet<>();
            for (int i = 0; i < outputs[0].length; i++)
                testRepeats.add(outputs[0][i]);
            
            this.webnodes=new WebNode[testRepeats.size()];
            weights = new HashSet<>();
            
            for (int i = 0; i < inputs.length; i++){
                inputs[i] = new InputNode();
            }
            Iterator it = testRepeats.iterator();
            int sent = 0;
            while (it.hasNext()){
                webnodes[sent++] = new WebNode((long) it.next());
            }
            
            
            Weight temp;
            for (WebNode node : webnodes){
                for (InputNode inode : inputs){
                    temp = new Weight((Math.random()*2.0)-1.0);
                    weights.add(temp);
                    inode.map.put(node, temp);
                }
                for (WebNode wnode : webnodes){
                    temp = new Weight((Math.random()*2.0)-1.0);
                    weights.add(temp);
                    node.map.put(wnode, temp);
                }
            }
        }
        
        public void disp(){
            for (int b = 0; b < inputLists[0].length; b++){
                for (int r = 0; r < inputLists.length; r++){
                    System.out.print(inputLists[r][b] + " ");
                }
                System.out.print(" \t"+getValue(b)+"\t"+outputs[0][b]);
                System.out.println();
            }
        }
        public int update(int total1, HashMap<Weight, Double> changes){
            // MOD // STORE //
            HashMap<Weight, Double> originalStorage = new HashMap<>();
            Iterator it;
            it = weights.iterator();
            Weight temp;
            while (it.hasNext()){
                temp = (Weight) it.next();
                originalStorage.put(temp, temp.d);
                temp.d += changes.get(temp);
            }
            
            // TEST //
            int total2 = 0;
            for (int b = 0; b < inputLists[0].length; b++){
                if (getValue(b) == outputs[0][b]) total2++;
            }
            
            // DISPLAY //
            System.out.println("\tOriginal obtained " + total1);
            System.out.println("\tModified obtained " + total2);
            System.out.println("\tModified obtained " + (total2-total1) + " more");
            
            // RESET // RETURN //
            if (total1 > total2){
                it = weights.iterator();
                while (it.hasNext()){
                    temp = (Weight) it.next();
                    temp.d = originalStorage.get(temp);
                }
                return total1;
            } else {
                return total2;
            }
        }
        public void update(){
            // TEST //
            int total1 = 0;
            for (int b = 0; b < inputLists[0].length; b++){
                if (getValue(b) == outputs[0][b]) total1++;
            }
            
            // MOD // STORE //
            HashMap<Weight, Double> originalStorage = new HashMap<>();
            HashMap<Weight, Double> changes = new HashMap<>();
            Iterator it;
            it = weights.iterator();
            Weight temp;
            double dtemp;
            while (it.hasNext()){
                temp = (Weight) it.next();
                originalStorage.put(temp, temp.d);
                dtemp = Math.random()/10000 - (0.5/10000);
                temp.d += dtemp;
                changes.put(temp, dtemp);
            }
            
            // TEST //
            int total2 = 0;
            for (int b = 0; b < inputLists[0].length; b++){
                if (getValue(b) == outputs[0][b]) total2++;
            }
            
            // DISPLAY //
            System.out.println("Original obtained " + total1);
            System.out.println("Modified obtained " + total2);
            System.out.println("Modified obtained " + (total2-total1) + " more");
            
            // RESET //
            if (total1 > total2){
                it = weights.iterator();
                while (it.hasNext()){
                    temp = (Weight) it.next();
                    temp.d = originalStorage.get(temp);
                }
            } else {
                int total3;
                // USE 'GOOD' DIRECTION
                while (true){
                    total3 = update(total2, changes);
                    if (total3 <= total2) break;
                    else {
                        total2 = total3;
                    }
                }
            }
        }
        
        public int getScore(){
            int total1 = 0;
            for (int b = 0; b < inputLists[0].length; b++){
                if (getValue(b) == outputs[0][b]) total1++;
            }
            return total1;
        }
        
        public long getValue(int b){
            
            for (int i = 0; i < webnodes.length; i++){
                webnodes[i].reset();
            }
            for (int i = 0; i < inputLists.length; i++){
                inputs[i].input(inputLists[i][b]);
                inputs[i].spreadData();
            }
            for (int i = 0; i < webnodes.length; i++){
                webnodes[i].spreadData();
            }
            
            long total = 0;
            for (WebNode wnode : webnodes){
                total += wnode.amountActivated;
            }
            WebNode max = webnodes[0];
            for (WebNode wnode : webnodes){
                wnode.thresh += (total / webnodes.length);//normalize
                wnode.check();
                if (wnode.fired){
                    if (wnode.amountActivated > max.amountActivated){
                        max = wnode;
                    }
                }
                wnode.thresh-= (total / webnodes.length);//de-normalize
            }

            return max.value;
        }
    }
    public static final int POP_SIZE = 10;
    public static void run(){
        DATAII.toArray();
        WebStructure[] population = new WebStructure[POP_SIZE];
        for (int i = 0; i < population.length; i++)
            population[i] = new WebStructure(new long[][]{DATAII.days, DATAII.months, DATAII.years}, DATAII.vals);
        
        HashMap<Integer, WebStructure> best = new HashMap<>();
        int max = 0;
        int sent = POP_SIZE/2;
        Iterator it;
        while (true){
            // GATHER SURVIVORS //
            for (int i = 0; i < population.length; i++){
                population[i].update();
                if (population[i].getScore() > max && sent-- > 0) best.put(i, population[i]);
            }
            // INTERBREED //
            for (int i = 1; i < population.length; i++){
                if (best.containsKey(i)) continue; //donn't replace best
                if (best.containsKey(i-1) && best.containsKey(i+1)){
                    Object[] arr1 = population[i-1].weights.toArray();
                    Object[] arr2 = population[i+1].weights.toArray();
                    it = population[i].weights.iterator();
                    int sent2 = 0;
                    //may or may not be in order, flaw
                    while (it.hasNext()){
                        ((Weight)it.next()).d = (((Weight)arr1[sent2]).d + ((Weight)arr2[sent2]).d)/2;
                    }
                }
                else {
                    population[i] = new WebStructure(new long[][]{DATAII.days, DATAII.months, DATAII.years}, DATAII.vals);
                }
            }
            // DISPLAY //
            for (WebStructure w : best.values()){
                System.out.println(w.getScore() + " / " + w.webnodes.length);
                if (w.getScore() >= w.webnodes.length-1){
                    w.disp();
                    break;
                }
            }
            System.out.println();
            
            // RESET //
            best.clear();
            sent = POP_SIZE / 2;
        }
        
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
}
