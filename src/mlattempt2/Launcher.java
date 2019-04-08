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
    
    public static final int GRID_TEST_SIZE = 4;
    public static void launchGridTest()
    {
        Grid g = new Grid(GRID_TEST_SIZE);
        
        
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
        
        
        double[][] higherInputs = new double[10][g.outputNodes.length];
        double[][] hitemp;
        int sent = 0;
        for (int i = 0; i < inp.length; i++)
        {
            double[] temp = new double[g.outputNodes.length];
            //INPUT
            g.inputNodes[0].currentInput = inp[i][0];
            g.inputNodes[1].currentInput = inp[i][1];
            
            //CALCULATE
            g.tick();
            
            /*
            //DISPLAY
            g.disp();
            System.out.println();
            */
            
            //STORE
            for (int b = 0; b < temp.length; b++)
                temp[b] = g.outputNodes[b].currentInput;
            higherInputs[sent++] = temp;
            
            //RESIZE
            if (sent > higherInputs.length/2)
            {
                hitemp = new double[higherInputs.length*2][GRID_TEST_SIZE];
                for (int b = 0; b < sent; b++)
                    hitemp[b] = higherInputs[b];
                higherInputs = hitemp;
            }
        }
        System.out.println("Sent: " + sent);
        hitemp = new double[sent][GRID_TEST_SIZE];
        for (int b = 0; b < sent; b++)
            hitemp[b] = higherInputs[b];
        higherInputs = hitemp;
        NN network = new NN(higherInputs, out, 2, 2, false);
        for (int i = 0; i < 100; i++)
        {
            network.aUpdWeights();
        }
        System.out.println("INP\tOUT\tRES\tGRIDINP");
        for (int r = 1; r < higherInputs.length; r++)
        {
            System.out.print(inputs[r]+"\t"+outputs[r]+"\t"+network.calc(higherInputs[r])[0] + "\t");
            /*for (double d : higherInputs[r])
                System.out.print(d + "\t");
            System.out.println("\n");
            network.disp();*/
            System.out.println();
        }
    }
    
    public static void testRandomNumbers()
    {
        double d = Math.random();
        double[][] inputs = new double[1000][1];
        double[][] outputs = new double[1000][1];
        inputs[0][0] = d;
        for (int i = 0; i < 999; i++)
        {
            inputs[i+1][0] = Math.random();
            outputs[i][0] = inputs[i+1][0];
        }
        outputs[999][0] = Math.random();
        NN network = new NN(inputs, outputs, 3, 10, false);
        for (int i = 0; i < 100; i++)
        {
            System.out.println(i+"/99");
            network.updateWeights();
        }
        System.out.println("INP\tOUT\tRES");
        for (int r = 1; r < inputs.length; r++)
            System.out.println(inputs[r][0]+"\t"+outputs[r][0]+"\t"+network.calc(new double[]{inputs[r][0]})[0]);
    }
    
    public static void launchConvTest()
    {
        String[] addresses = new String[]{ "Painting_2.jpg", "Mona_Lisa.jpg"};
        double[][] images = new double[addresses.length][];
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
                temp[i-2] = -rgb[i];
                test[i-2] = rgb[i];
            }
            images[b] = temp;
        }

        ConvNN network = new ConvNN(images, images, 6, 6, width, height);
        for (int i = 0; i < 10; i++)
        {
            network.updateWeights();
            System.out.println(i+"/9");
        }
        for (int b = 0; b < images.length; b++)
        {
            double[] answers = network.calc(images[b]);
            int[] cast = new int[answers.length];
            for (int i = 0; i < answers.length; i++)
            {
                cast[i] = (int) -answers[i];
                System.out.println(cast[i]);
            }
            Loader.createImage(width, height, cast, "image"+b);
        }
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
        launchGridTest();
    }
}
