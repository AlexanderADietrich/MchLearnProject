package mlattempt2;
import java.util.Random;
public class BaseMachineII {
    public Random r;
    
    public int max;
    public int maxindex;
    public String maxstring;
    
    public int absmax;
    public String absmaxstring;
    
    public long[] dates;
    public long[] winners;
    
    public  String[] operators;
    
    public  int[] consts;
    
    public GeneratorII[] generators;
    
    public BaseMachineII(){
        r = new Random();
        
        max = 0;
        maxindex = -1;
        maxstring = "";
        
        absmax = 0;
        absmaxstring = "";
        
        dates = new long[]{
            2410, 
            2010, 
            1710, 
            1310, 
            1010, 
            610
        };
        winners = new long[]{
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
            "(T)",
            "(-T)",
            "(T^3)",
            "(-T^3)",
            "(2^(sqrtT))",
            "(1.3^(sqrt(10000-T))",
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
        generators = new GeneratorII[10];
        for (int i = 0; i < generators.length; i++){
            generators[i] = new GeneratorII(this, 60);
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
    public void dispAbsMax(){
        System.out.println("DISPLAYING ABSMAX: ");
        System.out.println(absmax);
        System.out.println(absmaxstring);
        GeneratorII g = new GeneratorII(this, absmaxstring);
        g.update();
        for (int c = 0; c < dates.length; c++){
            System.out.println("Date:  \t" + dates[c]);
            System.out.println("Winner:\t" + winners[c]);
            System.out.println("Val:   \t" + g.vals[c]);
            System.out.println();
        }
        System.out.println();
    }
    
    public void dispGenerators(){
        System.out.println("DISPLAYING GENERATORS");
        for (GeneratorII gen : generators){
            System.out.println(gen.commands);
            System.out.println(gen.reward);
            gen.update();
            for (long v : gen.vals){
                System.out.println("\t" + v);
            }
        }
        System.out.println();
    }
    public void run(int times){
        int sent = -1;
        long temp = System.currentTimeMillis();
        while (++sent < times){
            System.out.println("Run " + sent);
            /*if (maxstring.split(" ").length > 0) System.out.println(" (" + (System.currentTimeMillis()-temp) + "/" + maxstring.split(" ").length + ")=" + ((System.currentTimeMillis()-temp)/maxstring.split(" ").length));
            else System.out.println();*/
            temp = System.currentTimeMillis();
            //Determine reward, max, and absmax.
            int sent2 = 0;
            for (GeneratorII gen : generators){
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
            
            
            
            //Combine and introduce mutations.
            for (int i = 0; i < generators.length; i++){
                if (i != maxindex){
                    if (i < generators.length-1){
                        generators[i] = combine(generators[i], generators[i+1]);
                    } else {
                        generators[i] = combine(generators[i], generators[0]);
                    }
                    generators[i].reward = detReward(generators[i]);
                    
                    for (int b = 0; b < 3; b++){
                        int decision = r.nextInt(120);
                        if      (decision > 100) generators[i].mutate();
                        else if (decision > 80) generators[i].intermutate();
                        else if (decision > 75) generators[i].delmutate();
                        else if (decision > 30) generators[i].addTree(r.nextInt(6));
                        else if (decision > 20) generators[i].mutateTreeOperators();
                        else if (decision >= 0) generators[i].shiftTree();
                    }
                }
            }
        }
        dispGenerators();
        dispMax();
        dispAbsMax();
    }
    /**
     * Imitate real life reproduction of organisms
     * @param gen1
     * @param gen2
     * @return 
     */
    public GeneratorII combine(GeneratorII gen1, GeneratorII gen2){
        String temp = "";
        int half;
        
        String[] arrtemp = gen1.commands.split(" ");
        if (arrtemp.length > 0){
            half = arrtemp.length/2;
            for (int i = 0; i < half; i++){
                temp = temp + arrtemp[i] + " ";
            }
        }
        
        arrtemp = gen2.commands.split(" ");
        if (arrtemp.length > 0){
            half = arrtemp.length/2;
            for (int i = half; i < arrtemp.length; i++){
                temp = temp + arrtemp[i] + " ";
            }
        }
        
        GeneratorII retVal = new GeneratorII(this, temp);
        
        

        
        return retVal;
    }
    
    public  int detReward(GeneratorII gen){
        int retVal = 1000000;
        long currentVal;
        
        for (int c = 0; c < winners.length; c++){
            currentVal = gen.getVal(c);

            retVal -= (int) (1000*Math.log(((Math.abs(currentVal-winners[c]))+1)));
            
            if (currentVal < 0) retVal -= 10000;//discount negative answers 

            if (winners[c] == currentVal){
                System.out.println("L11 of detReward(...) of BaseMachineII");
                retVal+= 10;
            }
        }
        return retVal;
        
    }
    
}
