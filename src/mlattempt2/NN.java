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
public class NN {
    public double reLu(double d)
    {
        return (d < 0) ? 0 : d;
    }
    public double dReLu(double d)
    {
        return (d < 0) ? 0 : 1;
    }
    public class Node
    {
        //Used for calculations
        public double currentInput = 0.0;
        //Used for derivative
        public double deltafeed = 0.0;
        /**
         * Links are nodes this is linked to.
         *      EXAMPLE: links[w].input(currentInput(weights[w]));
         */
        public Node[]       links;
        public double[]     weights;
        public Node()
        {

        }
        public Node(Node[] links)
        {
            this.links = links;
            weights = new double[links.length];
            int sent = 0;
            for (Node n : links)
                weights[sent++] = Math.random();
        }
        public void passLinks(Node[] links)
        {
            this.links = new Node[links.length];
            weights = new double[links.length];
            int sent = 0;
            for (Node n : links)
            {
                this.links[sent] = n;
                weights[sent++] = Math.random();
            }
        }
        public void input(double d)
        {
            currentInput += reLu(d);
        }
        public void feedForward()
        {
            int sent = 0;
            for (Node n : links)
            {
                n.input(currentInput*weights[sent++]);
            }
        }
        public void dFeedForward()
        {
            int sent = 0;
            for (Node n : links)
            {
                n.deltafeed += dReLu(currentInput)*weights[sent++]*deltafeed;
            }
        }
        public void reset()
        {
            currentInput = 0;
        }
        public void dReset()
        {
            deltafeed = 0;
        }
    }

    public NN getNN(double[] inputs, double[] outputs, int layers, int nodesPer, boolean sparse)
    {
        double[][] inp = new double[inputs.length][1];
        double[][] out = new double[inputs.length][1];
        for (int i = 0; i < inp.length; i++)
        {
            inp[i][0] = inputs[i];
            out[i][0] = outputs[i];
        }
        return new NN(inp, out, layers, nodesPer, sparse);
    }
    Updater[] updaters;
    DeltaUpdater[] dUpdaters;
    double[][]  inputs;
    double[][]  outputs;
    Node[][]    layers;
    double dw;
    boolean sparse;
    double numWeights;

    /**
     * Creates a completely uninitialized NN
     */
    public NN()
    {
        
    }
    public NN(double[][] inputs, double[][] outputs, int layers, int nodesPer, boolean sparse)
    {
        this.sparse = sparse;
        int n = 0;
        for (double[] list : outputs)
            for (double d : list)
                n++;
        //separated to avoid possible double max value
        double average = 0.0;
        for (double[] list : outputs)
            for (double d : list)
                average += d / (n*1.0);

        //Large errors need small dw's
        dw = 1/average;

        this.inputs = inputs;
        this.outputs = outputs;
        this.layers = new Node[layers+2][];

        //Create inputs
        this.layers[0] = new Node[inputs[0].length];
        for (int i = 0; i < this.layers[0].length; i++)
        {
            this.layers[0][i] = new Node();
        }
        //Create hidden(s)
        for (int i = 1; i < this.layers.length-1; i++)
        {
            this.layers[i] = new Node[nodesPer];
            for (int w = 0; w < nodesPer; w++)
            {
                this.layers[i][w] = new Node();
            }
            if (sparse) nodesPer /= 2;
        }
        //Create output
        this.layers[this.layers.length-1] = new Node[outputs[0].length];
        for (int i = 0; i < this.layers[this.layers.length-1].length; i++)
        {
            this.layers[this.layers.length-1][i] = new Node();
        }
        //Link
        for (int i = 0; i < this.layers.length-1; i++)
        {
            for (int w = 0; w < this.layers[i].length; w++)
            {
                numWeights += this.layers[i+1].length;
                this.layers[i][w].passLinks(this.layers[i+1]);
            }
        }
        
        updaters = new Updater[this.layers.length-1];
        for (int i = 0; i < updaters.length; i++)
            updaters[i] = new Updater(this.layers[i]);
        dUpdaters = new DeltaUpdater[this.layers.length-1];
        for (int i = 0; i < dUpdaters.length; i++)
            dUpdaters[i] = new DeltaUpdater(this.layers[i]);
    }
    public void reset()
    {
        for (int i = 0; i < layers.length; i++)
            for (int w = 0; w < layers[i].length; w++)
                layers[i][w].reset();
    }
    public void dReset()
    {
        for (int i = 0; i < layers.length; i++)
            for (int w = 0; w < layers[i].length; w++)
                layers[i][w].dReset();
    }
    /**
     * 
     * @param inputs assumed to be length of input nodes
     * @return outputs
     */
    public double[] calc(double[] inputs)
    {
        //reset
        reset();
        for (int i = 0; i < layers[0].length; i++)
            layers[0][i].input(inputs[i]);
        
        for (Updater e : updaters)
            e.run();
        /*//Input to inputs and feed forward
        for (int i = 0; i < layers[0].length; i++)
        {
            layers[0][i].input(inputs[i]);
            layers[0][i].feedForward();
        }
        for (int i = 1; i < layers.length-1; i++)
            for (int w = 0; w < layers[i].length; w++)
                layers[i][w].feedForward();*/
        double[] retVal = new double[layers[layers.length-1].length];
        for (int i = 0; i < layers[layers.length-1].length; i++)
            retVal[i] = layers[layers.length-1][i].currentInput;
        return retVal;
    }
    
