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
public class AutoEncoder extends NN{
    public static final int LAYERS  = 2;
    public static final int NODES   = 100;
    public double bias;
    public AutoEncoder(double[][] inputs)
    {
        bias = 0;
        for (int i = 0; i < inputs[0].length; i++)
            bias += inputs[0][i]/(inputs[0].length*1.0);
        this.inputs = inputs;
        this.outputs = inputs;
        int n = 0;
        for (double[] list : outputs)
            for (double d : list)
                n++;
        //separated to avoid possible double max value
        double average = 0.0;
        for (double[] list : outputs)
            for (double d : list)
                average += d / (n*1.0);
        average = Math.abs(average);
        //Large errors need small dw's
        dw = 1.0/average;
        
        
        this.layers = new Node[LAYERS+2][];

        //Create inputs
        this.layers[0] = new Node[inputs[0].length];
        for (int i = 0; i < this.layers[0].length; i++)
        {
            this.layers[0][i] = new Node();
        }
        //System.out.println("Done creating inputs");
        //Create hidden(s)
        for (int i = 1; i < this.layers.length-1; i++)
        {
            this.layers[i] = new Node[NODES];
            for (int w = 0; w < NODES; w++)
            {
                this.layers[i][w] = new Node();
            }
        }
        //System.out.println("Done creating hiddens");
        //Create output
        this.layers[this.layers.length-1] = new Node[outputs[0].length];
        for (int i = 0; i < this.layers[this.layers.length-1].length; i++)
        {
            this.layers[this.layers.length-1][i] = new Node();
        }
        //System.out.println("Done creating outputs");
        //Link
        for (int i = 0; i < this.layers.length-1; i++)
        {
            for (int w = 0; w < this.layers[i].length; w++)
            {
                this.layers[i][w].passLinks(this.layers[i+1]);
            }
        }
        //System.out.println("Done linking");

    }
    public double[] calc(double[] inputs)
    {
        //reset
        reset();
        //Input to inputs and feed forward
        for (int i = 0; i < layers[0].length; i++)
        {
            layers[0][i].input(inputs[i]);
            layers[0][i].feedForward();
        }
        for (int i = 1; i < layers.length-1; i++)
            for (int w = 0; w < layers[i].length; w++)
                layers[i][w].feedForward();
        double[] retVal = new double[layers[layers.length-1].length];
        for (int i = 0; i < layers[layers.length-1].length; i++)
            retVal[i] = layers[layers.length-1][i].currentInput + bias;
        return retVal;
    }
    public void updateWeights()
    {
        //System.out.println("Beginning update");
        //for each input output pair
        for (int inp = 0; inp < inputs.length; inp++)
        {
            //System.out.print("Calculating...");
            //populate with values
            double[] calcs = calc(inputs[inp]);
            //System.out.println("Done");
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
                            for (int j = 0; j < layers[r].length; j++)
                                layers[r][j].dFeedForward();
                        //Adjust weights for each output
                        for (int r = 0; r < layers[layers.length-1].length; r++)
                        {
                            layers[i][b].weights[w] -= 
                                    0.000000001
                                    *dw
                                    *layers[layers.length-1][r].deltafeed
                                    *(calcs[r]-outputs[inp][r]); 
                        }
                    }
                }
            }
        }
    }
}
