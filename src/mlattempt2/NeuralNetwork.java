package mlattempt2;

import java.util.Random;

public class NeuralNetwork {
    /*
     * I have not yet been instructed, nor have i yet taught myself linear alg-
     * ebra. Due to this limitation I am not going to attempt to copy the 
     * hyper-efficient algorithms that have been developed for neural networks,
     * as I will not learn anything. Instead I am going develop
     * something efficient and effective given my own limitations.
    
    EDIT: I've learned not to doubt myself as much, as I really didn't need li-
    near algebra. The derivation of how gradient descent by derivative of a 
    specific weight only seems to require knowledge up to calculus III.
     */
    
    /**
     * Code folds for large arrays are a blessing
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
    public Random r = new Random();
    
    /**
     * The node contains weights, and a value.
     */
    private class Node{
        /*public Node(Node[] prevs){
            init(prevs);
            weights = new int[2];
            weights[0] = r.nextInt(100);
            weights[1] = r.nextInt(100);
        }*/
        public Node(long inp, Node[] prevs){
            if (prevs != null){
                init(prevs);
            }
            
            weights = new double[2];
            weights[0] = r.nextInt(100);
            weights[1] = r.nextInt(100);
            this.inp=inp;
        }
        public void init(Node[] prevs){
            hasPrevs = true; 
            this.prevs=prevs;
            nexts = new Node[prevs.length*prevs.length];//assumes 2 weights per
        }
        
        public long val(int weightNum){
            return (long) (inp*weights[weightNum]/100);
        }
        public boolean hasPrevs = false;
        public long     inp;
        public Node[]   prevs;
        public Node[]   nexts;
        public double[]    weights;//Between 0 and 100, divided by 100 for math
    }
    
    public long error(long val, int c){
        System.out.println(val + " versus " + DATA.test[c] + " at " + c);
        return (long) Math.abs(val-DATA.test[c]);
    }
    
    public Node[] layerMaker(Node[] prev, Node[] current){
        int len = prev.length*prev.length;
        Node[] retVal = new Node[len];
        long val;
        int sent;
        for (int i = 0; i < len; i++){
            val = 0;
            sent = 0;
            for (int b = len/2; b != 0; b = b/2){
                val = (long) (val + prev[sent].inp*prev[sent++].weights[i/b % 2]);
            }
            retVal[i] = new Node(val, prev);
            System.arraycopy(current[i].weights, 0, retVal[i].weights, 0, current[i].weights.length);

        }
        return retVal;
    }
            
    public Node[] layerMaker(Node[] prev){
        
        //This algorithm works great
        int len = prev.length*prev.length;
        Node[] retVal = new Node[len];
        long val;
        int sent;
        for (int i = 0; i < len; i++){
            val = 0;
            sent = 0;
            for (int b = len/2; b != 0; b = b/2){
                val = (long) (val + prev[sent].inp*prev[sent++].weights[i/b % 2]);
            }
            retVal[i] = new Node(val, prev);
        }
        return retVal;
    }
    
