package mlattempt2;
import java.util.Random;
public class BaseMachine {
    public Random r;
    
    public int max;
    public int maxindex;
    public String maxstring;
    
    public int absmax;
    public String absmaxstring;
    
    public long[] months;
    public long[] dates;
    public long[] test;
    
    public  String[] operators;
    
    public  int[] consts;
    
    public boolean disp = true;
    
    public Generator[] generators;
    
    public static final int constslen = 6;
    
    public void setConsts(int[] list){
        consts = list;
    }
    public void genConsts(){
        consts = new int[constslen];
        for (int i = 0; i < constslen; i++){
            consts[i] = r.nextInt(10);
        }
    }
    
    public BaseMachine(){
        r = new Random();
        
        max = 0;
        maxindex = -1;
        maxstring = "";
        
        absmax = 0;
        absmaxstring = "";
        
        months = new long[]{
            //12,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            10,
            10,
            
            
            10, 
            10, 
            10, 
            10, 
            10, 
            10
        };
        
        dates = new long[]{
            //1,
            28,
            24,
            21,
            17,
            14,
            10,
            7,
            3,
            31,
            27,
            
            
            24, 
            20, 
            17, 
            13, 
            10, 
            6
        };
        /*test = new long[]{//high of seattle*1000
            //45,
            50000,
            47000,
            51000,
            48000,
            42000,
            47000,
            49000,
            53000,
            55000,
            56000,
            
            59000, 
            54000, 
            69000,
            59000,
            60000,
            56000,
        };*/
        
        test = new long[]{
            //105847115526L,
            46859691921L,
            563358511118L,
            14233875518L,
            866820525L,
            49624276923L,
            53295573424L,
            502834422625L,
            246521321511L,
            40394772520L,
            1312827194L,
            
            56534521322L, 
            621654576923L, 
            36469685715L,
            146511433215L,
            2760422387L,
            225327671L
        };
        
        operators = new String[]{
            "(digit)",
            "(NothingOne)",
            "(NothingTwo)",
            "(NothingThree)",
            "(NothingFour)",
            "(antidigit)",
            "(antidigit2)",
            "(antidigit3)",
            "(ex0.5)",
            "(ex16)",
            "(ex32)",
            "(ex4)",
            "(M)",
            "(T)",
            "(-M)",
            "(-T)",
            "(M^3)",
            "(T^3)",
            "(-M^3)",
            "(-T^3)",
            "(2^M)",
            "(2^T)",
            "(1.3^(100-M))",
            "(1.3^(100-T))",
            "(100T)",
            "(1000T)",
            "(10000T)",
            "(100000T)",
            "(1000000T)",
            "(-100T)",
            "(-1000T)",
            "(-10000T)",
            "(-100000T)",
            "(-1000000T)",
            "(/100T)",
            "(-/100T)",
            "(100M)",
            "(1000M)",
            "(10000M)",
            "(100000M)",
            "(1000000M)",
            "(-100M)",
            "(-1000M)",
            "(-10000M)",
            "(-100000M)",
            "(-1000000M)",
            "(/100M)",
            "(-/100M)",
            "(sqrt)",
            "(ex2)",
            "(0.5)",
            "(2)",
            "(:1)",
            "(:-1)",
            "(:10)",
            "(:-10)",
            "(:100)",
            "(:-100)",
            "(:1000)",
            "(:-1000)",
            "(:10000)",
            "(:-10000)",
            "(:100000)",
            "(:-100000)",
            "(:1000000)",
            "(:-1000000)",
            "(:10000000)",
            "(:-10000000)",
            "(:100000000)",
            "(:-100000000)",
            "(:1000000000)",
            "(:-1000000000)",
            "(:10000000000)",
            "(:-10000000000)",
        };
        generators = new Generator[10];
        for (int i = 0; i < generators.length; i++){
            generators[i] = new Generator(this, 60);
        }
    }
    public  String makeSmartTree(int n){
        /*
        n=1
        num1
            
            num1
        
        n=2
        num1<num2<num3
        
            num2
        num1    num3
        
        n=3
        num1<num2<num3<num4<num5<num6<num7
        
                            num4
                    num2            num6
                num1    num3    num5    num7
        
        n=4
        1<2<3<4<5<6<7<8<9<10<11<12<13<14<15
        
                    8
                4       12
              2   6   10  14
             1 3 5 7 9  11  15
        
        general
        n=n
        number of numbers: 2^n -1
        
        
        
        
        
        
        */
        
        if (n == 0) return "";
        int b = (int)(Math.pow(2, n)-1);
        int[] indexarr = new int[b];
        long[] numarray = new long[b];
        int sent = 0;
        for (int i = 1; i <= n; i++){
            int num = (int)(Math.pow(2, i));
            for (int c = 1; c < num; c+=2)
                indexarr[sent++] = (b*c)/num;
        }
        
        long max;
        long current;
        sent = 0;
        current = 0;
        max = Long.MIN_VALUE;
        while (true){
            current = current + Math.abs(r.nextInt()*10000);
            if (current > max){
                numarray[sent++] = current;
                max = current;
            }
            if (sent >= numarray.length) break;
        }
        
        String retVal = "(y";
        
        
        sent = 0;
        long[] rearrange = new long[numarray.length];
        for (int inte : indexarr){
            rearrange[inte] = numarray[sent++];
        }
        for (long inte: rearrange){
            retVal = retVal + inte + "#";
        }
        retVal = retVal + "y";
        
        String[] res = new String[(int)(Math.pow(2, n-1))+1];
        for (int c = 0; c < res.length; c++){
            res[c] = operators[(int) (operators.length*Math.random())];
            retVal = retVal + res[c] + "#";
        }
        retVal = retVal + "y" + n + "y)";
        
        
        
        
        
        return retVal;
    }    
    public  String handleSmartTree(long val, String smartTree){
        smartTree = smartTree.substring(2, smartTree.length()-1);
        String[]    splitter = smartTree.split("y");
        
        String[]    sNumarray = splitter[0].split("#");
        long[]      numarray = new long[sNumarray.length];
        for (int i = 0; i < sNumarray.length; i++){
            numarray[i] = Long.parseLong(sNumarray[i]);
        }
        String[]    resarray = splitter[1].split("#");
        
        
        int n = Integer.parseInt(splitter[2]);
        int sent = 1;
        int times = 0;
        long current;
        while (true){
            current = numarray[sent-1];
            times++;
            if (!(times < n)) break;
            if (val < current) sent = sent*2;
            else sent = 1 + (sent*2);
        }
        
        if (val < current){
            return resarray[sent- ((int)(Math.pow(2, n-1)))];
        } else {
            return resarray[sent- ((int)(Math.pow(2, n-1)))+1];
        }        
    }
    public void dispMax(){
        System.out.println("DISPLAYING MAX: ");
        System.out.println(max);
        System.out.println(maxindex);
        System.out.println(maxstring);
        System.out.println();
    }
    /**
     * Displays the current absolute max generator's reward, command list, and values.
     */
    public void dispAbsMax(){
        System.out.println("DISPLAYING ABSMAX: ");
        System.out.println(absmax);
        System.out.println(absmaxstring);
        Generator g = new Generator(this, absmaxstring);
        g.update();
        for (int c = 0; c < dates.length; c++){
            System.out.println("Date:  \t" + dates[c]);
            System.out.println("Winner:\t" + test[c]);
            System.out.println("Val:   \t" + g.vals[c]);
            System.out.println();
        }
        System.out.println();
    }
    /**
     * Displays the generators' command lists, their rewards, and their values.
     */
    public void dispGenerators(){
        System.out.println("DISPLAYING GENERATORS");
        for (Generator gen : generators){
            System.out.println(gen.commands);
            System.out.println(gen.reward);
            gen.update();
            for (long v : gen.vals){
                System.out.println("\t" + v);
            }
        }
        System.out.println();
    }
    
