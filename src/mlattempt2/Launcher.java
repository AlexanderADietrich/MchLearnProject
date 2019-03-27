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
public class Launcher {
    
    
    public static void launchNNTest()
    {
        NeuralNetworkTest n = new NeuralNetworkTest();
        n.test();
    }
    public static void launchEncoder(){
        double[][] images = new double[2][];
        String[] addresses = new String[]{"Mona_Lisa.jpg", "Painting_2.jpg"};
        int[] rgb;
        int width = 0;
        int height = 0;
        for (int b = 0; b < addresses.length; b++)
        {
            rgb = Loader.loadImageRGB(addresses[b]);
            width = rgb[rgb.length-2];
            height = rgb[rgb.length-1];
            double[] temp = new double[rgb.length-2];
            int[] test = new int[rgb.length-2];
            for (int i = 2; i < rgb.length; i++)
            {
                System.out.println(rgb[i]);
                temp[i-2] = rgb[i];
                test[i-2] = rgb[i];
            }
            images[b] = temp;
        }
        
        AutoEncoder encode = new AutoEncoder(images);
        for (int i = 0; i < 100; i++)
        {
            System.out.println((i+1)+"/100");
            encode.updateWeights();
        }
        for (int b = 0; b < images.length; b++)
        {
            double[] answers = encode.calc(images[b]);
            int[] cast = new int[answers.length];
            for (int i = 0; i < answers.length; i++)
            {
                cast[i] = (int) answers[i];
                System.out.println(cast[i]);
            }
            Loader.createImage(width, height, cast, "image"+b);
        }
    }
    /**
     * The "neural network" I built can only output what's essentially a linear
     * regression, which is useful, but not as accurate as I would like.
     */
    public static void launchHybrid(){
        NeuralNetwork n = new NeuralNetwork();
        n.altRun();
        GeneticRefiner g = new GeneticRefiner(n.results);
        g.run(10000);
    }
    public static void launchGenetic(){
        ConstantOptimizer copti = new ConstantOptimizer();
        copti.bestConsts = new int[]{5, 1, 1, 5, 1, 3};
        //The constant optimizer is slow and unreliable right now.
        
        
        
        String[] bests = new String[10];
        BaseMachine machine;
        for (int i = 0; i < bests.length; i++){
            machine = new BaseMachine();
            machine.setConsts(copti.bestConsts);
            machine.run(1000, 100);
            bests[i] = machine.absmaxstring;
        }
        machine = new BaseMachine();
        machine.setConsts(copti.bestConsts);
        machine.generators = new Generator[10];
        for (int i = 0; i < machine.generators.length; i++){
            System.out.println(bests[i]);
            machine.generators[i] = new Generator(machine, bests[i]);
        }
        machine.run(10000, 100);
    }
    public static void main(String[] args){
        launchNNTest();
    }
}