    /*
    Plan: 
    
    2 nodes first layer |  (MONTH)  (DAY  )
                           (w1 w2)  (w3 w4)   
    
    4 nodes sec   layer |  (MDw1w3)  (MDw1w4)  (MDw2w3)  (MDw2w4)
                           (2w1 2w2) (2w3 2w4) (2w5 2w6) (2w7 2w8)
    
    
                     
    16 nodes thr  layer |  (1357) (1457) (1367) (1467) (1358) (1458) (1368) (1468) (2357) (2457) (2367) (2467) (2358) (2458) (2368) (2468)
   
    
    */
    public void run(){
        //INIT
        Node[] layer1 = new Node[2];
        layer1[0] = new Node(DATA.dates[0], null);
        layer1[1] = new Node(DATA.months[0], null);
        Node[] layer2 = layerMaker(layer1);
        Node[] layer3 = layerMaker(layer2);
        Node[] layer4 = layerMaker(layer3);
        
        Node[][] layers = new Node[][]{layer1, layer2, layer3, layer4};
        Node[][] layersp= new Node[4][];
        Node[][] layersn= new Node[4][];
        
        for (int i = 0; i < 4; i++){
            layersp[i] = new Node[layers[i].length];
            layersn[i] = new Node[layers[i].length];
            System.arraycopy(layers[i], 0, layersp[i], 0, layers[i].length);
            System.arraycopy(layers[i], 0, layersn[i], 0, layers[i].length);
        }
        
        
        
        //DISPLAY
        for (Node n : layer1){
            System.out.println(n.inp);
        }
        System.out.println();
        for (Node n : layer2){
            System.out.println(n.inp);
        }
        System.out.println();
        for (Node n : layer3){
            System.out.println(n.inp);
        }
        for (Node n : layer4){
            System.out.println(n.inp);
        }
        System.out.println();
        
        
        
        //OPTIMIZE
        long val1;
        long val2;
        long val3;
        for (int repeat = 0; repeat < 10; repeat++){
            //get to local minimum of weights based on input dates and months (folds)
            for (int datpos = 0; datpos < DATA.dates.length; datpos++){
                layers[0][0].inp = DATA.dates[datpos];
                layers[0][1].inp = DATA.months[datpos];

                layers[1] = layerMaker(layers[0], layers[1]);
                layers[2] = layerMaker(layers[1], layers[2]);
                layers[3] = layerMaker(layers[2], layers[3]);


                long prevInac = Long.MAX_VALUE;
                while (true){
                    for (int c = 0; c < layers.length-1; c++){
                        for (int i = 0; i < layers[c].length; i++){
                            for (int w = 0; w < 2; w++){
                                System.out.println("I: " + i + " W: " + w);
                                while (true){//assumes that sp, sn, and s are the same
                                    layersp[c][i].weights[w]+= 0.1;
                                    if (layersp[c][i].weights[w] > 100.0) layersp[c][i].weights[w] = 100.0;
                                    if (layersp[c][i].weights[w] < 0.0) layersp[c][i].weights[w] = 0.0;

                                    for (int g = c+1; g < layers.length; g++){
                                        layersp[g] = layerMaker(layersp[g-1], layersp[g]);
                                    }

                                    layersn[c][i].weights[w]-= 0.1;
                                    if (layersn[c][i].weights[w] > 100.0) layersn[c][i].weights[w] = 100.0;
                                    if (layersn[c][i].weights[w] < 0.0) layersn[c][i].weights[w] = 0.0;
                                    
                                    for (int g = c+1; g < layers.length; g++){
                                        layersn[g] = layerMaker(layersn[g-1], layersn[g]);
                                    }

                                    val2=0;//"middle" val
                                    for (Node n : layers[3]){
                                        val2 += n.inp;
                                    }
                                    val1=0;//"lesser" val
                                    for (Node n : layersn[3]){
                                        val1 += n.inp;
                                    }
                                    val3=0;//"greater" val
                                    for (Node n : layersp[3]){
                                        val3 += n.inp;
                                    }

                                    //Currently all three vals are the output of the networks
                                    //Switch it to be the error
                                    System.out.println("VALS");
                                    System.out.println(val1 + "\n" + val2 + "\n" + val3 + "\n");

                                    val1 = error(val1, datpos);
                                    val2 = error(val2, datpos);
                                    val3 = error(val3, datpos);

                                    System.out.println("ERRORS");
                                    System.out.println(val1 + "\n" + val2 + "\n" + val3 + "\n");

                                    if (val3 > val2 && val1 > val2){// Bottom of valley
                                        System.out.println("BROKE");
                                        break;
                                    }
                                    else if (val3 < val2){//+ is closer to bottom of valley
                                        System.out.println("RIGHT");
                                        System.arraycopy(layersp, 0, layers, 0, layersp.length);
                                        System.arraycopy(layersp, 0, layersn, 0, layersp.length);
                                    } else if (val1 < val2){//- is closer to bottom of valley
                                        System.out.println("LEFT");
                                        System.arraycopy(layersn, 0, layers, 0, layersn.length);
                                        System.arraycopy(layersn, 0, layersn, 0, layersn.length);
                                    }
                                    else if (val1 == val2 || val3 == val2){//plateau
                                        break;
                                    }
                                }

                            }
                        }
                    }
                    long currentVal = 0;
                    for (Node n : layers[3]){
                        currentVal += n.inp;
                    }
                    System.out.println("Current Innaccuracy " + Math.abs(DATA.test[0]*1.0 - currentVal*1.0)/(DATA.test[0]*1.0));

                    System.out.println("Current: " + (long) Math.abs(DATA.test[0] - currentVal));
                    System.out.println("Prev: " + prevInac);
                    if ((long) Math.abs(DATA.test[0] - currentVal) == prevInac) break;
                    else prevInac = (long) Math.abs(DATA.test[0]-currentVal);
                }
            }
        }
        long finalError = 0;
        long finalCurrent;
        long minError = Long.MAX_VALUE;
        long maxError = Long.MIN_VALUE;
        results = new long[DATA.dates.length];
        
        for (int datPos = 0; datPos < DATA.dates.length; datPos++){
            finalCurrent = 0;
            layers[0][0].inp = DATA.dates[datPos];
            layers[0][1].inp = DATA.months[datPos];

            layers[1] = layerMaker(layers[0], layers[1]);
            layers[2] = layerMaker(layers[1], layers[2]);
            layers[3] = layerMaker(layers[2], layers[3]);
            for (Node n : layers[3]){
                finalCurrent += n.inp;
            }
            results[datPos] = finalCurrent;
            long etemp = error(finalCurrent, datPos);
            finalError += etemp;
            if (etemp < minError) minError = etemp;
            if (etemp > maxError) maxError = etemp;
        }
        System.out.println("Average error " + (finalError/DATA.dates.length));
        System.out.println("Max error     " + maxError);
        System.out.println("Min error     " + minError);

        for (int b = 0; b < layers.length; b++){
            System.out.print("LAYER" + b + ": ");
            for (int i = 0; i < layers[b].length; i++){
                System.out.print("(n " + i + " ");
                for (int w = 0; w < layers[b][i].weights.length; w++){
                    System.out.print("w" + w + "=" + layers[b][i].weights[w] + " ");
                }
                System.out.print(") ");
            }
            System.out.println();
        }
    }
    
