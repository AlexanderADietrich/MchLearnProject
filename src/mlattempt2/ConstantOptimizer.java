package mlattempt2;

import java.util.HashMap;

public class ConstantOptimizer implements Runnable{

    //Impossible to have an array of hashmaps otherwise
    private class Container{
        private HashMap<Integer, Integer> c;
        public Container(){
            
        }
    }
    
    public BaseMachine getCurrent(){
        return this.current;
    }
    
    public Container[][] constants;//sets of constants
    public BaseMachine current;//Base Machine to optimize
    
    public Container[] totals;//The totals from the best set
    public int times = 0;//Counter until done optimizing
    
    public  int nsetsets = 10;
    public  int nstages = 2;
    public  int nconstants = 6;
    
    public  int maxindex = 0;
    public  int[] temp = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    
    
    public ConstantOptimizer(){
        constants = new Container[10][nstages];
        totals      = new Container[nstages];
        current = new BaseMachine();
        /*
        1   |   c1  c2
        2   |   c1  c2
        3   |   c1  c2
        4   |   c1  c2
        5   |   c1  c2
        6   |   c1  c2
        7   |   c1  c2 
        8   |   c1  c2 
        9   |   c1  c2  
        10  |   c1  c2  
        */
        
        
        
        
        
        for (int b = 0; b < nsetsets; b++){
            constants[b]   = new Container[nconstants];
        }
        
        for (int i = 0; i < nstages; i++){
            totals[i] = new Container();
            totals[i].c = new HashMap<>();
        }
        
        for (int b = 0; b < nsetsets; b++){
            for (int i = 0; i < nconstants; i++){//initialize
                constants[b][i]    = new Container();
                //totals[i]       = new Container();

                constants[b][i].c      = new HashMap<>();//current "best"
                //totals[i].c         = new HashMap<>();
            }
        }
        
        for (int g = 1; g <= nconstants; g++){//initialize (g=constants b=sets i=stages)
            for (int b = 0; b < nsetsets; b++){
                for (int i = 0; i < nstages; i++){
                    totals[i].c.put(g, 0);
                    constants[b][i].c.put(g, 1);
                }
            }
        }
    }
    
    
    public void run(){
        ConstantOptimizer c = new ConstantOptimizer();
        c.current = new BaseMachine();
        while (true){
            //reset total rewards
            temp = new int[nsetsets];
            
            //Test all sets
            for (int b = 0; b < nsetsets; b++){
                //Test 10 times for reliability
                for (int r = 0; r < 1; r++){
                    //Reset the base machine fully
                    try 
                    {
                        c.current.kill();
                    }
                    catch (Exception ex)
                    {
                        System.out.println(b + " " + r + " " + (c.current == null));
                    }
                    c.current.resetTimeVars();
                    c.current = null;
                    c.current = new BaseMachine();

                    for (int i = 0; i < nstages; i++){
                        //System.out.println("Set " + b);
                        //System.out.println("Stage " + i);
                        
                        c.current.consts = new int[c.constants[b][i].c.size()];
                        for (int g : c.constants[b][i].c.keySet()){
                            //System.out.println("b " + b + " i " + i + " g " + g);
                            c.current.consts[g-1] = c.constants[b][i].c.get(g);
                        }
                        //System.out.println();

                        c.current.runs = 10;
                        c.current.go();
                        while (c.current.isRunning()){
                            //Do Nothing
                        }
                        c.temp[b] += c.current.getReward();
                    }
                }
            }
            long max = 0;
            maxindex = 0;
            for (int b = 0; b < temp.length; b++){
                if (max < temp[b]){
                    maxindex = b;
                    max = temp[b];
                }
            }
            
            
            //display
            
            //increment the times
            c.times++;
            
            //Only count the last 50 
            if (c.times > 49){
                //display II / add to totals
                for (int i = 0; i < nstages; i++){

                    System.out.print("Avg for stage " + i + ":\t");

                    for (int g : c.constants[maxindex][i].c.keySet()){
                        c.totals[i].c.put(g, c.totals[i].c.get(g)+c.constants[maxindex][i].c.get(g));

                        System.out.print("\t" + (c.totals[i].c.get(g)/(c.times-49)));

                    }

                    System.out.println();

                }

                System.out.println();
            }
            
            
            
            //mod all sets other than max
            for (int b = 0; b < nstages; b++){
                if (b != maxindex){
                    for (int i = 0; i < nconstants; i++){
                        for (int g : c.constants[b][i].c.keySet()){
                            c.constants[b][i].c.put(g, c.constants[b][i].c.get(g) + ((Math.random() < 0.5) ? +1 : -1));
                            if (c.constants[b][i].c.get(g) <= 0) c.constants[b][i].c.put(g, 1);
                        }
                    }
                }
            }
            
            /*boolean breaker = false;
            for (int g : c.constants[0].c.keySet()){
                for (int b = 0; b < 5; b++){
                    if (Math.random() < 0.2){
                        c.constants2[b].c.put(g, c.constants2[b].c.get(g) + ((Math.random() < 0.5) ? +1 : -1));
                        breaker = true;
                        
                    }
                    if (c.constants2[b].c.get(g) <= 0) c.constants2[b].c.put(g, 1);
                    if (breaker) break;
                }
                if (breaker) break;
            }*/
            
            //if optimizing is 'complete' break
            if (c.times >= 100) break;
            System.out.println("(" + c.times + "/100)"); 
            
        }
        c.current.kill();
        c.current.resetTimeVars();
        c.current = null;
        c.current = new BaseMachine();
        c.current.disp = true;
        c.current.initGUI();

        boolean flop = true;
        while (true){
            for (int b = 0; b < nstages; b++){
                System.out.println(c.current == null);
                System.out.println("Set " + b);
                c.current.consts = new int[c.totals[b].c.size()];
                for (int g : c.totals[b].c.keySet()){
                    c.current.consts[g-1] = c.totals[b].c.get(g)/(c.times-50);
                }
            
            
                c.current.runs = 10000/nstages;
                c.current.go();
                while (c.current.isRunning()){
                    if (c.current.flag){
                        break;
                    }
                }
                if (c.current.flag){
                    b = -1;
                    c.current.flag = false;
                    continue;
                }
                System.out.println("Big Flop: " + c.current.bigflop);
                flop = !flop;
            }
        }
    }
    
}