    //public double prevAvg = Double.MAX_VALUE;//Used to adapt dw, starts at max val
    /**
     * Assumes fully connected
     */
    public void updateWeights()
    {
        //for each input output pair
        for (int inp = 0; inp < inputs.length; inp++)
        {
            //populate with values
            double[] calcs = calc(inputs[inp]);
            //saves on time
            double[] dists = new double[calcs.length];
            for (int i = 0; i < dists.length; i++)
                dists[i] = (calcs[i]-outputs[inp][i]);
            //calc updates, update
            for (int i = 0; i < layers.length-1; i++)
            {
                for (int b = 0; b < layers[i].length; b++)
                {
                    for (int w = 0; w < layers[i][b].weights.length; w++)
                    {
                        //reset
                        dReset();
                        //feed forward
                        layers[i+1][w].deltafeed = layers[i][b].currentInput;
                        /**
                         * New feed forward takes more lines but pays off
                         * in that it doesn't move on to the next layer 
                         * before the previous is done
                         */

                        //Feed forward from next node
                        if (i+1 < layers.length-1) 
                            layers[i+1][w].dFeedForward();
                        //Feed forward from layers after next node
                        for (int r = i+2; r < layers.length-1; r++)
                            dUpdaters[r].run();
                        //Adjust weights for each output
                        for (int r = 0; r < layers[layers.length-1].length; r++)
                            layers[i][b].weights[w] -=
                                    0.000000001
                                    *dw
                                    *layers[layers.length-1][r].deltafeed
                                    *dists[r]; 
                    }
                }
            }
        }
    }
    
    public double prevAvg = Double.MAX_VALUE;//Used to adapt dw, starts at max val
    /**
     * "Adaptive Update Weights"
     */
    public void aUpdWeights()
    {
        double currentAvg = 0.0;
        //for each input output pair
        for (int inp = 0; inp < inputs.length; inp++)
        {
            //populate with values
            double[] calcs = calc(inputs[inp]);
            //saves on time
            double[] dists = new double[calcs.length];
            for (int i = 0; i < dists.length; i++)
            {
                dists[i] = (calcs[i]-outputs[inp][i]);
                currentAvg += dists[i]/dists.length;
            }
            if (currentAvg > prevAvg) dw = dw / 1.1;
            else dw = dw *1.1;
            prevAvg = currentAvg;
            //calc updates, update
            for (int i = 0; i < layers.length-1; i++)
            {
                for (int b = 0; b < layers[i].length; b++)
                {
                    for (int w = 0; w < layers[i][b].weights.length; w++)
                    {
                        //reset
                        dReset();
                        //feed forward
                        layers[i+1][w].deltafeed = layers[i][b].currentInput;
                        /**
                         * New feed forward takes more lines but pays off
                         * in that it doesn't move on to the next layer 
                         * before the previous is done
                         */

                        //Feed forward from next node
                        if (i+1 < layers.length-1) 
                            layers[i+1][w].dFeedForward();
                        //Feed forward from layers after next node
                        for (int r = i+2; r < layers.length-1; r++)
                            dUpdaters[r].run();
                        //Adjust weights for each output
                        for (int r = 0; r < layers[layers.length-1].length; r++)
                            layers[i][b].weights[w] -=
                                    0.000000001
                                    *(1.0/(i+1))
                                    *dw
                                    *layers[layers.length-1][r].deltafeed
                                    *dists[r]; 
                    }
                }
            }
        }
    }
    
    public void disp()
    {
        for (int i = 0; i < layers.length; i++)
        {
            for (int w = 0; w < layers[i].length; w++)
            {
                System.out.print(layers[i][w].currentInput + "\t");
            }
            System.out.println();
        }
    }
}

