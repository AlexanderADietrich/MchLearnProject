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
    public static void launchNN2(){
        WebNetwork.run();
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
        launchNN2();
    }
}
