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
public class ConvNN extends NN{
    /**
     * Leaky reLu required for some problems
     */
    public double reLu(double d)
    {
        return (d < 0) ? 0.00001*d : d;
    }
    public double dReLu(double d)
    {
        return (d < 0) ? 0.00001 : 1;
    }
    
    
    double bias;
    public ConvNN(double[][] inputs, double[][] outputs, int layers, int nodesPerSQ, int width, int height)
    {
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
        dw = 1.0/Math.abs(average);
        
        //"intercept"
        bias = average;

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
            this.layers[i] = new Node[nodesPerSQ*nodesPerSQ];
            for (int w = 0; w < nodesPerSQ*nodesPerSQ; w++)
            {
                this.layers[i][w] = new Node();
            }
        }
        //Create output
        this.layers[this.layers.length-1] = new Node[outputs[0].length];
        for (int i = 0; i < this.layers[this.layers.length-1].length; i++)
        {
            this.layers[this.layers.length-1][i] = new Node();
        }
        //Link
        Node[] temp;
        Node[][] twoDTemp;
        int wtemp;
        for (int i = 0; i < this.layers.length-1; i++)
        {
            for (int w = 0; w < this.layers[i].length; w++)
            {
                //Link "geometrically"
                
                
                wtemp = w;
                if (i == 0) w = (nodesPerSQ*w) / this.layers[i].length;
               
                //Connection to final is special case
                if (i == (this.layers.length-2))
                {
                    
                    int wfactor = width/nodesPerSQ;
                    int lfactor = height/nodesPerSQ;
                    twoDTemp = new Node[lfactor][wfactor];
                    for (int b = 0; b < lfactor; b++)
                    {
                        for (int g = 0; g < wfactor; g++)
                        {
                            twoDTemp[b][g] = this.layers[i+1][w*wfactor + b*width + g];
                        }
                    }
                    temp = new Node[lfactor*wfactor];
                    int sent = 0;
                    for (Node[] arr : twoDTemp)
                        for (Node no : arr)
                            temp[sent++] = no;
                }
                else {
                    //Corner 1 (Top Left)
                    if (w == 0)
                    {
                        temp = new Node[3];
                        //CounterClockwise
                        temp[0] = this.layers[i+1][0];
                        temp[1] = this.layers[i+1][1];
                        temp[2] = this.layers[i+1][nodesPerSQ];
                    }
                    //Corner 2 (Top Right)
                    else if ((w+1) == nodesPerSQ)
                    {
                        temp = new Node[3];
                        //CounterClockwise
                        temp[0] = this.layers[i+1][nodesPerSQ-1];
                        temp[1] = this.layers[i+1][nodesPerSQ*2-1];
                        temp[2] = this.layers[i+1][nodesPerSQ-2];
                    }
                    //Corner 3 (Bottom Right)
                    else if ((w+1) == (nodesPerSQ*nodesPerSQ))
                    {
                        temp = new Node[3];
                        //CounterClockwise
                        temp[0] = this.layers[i+1][nodesPerSQ*nodesPerSQ-1];
                        temp[1] = this.layers[i+1][nodesPerSQ*nodesPerSQ-2];
                        temp[2] = this.layers[i+1][nodesPerSQ*(nodesPerSQ-1)-1];
                    }
                    //Corner 4 (Bottom Left)
                    else if (w == (nodesPerSQ*(nodesPerSQ-1)))
                    {
                        temp = new Node[3];
                        //CounterClockwise
                        temp[0] = this.layers[i+1][nodesPerSQ*(nodesPerSQ-1)];
                        temp[1] = this.layers[i+1][nodesPerSQ*(nodesPerSQ-1)+1];
                        temp[2] = this.layers[i+1][nodesPerSQ*(nodesPerSQ-2)];
                    }
                    //Top Edge
                    else if (w < nodesPerSQ)
                    {
                        temp = new Node[4];
                        temp[0] = this.layers[i+1][w];
                        temp[1] = this.layers[i+1][w+1];
                        temp[2] = this.layers[i+1][w+nodesPerSQ];
                        temp[3] = this.layers[i+1][w-1];
                    }
                    //Right Edge
                    else if ((w+1) % nodesPerSQ == 0)
                    {
                        temp = new Node[4];
                        temp[0] = this.layers[i+1][w];
                        temp[1] = this.layers[i+1][w+nodesPerSQ];
                        temp[2] = this.layers[i+1][w-1];
                        temp[3] = this.layers[i+1][w-nodesPerSQ];
                    }
                    //Bottom Edge
                    else if (w > (nodesPerSQ*(nodesPerSQ-1)))
                    {
                        temp = new Node[4];
                        temp[0] = this.layers[i+1][w];
                        temp[1] = this.layers[i+1][w+1];
                        temp[2] = this.layers[i+1][w-nodesPerSQ];
                        temp[3] = this.layers[i+1][w-1];
                    }
                    //Left Edge
                    else if (w % nodesPerSQ == 0)
                    {
                        temp = new Node[4];
                        temp[0] = this.layers[i+1][w];
                        temp[1] = this.layers[i+1][w+1];
                        temp[2] = this.layers[i+1][w+nodesPerSQ];
                        temp[3] = this.layers[i+1][w-nodesPerSQ];
                    }
                    //Default
                    else
                    {
                        temp = new Node[5];
                        temp[0] = this.layers[i+1][w];
                        temp[1] = this.layers[i+1][w+1];
                        temp[2] = this.layers[i+1][w-nodesPerSQ];
                        temp[3] = this.layers[i+1][w-1];
                        temp[4] = this.layers[i+1][w+nodesPerSQ];
                    }
                }
                
                if (i == 0) w = wtemp;
                
                this.layers[i][w].passLinks(temp);
            }
        }
        
        updaters = new Updater[this.layers.length-1];
        for (int i = 0; i < updaters.length; i++)
            updaters[i] = new Updater(this.layers[i]);
        dUpdaters = new DeltaUpdater[this.layers.length-1];
        for (int i = 0; i < dUpdaters.length; i++)
            dUpdaters[i] = new DeltaUpdater(this.layers[i]);
    }
    public double[] calc(double[] inputs)
    {
        //reset
        reset();
        for (int i = 0; i < layers[0].length; i++)
            layers[0][i].input(inputs[i]);
        
        for (Updater e : updaters)
            e.run();
        double[] retVal = new double[layers[layers.length-1].length];
        for (int i = 0; i < layers[layers.length-1].length; i++)
            retVal[i] = layers[layers.length-1][i].currentInput+bias;
        return retVal;
    }
}
