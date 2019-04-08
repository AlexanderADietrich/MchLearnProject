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
public class NeuralNetworkTest {
    public NeuralNetworkTest()
    {
        
    }
    public void test()
    {
        double[][] split = Loader.loadDJIA();
        double[] inputs = split[0];
        double[] outputs = split[1];
        
        double[][] inp = new double[inputs.length-1][2];
        double[][] out = new double[inputs.length-1][1];
        
        for (int i = 1; i < outputs.length; i++)
        {
            inp[i-1][0] = inputs[i];    //current input
            inp[i-1][1] = outputs[i-1]; //previous output
            out[i-1][0] = outputs[i];   //current output
        }
        
        NN network;
        network = new NN(inp, out, 3, 3, false);
        for (int i = 0; i <= 100; i++){
            network.updateWeights();
        }
        network.disp();
        System.out.println("INP\tOUT\tRES");
        for (int r = 1; r < inputs.length; r+=10)
            System.out.println(inputs[r]+"\t"+outputs[r]+"\t"+network.calc(new double[]{inputs[r], outputs[r-1]})[0]);
    }
    
}
