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
    public static void main(String[] args){
        
        NeuralNetwork n = new NeuralNetwork();
        n.run();
        /*ConstantOptimizer copti = new ConstantOptimizer();
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
        machine.run(10000, 100);*/
    }
}