    public void altRun(){
        //INIT
        Node[] layer1 = new Node[2];
        layer1[0] = new Node(DATA.dates[0], null);
        layer1[1] = new Node(DATA.months[0], null);
        Node[] layer2 = layerMaker(layer1);
        Node[] layer3 = layerMaker(layer2);
        Node[] layer4 = layerMaker(layer3);
        
        Node[][] layers = new Node[][]{layer1, layer2, layer3, layer4};
        Node[][] layersp= new Node[4][];
        Node[][] layersn= new Node[4][];
        
        for (int i = 0; i < 4; i++){
            layersp[i] = new Node[layers[i].length];
            layersn[i] = new Node[layers[i].length];
            System.arraycopy(layers[i], 0, layersp[i], 0, layers[i].length);
            System.arraycopy(layers[i], 0, layersn[i], 0, layers[i].length);
        }
        
        
        
        //DISPLAY
        for (Node n : layer1){
            System.out.println(n.inp);
        }
        System.out.println();
        for (Node n : layer2){
            System.out.println(n.inp);
        }
        System.out.println();
        for (Node n : layer3){
            System.out.println(n.inp);
        }
        for (Node n : layer4){
            System.out.println(n.inp);
        }
        System.out.println();
        
        
        
        //OPTIMIZE
        long val1;
        long val2;
        long val3;
        for (int repeat = 0; repeat < 10; repeat++){
            //get to local minimum of weights
            long prevInac = Long.MAX_VALUE;
            for (int spiral = 10; spiral > 0; spiral--){
                for (int c = 0; c < layers.length-1; c++){
                    for (int i = 0; i < layers[c].length; i++){
                        for (int w = 0; w < 2; w++){
                            System.out.println("I: " + i + " W: " + w);
                            while (true){//assumes that sp, sn, and s are the same
                                layersp[c][i].weights[w]+= 0.1*spiral;
                                if (layersp[c][i].weights[w] > 100.0) layersp[c][i].weights[w] = 100.0;
                                if (layersp[c][i].weights[w] < 0.0) layersp[c][i].weights[w] = 0.0;

                                for (int g = c+1; g < layers.length; g++){
                                    layersp[g] = layerMaker(layersp[g-1], layersp[g]);
                                }

                                layersn[c][i].weights[w]-= 0.1*spiral;
                                if (layersn[c][i].weights[w] > 100.0) layersn[c][i].weights[w] = 100.0;
                                if (layersn[c][i].weights[w] < 0.0) layersn[c][i].weights[w] = 0.0;

                                for (int g = c+1; g < layers.length; g++){
                                    layersn[g] = layerMaker(layersn[g-1], layersn[g]);
                                }

                                val1 = 0; 
                                val2 = 0;
                                val3 = 0;

                                Node[][][] superLayers = new Node[][][]{layersn, layers, layersp};

                                long runCurrent;
                                long currentError;
                                int sent = 0;
                                for (Node[][] glayers : superLayers){
                                    currentError = 0;
                                    for (int datPos = 0; datPos < DATA.dates.length; datPos++){
                                        runCurrent = 0;
                                        glayers[0][0].inp = DATA.dates[datPos];
                                        glayers[0][1].inp = DATA.months[datPos];

                                        glayers[1] = layerMaker(layers[0], layers[1]);
                                        glayers[2] = layerMaker(layers[1], layers[2]);
                                        glayers[3] = layerMaker(layers[2], layers[3]);

                                        for (Node n : glayers[3]){
                                            runCurrent += n.inp;
                                        }
                                        currentError += error(runCurrent, datPos);
                                    }
                                    if (sent == 0){
                                        val1 = currentError;
                                    } else if (sent == 1){
                                        val2 = currentError;
                                    } else if (sent == 2){
                                        val3 = currentError;
                                    }
                                    sent++;
                                }



                                System.out.println("ERRORS");
                                System.out.println(val1 + "\n" + val2 + "\n" + val3 + "\n");

                                if (val3 > val2 && val1 > val2){// Bottom of valley
                                    System.out.println("BROKE");
                                    break;
                                }
                                else if (val3 < val2){//+ is closer to bottom of valley
                                    System.out.println("RIGHT");
                                    System.arraycopy(layersp, 0, layers, 0, layersp.length);
                                    System.arraycopy(layersp, 0, layersn, 0, layersp.length);
                                } else if (val1 < val2){//- is closer to bottom of valley
                                    System.out.println("LEFT");
                                    System.arraycopy(layersn, 0, layers, 0, layersn.length);
                                    System.arraycopy(layersn, 0, layersn, 0, layersn.length);
                                }
                                else if (val1 == val2 || val3 == val2){//plateau
                                    break;
                                }
                            }

                        }
                    }
                }
                long currentVal = 0;
                for (Node n : layers[3]){
                    currentVal += n.inp;
                }
                System.out.println("Current Innaccuracy " + Math.abs(DATA.test[0]*1.0 - currentVal*1.0)/(DATA.test[0]*1.0));

                System.out.println("Current: " + (long) Math.abs(DATA.test[0] - currentVal));
                System.out.println("Prev: " + prevInac);
                prevInac = (long) Math.abs(DATA.test[0]-currentVal);
            }
        }
        
        long finalError = 0;
        long finalCurrent;
        long minError = Long.MAX_VALUE;
        long maxError = Long.MIN_VALUE;
        results = new long[DATA.dates.length];
        
        for (int datPos = 0; datPos < DATA.dates.length; datPos++){
            finalCurrent = 0;
            layers[0][0].inp = DATA.dates[datPos];
            layers[0][1].inp = DATA.months[datPos];

            layers[1] = layerMaker(layers[0], layers[1]);
            layers[2] = layerMaker(layers[1], layers[2]);
            layers[3] = layerMaker(layers[2], layers[3]);
            for (Node n : layers[3]){
                finalCurrent += n.inp;
            }
            results[datPos] = finalCurrent;
            long etemp = error(finalCurrent, datPos);
            finalError += etemp;
            if (etemp < minError) minError = etemp;
            if (etemp > maxError) maxError = etemp;
        }
        System.out.println("Average error " + (finalError/DATA.dates.length));
        System.out.println("Max error     " + maxError);
        System.out.println("Min error     " + minError);

        for (int b = 0; b < layers.length; b++){
            System.out.print("LAYER" + b + ": ");
            for (int i = 0; i < layers[b].length; i++){
                System.out.print("(n " + i + " ");
                for (int w = 0; w < layers[b][i].weights.length; w++){
                    System.out.print("w" + w + "=" + layers[b][i].weights[w] + " ");
                }
                System.out.print(") ");
            }
            System.out.println();
        }
    }
    