    /**
     * Goes through a number of runs equal to times, mimicking selection
     * processes in a digital environment.
     * @param times the number of times to be run.
     */
    public void run(int times, int maxSame){
        int sent = -1;
        int numSame = 0;
        while (++sent < times){
            if (disp) System.out.println("Run " + sent + " HighestLen " + absmaxstring.split(" ").length);
            
            //Determine reward, max, and absmax.
            String temp = maxstring;
            int sent2 = 0;
            for (Generator gen : generators){
                gen.reward = detReward(gen);
                if (gen.reward > max){
                    max = gen.reward;
                    maxindex = sent2;
                    maxstring = gen.commands;
                }
                if (gen.reward > absmax){
                    absmax = gen.reward;
                    absmaxstring = gen.commands;
                }
                sent2++;
            }
            if (maxstring.equals(temp)) numSame++;
            else numSame = 0;
            if (numSame == maxSame) break;

            for (int i = 0; i < generators.length; i++){
                if (i != maxindex && r.nextBoolean()){
                    if (i == generators.length-1){
                        generators[i] = combine(generators[i], generators[0]);
                    }
                    else {
                        generators[i] = combine(generators[i], generators[i+1]);   
                    }
                }
                
                if (generators[i].reward < max){
                    for (int b = 0; b < 5; b++){
                        int decision = r.nextInt(consts[0]+consts[1]+consts[2]+consts[3]+consts[4]+consts[5]);
                        if      (decision > consts[1]+consts[2]+consts[3]+consts[4]+consts[5]) generators[i].mutate();
                        else if (decision > consts[2]+consts[3]+consts[4]+consts[5]) generators[i].intermutate();
                        else if (decision > consts[3]+consts[4]+consts[5]) generators[i].delmutate();
                        else if (decision > consts[4]+consts[5]) generators[i].addTree(r.nextInt(5));
                        else if (decision > consts[5]) generators[i].mutateTreeOperators();
                        else if (decision >= 0) generators[i].shiftTree();
                    }
                }
            }
            
            
            
            
            /*
            for (int b = 0; b < consts[6]; b++){
                int decision = r.nextInt(consts[0]+consts[1]+consts[2]+consts[3]+consts[4]+consts[5]);
                if      (decision > consts[1]+consts[2]+consts[3]+consts[4]+consts[5]) generators[i].mutate();
                else if (decision > consts[2]+consts[3]+consts[4]+consts[5]) generators[i].intermutate();
                else if (decision > consts[3]+consts[4]+consts[5]) generators[i].delmutate();
                else if (decision > consts[4]+consts[5]) generators[i].addTree(r.nextInt(10));
                else if (decision > consts[5]) generators[i].mutateTreeOperators();
                else if (decision >= 0) generators[i].shiftTree();
            }
            */
        }
        if (disp){
            dispGenerators();
            dispMax();
            dispAbsMax();
        }
    }
    /**
     * Imitate real life reproduction of organisms
     * @param gen1
     * @param gen2
     * @return the 'child' of gen1 and gen2
     */
    public Generator combine(Generator gen1, Generator gen2){
        String temp = "";
        int half;
        
        String[] arrtemp1 = gen1.commands.split(" ");
        String[] arrtemp2 = gen2.commands.split(" ");
        int maxlen = (arrtemp1.length > arrtemp2.length) ? arrtemp1.length : arrtemp2.length;
        int minlen = (arrtemp1.length < arrtemp2.length) ? arrtemp1.length : arrtemp2.length;
        int sent = 0;
        while (sent < minlen){
            if (r.nextBoolean()){
                temp = temp + arrtemp1[sent++] + " ";
            } else {
                temp = temp + arrtemp2[sent++] + " ";
            }
        }
        Generator retVal = new Generator(this, temp);
        return retVal;
    }
    /**
     * Imitate real life reproduction of organisms
     * @param gen1commands
     * @param gen2commands
     * @return the 'child' of gen1 and gen2
     */
    public Generator combine(String gen1commands, String gen2commands){
        String temp = "";
        int half;
        
        String[] arrtemp1 = gen1commands.split(" ");
        String[] arrtemp2 = gen2commands.split(" ");
        int maxlen = (arrtemp1.length > arrtemp2.length) ? arrtemp1.length : arrtemp2.length;
        int minlen = (arrtemp1.length < arrtemp2.length) ? arrtemp1.length : arrtemp2.length;
        int sent = 0;
        while (sent < minlen){
            if (r.nextBoolean()){
                temp = temp + arrtemp1[sent++] + " ";
            } else {
                temp = temp + arrtemp2[sent++] + " ";
            }
        }
        while (sent < maxlen){
            if (sent < arrtemp1.length){
                temp = temp + arrtemp1[sent++] + " ";
            } else {
                temp = temp + arrtemp2[sent++] + " ";
            }
        }
        Generator retVal = new Generator(this, temp);
        retVal.delmutate(maxlen-minlen);
        return retVal;
    }
    
    /**
     * Determines the reward for a Generator
     * @param gen the generator to be analyzed
     * @return the generator's reward
     */
    public  int detReward(Generator gen){
        int retVal = 1000000;
        long currentVal;
        for (int c = 0; c < test.length; c++){
            currentVal = gen.getVal(c);

            retVal -= (int) (1000*Math.log(((Math.abs(currentVal-test[c]))+1)));
            
            if (currentVal < 0) retVal -= 10000;//discount negative answers 

            if (test[c] == currentVal){
                //System.out.println("L11 of detReward(...) of BaseMachineII");
                retVal+= 100000;
            }
        }
        return retVal;
    }
    
}
