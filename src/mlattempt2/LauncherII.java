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
public class LauncherII {
    public static void main(String[] args){
        String[] bests = new String[10];
        BaseMachineII machine;
        for (int i = 0; i < bests.length; i++){
            machine = new BaseMachineII();
            machine.run(1000);
            bests[i] = machine.absmaxstring;
        }
        machine = new BaseMachineII();
        for (int i = 0; i < machine.generators.length; i++){
            System.out.println(bests[i]);
            machine.generators[i] = new GeneratorII(machine, bests[i]);
        }
        machine.run(1000);
    }
}