    public long[] results;
    
    /**
     * 
     * 
     *  //This algorithm works great
        System.out.println();
        int len = 16;
        for (int i = 0; i < len; i++){
            System.out.print("(");
            int sent = 0;
            for (int b = len/2; b != 0; b = b/2){
                System.out.print(i/b % 2);
            
            }
            System.out.println(")");
        }
        //(0000)
        layer3[0] = new Node(layer2[0].val(0)*layer2[1].val(0)*layer2[2].val(0)*layer2[3].val(0), layer2);
        //(0001)
        layer3[1] = new Node(layer2[0].val(0)*layer2[1].val(0)*layer2[2].val(0)*layer2[3].val(1), layer2);
        
        //(0010)
        layer3[2] = new Node(layer2[0].val(0)*layer2[1].val(0)*layer2[2].val(1)*layer2[3].val(0), layer2);
        //(0011)
        layer3[3] = new Node(layer2[0].val(0)*layer2[1].val(0)*layer2[2].val(1)*layer2[3].val(1), layer2);
        
        //(0100)
        layer3[4] = new Node(layer2[0].val(0)*layer2[1].val(1)*layer2[2].val(0)*layer2[3].val(0), layer2);
        //(0101)
        layer3[5] = new Node(layer2[0].val(0)*layer2[1].val(1)*layer2[2].val(0)*layer2[3].val(1), layer2);
        
        //(0110)
        layer3[6] = new Node(layer2[0].val(0)*layer2[1].val(1)*layer2[2].val(1)*layer2[3].val(0), layer2);
        //(0111)
        layer3[7] = new Node(layer2[0].val(0)*layer2[1].val(1)*layer2[2].val(1)*layer2[3].val(1), layer2);
        
        //(1000)
        layer3[8] = new Node(layer2[0].val(1)*layer2[1].val(0)*layer2[2].val(0)*layer2[3].val(0), layer2);
        //(1001)
        layer3[9] = new Node(layer2[0].val(1)*layer2[1].val(0)*layer2[2].val(0)*layer2[3].val(1), layer2);
        
        //(1010)
        layer3[10] = new Node(layer2[0].val(1)*layer2[1].val(0)*layer2[2].val(1)*layer2[3].val(0), layer2);
        //(1011)
        layer3[11] = new Node(layer2[0].val(1)*layer2[1].val(0)*layer2[2].val(1)*layer2[3].val(1), layer2);
        
        //(1100)
        layer3[12] = new Node(layer2[0].val(1)*layer2[1].val(1)*layer2[2].val(0)*layer2[3].val(0), layer2);
        //(1101)
        layer3[13] = new Node(layer2[0].val(1)*layer2[1].val(1)*layer2[2].val(0)*layer2[3].val(1), layer2);
        
        //(1110)
        layer3[14] = new Node(layer2[0].val(1)*layer2[1].val(1)*layer2[2].val(1)*layer2[3].val(0), layer2);
        //(1111)
        layer3[15] = new Node(layer2[0].val(1)*layer2[1].val(1)*layer2[2].val(1)*layer2[3].val(1), layer2);
            
        int sent = 0;
        for (int a = 0; a < 2; a++){
            for (int b = 0; b < 2; b++){
                for (int c = 0; c < 2; c++){
                    for (int d = 0; d < 2; d++){
                        layer3[sent++] = new Node(layer2[0].val(a)*layer2[1].val(b)*layer2[2].val(c)*layer2[3].val(d), layer2);
                        System.out.println("(" + a + "" + b + "" + c + "" + d + ")");
                    }
                }
            }
        }
        System.out.println();
        
        for (int i = 0; i < 16; i++){
            System.out.print("(");
            System.out.print(i/8 % 2);
            System.out.print(i/4 % 2);
            System.out.print(i/2 % 2);
            System.out.print(i % 2);
            System.out.println(")");
        }
        */
}
