package mlattempt2;

import java.util.HashMap;

public class ConstantOptimizer {
    public HashMap<Integer, Integer> constants;
    public HashMap<Integer, Integer> constants2;
    public BaseMachine current;
    
    public HashMap<Integer, Integer> totals;
    public int times = 0;
    
    
    //Id, Constant
    public ConstantOptimizer(){
        constants   = new HashMap<>();
        constants2  = new HashMap<>();
        totals = new HashMap<>();
        current = new BaseMachine();
    }
    public int temp1 = 0;
    public int temp2 = 0;
    public static void main(String[] args){
        ConstantOptimizer c = new ConstantOptimizer();
        
        c.totals.put(1, 0);
        c.totals.put(2, 0);
        c.totals.put(3, 0);
        c.constants.put(1, 4);
        c.constants.put(2, 4);
        c.constants.put(3, 4);
        c.constants2.put(1, 5);
        c.constants2.put(2, 4);
        c.constants2.put(3, 4);
        while (true){
            
            c.temp1 = 0;
            c.temp2 = 0;
            
                    c.current = new BaseMachine();
                    c.current.const1 = c.constants.get(1);
                    c.current.const2 = c.constants.get(2);
                    c.current.const3 = c.constants.get(3);
                    c.current.runs = 1000;
                    c.current.go();
                    while (c.current.isRunning()){
                        //Do Nothing
                    }
                    c.temp1 += c.current.getReward();
                    c.current.kill();

                    c.current = new BaseMachine();
                    c.current.const1 = c.constants2.get(1);
                    c.current.const2 = c.constants2.get(2);
                    c.current.const3 = c.constants2.get(3);
                    c.current.runs = 1000;
                    c.current.go();
                    while (c.current.isRunning()){
                        //Do Nothing
                    }
                    c.temp2 += c.current.getReward();
                    c.current.kill();
            
            System.out.print("Val 1\t" + c.temp1);
            for (int g : c.constants.values()){
                System.out.print(" " + g);
            }
            System.out.println();
            
            System.out.print("Val 2\t" + c.temp2);
            for (int g : c.constants2.values()){
                System.out.print(" " + g);
            }
            System.out.println();
            
            System.out.println();
            
            c.times++;
            for (int g : c.constants.keySet()){
                c.totals.put(g, c.totals.get(g)+c.constants.get(g));
                System.out.println("Avg for val " + g + ": " + c.totals.get(g)/c.times);
            }
            
            System.out.println();
            
            
            if (c.temp1 < c.temp2){
                c.constants.clear();
                for (int g : c.constants2.keySet()){
                    c.constants.put(g, c.constants2.get(g));
                }
            }
            
            
            for (int g : c.constants2.keySet()){
                c.constants2.put(g, c.constants2.get(g) + ((Math.random() < 0.5) ? +1 : -1));
                if (c.constants2.get(g) <= 0) c.constants2.put(g, 1);
            }
            if (c.times >= 10) break;
        }
        c.current = new BaseMachine();
        c.current.const1 = c.totals.get(1)/c.times;
        c.current.const2 = c.totals.get(2)/c.times;
        c.current.const3 = c.totals.get(3)/c.times;
        c.current.run = true;
        c.current.disp = true;
        c.current.go();
        while (c.current.isRunning()){
            //Do Nothing
        }
    }
}
