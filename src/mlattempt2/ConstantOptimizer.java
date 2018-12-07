package mlattempt2;

import java.util.Arrays;
import java.util.Random;


public class ConstantOptimizer {
    public BaseMachine[] arr;
    
    public int[] bestConsts;
    
    public int max = Integer.MIN_VALUE;
    public int maxindex = -1;
    
    public Random r;
    
    public ConstantOptimizer(){
        r = new Random();
        arr = new BaseMachine[10];
        for (int i = 0; i < arr.length; i++){
            arr[i] = new BaseMachine();
            arr[i].disp = false;
            arr[i].genConsts();
        }
    }
    
    public void run(int times){
        int sent = -1;
        while (++sent < times){
            System.out.println("COpti Run " + sent);
            
            int[] temp = bestConsts;
            //Determine Rewards
            for (int i = 0; i < arr.length; i++){
                arr[i].run(10, 9999);
                if (arr[i].absmax > max){
                    max = arr[i].absmax;
                    maxindex = i;
                    bestConsts = arr[i].consts;
                }
                System.out.println("COpti Generator Run " + i + " " + arr[i].absmaxstring.split(" ").length);
            }
            System.out.println(Arrays.toString(bestConsts));
            System.out.println("[mut, imute, dmute, addtree, mutree, shiftree]");
            
            
            //Combine/Mutate
            for (int i = 0; i < arr.length; i++){
                if (i != maxindex){
                    if (r.nextBoolean()){//Prevent genetic over-uniformity
                        if (i < arr.length-1){
                            arr[i] = combine(arr[i], arr[i+1]);
                        } else {
                            arr[i] = combine(arr[i], arr[0]);
                        }
                        arr[i].disp=false;
                    } else {
                        int[] constantstemp = arr[i].consts;
                        arr[i] = new BaseMachine();
                        arr[i].disp=false;
                        arr[i].consts = constantstemp;
                    }
                    for (int b = 0; b < 5; b++){
                        int index = r.nextInt(BaseMachine.constslen);
                        arr[i].consts[index] = arr[i].consts[index] + ((r.nextBoolean()) ? 1 : -1);
                        if (arr[i].consts[index] < 0) arr[i].consts[index] = 0;
                    }
                } else {
                    arr[i] = new BaseMachine();
                    arr[i].disp=false;
                    arr[i].consts = bestConsts;
                }
            }
            
        }
    }
    
    public BaseMachine combine(BaseMachine bmch, BaseMachine bmch2){
        int[] newconsts = new int[BaseMachine.constslen];
        
        /*
        More accurate than previously to how genetics work (gene replacement
        /swapping, rather than averaging).
        */
        for (int i = 0; i < newconsts.length;i++){
            if (r.nextBoolean()){
                newconsts[i] = bmch.consts[i];
            } else {
                newconsts[i] = bmch2.consts[i];
            }
        }
        
        
        BaseMachine retVal = new BaseMachine();
        retVal.setConsts(newconsts);
        return retVal;
    }
}
