package mlattempt2;

public class GeneticProblemSolver {
    /**
     * Hypothesis (3/22/19):
     *      Any real-life mathematical function can be approximated using:
     *          af(x)=x
     *          bf(x)=x^2
     *          cf(x)=x^0.5
     *          df(x)=sin(Bx)C where B is a very small real number
     *          C, where C is a real number representing the y intercept
     *      This is undoubtedly false, however it may be close enough,
     *      This program will serve to test that theory,
     * 
     * Update (3/24/19):
     *      It seems clear that the above has a flaw, in that its repetitive
     *      nature can only emulate one sine wave, making it essentially usel-
     *      ess for patterns that seem made up of multiple overlapping waves,
     *      In addition, x^2 doesn't seem to be very useful,
     * 
     * Update II (3/24/19):
     *      The problem seems more to be that each solution is the same semi-
     *      linear regression, which isn't very useful; will implement a 
     *      'disable' to force the algorithm to learn in different ways each
     *      time,
     * 
     * Update III (3/24/19):
     *      Disable seems to be a success: Genetic algorithms alone seem unable
     *      to accurately learn the data, yet a combination may be more effec-
     *      tive; Moving on to cleaning the neural network code in order to 
     *      integrate the two,
     * Update (3/25/19):
     *      The neural network performance now that I've revisited it is actua-
     *      lly quite staggering, will return to genetic algorithms later.
     */
    double sigm(double d){
        return (1.0/(1.0+Math.pow(Math.E, -d)));
    }
    public class Cell
    {
        /**
         * Small adjustment factor(s), like neural network's learning rate
         * RES for resolution
         */
        public static final double SMALL_RES = 0.1;
        public static final double BIG_RES = 1.0;
        public double a;
        public double b;
        public double c;
        public double d;
            public double speed;
        public double e;
            public double speed2;
        public double intercept;
        public int disable;
        
        public Cell()
        {
            disable = (int) (Math.random()*5);
            if (disable != 0) a =Math.random()*BIG_RES;
            if (disable != 1) b =Math.random()*BIG_RES;
            if (disable != 2) c =Math.random()*BIG_RES;
            if (disable != 3) d =Math.random()*BIG_RES;
                speed =Math.random()*SMALL_RES;
            if (disable != 4) e =Math.random()*BIG_RES;
                speed2 = Math.random()*SMALL_RES;
            intercept = 0;
        }
        public Cell(Cell one, Cell two)
        {
            if (disable != 0) a =(one.a+two.a)/2.0;
            if (disable != 1) b =(one.b+two.b)/2.0;
            if (disable != 2) c =(one.c+two.c)/2.0;
            if (disable != 3) d =(one.d+two.d)/2.0;
                speed =(one.speed+two.speed)/2.0;
            if (disable != 4) e =(one.e+two.e)/2.0;
                speed2 = (one.speed2+two.speed2)/2.0;
            intercept = one.intercept;
        }
        public Cell(Cell one)
        {
            if (disable != 0) a =one.a + (Math.random() - 0.5)*BIG_RES;
            if (disable != 1) b =one.b + (Math.random() - 0.5)*BIG_RES;
            if (disable != 2) c =one.c + (Math.random() - 0.5)*BIG_RES;
            if (disable != 3) d =one.d + (Math.random() - 0.5)*BIG_RES;
                speed =one.speed + (Math.random() - 0.5)*SMALL_RES;
            if (disable != 4) e =one.e + (Math.random() - 0.5)*BIG_RES;
                speed2 = one.speed2 + (Math.random() - 0.5)*SMALL_RES;
            intercept = one.intercept;
        }
        public double getRes(double input)
        {
            double retVal = 0.0;
            if (disable != 1) retVal += a*input;
            if (disable != 2) retVal += b*Math.pow(input, 2.0);
            if (disable != 3) retVal += c*Math.pow(input, 0.5);
            if (disable != 4) retVal += d*Math.sin(input*speed);
            if (disable != 5) retVal += e*Math.sin(input*speed2);
            retVal += intercept;
            return retVal;
        }
        public void disp()
        {
            System.out.println(a + " * x");
            System.out.println(b + " * x^2");
            System.out.println(c + " * x^0.5");
            System.out.println(d+" * sin( x * "+speed+" )");
            System.out.println(e+" * sin( x * "+speed2+" )");
            System.out.println("Intercept: " + intercept);
        }
        
    }
    
    /**
     * Makes decisions between its subcells
     */
    public class BrainCell extends Cell
    {
        public static final double SMALL_RES = 0.01;
        public static final double BIG_RES = 0.1;
        public double speed3;
        public Cell[] subcells;
        public BrainCell(Cell[] arr)
        {
            super();
            subcells = arr;
            speed3 = Math.random()*SMALL_RES;
        }
        public BrainCell(BrainCell one, BrainCell two)
        {
            subcells = one.subcells;
            a = (one.a+two.a)/2.0;
            b = (one.b+two.b)/2.0;
            c = (one.c+two.c)/2.0;
            d = (one.d+two.d)/2.0;
                speed = (one.speed+two.speed)/2.0;
            e = (one.e+two.e)/2.0;
                speed2 = (one.speed2+two.speed2)/2.0;
            speed3 = (one.speed3+two.speed3)/2.0;
        }
        public BrainCell(BrainCell one)
        {
            subcells = one.subcells;
            a = one.a + (Math.random() - 0.5)*BIG_RES;
            b = one.b + (Math.random() - 0.5)*BIG_RES;
            c = one.c + (Math.random() - 0.5)*BIG_RES;
            d = one.d + (Math.random() - 0.5)*BIG_RES;
                speed = one.speed + (Math.random() - 0.5)*SMALL_RES;
            e = one.e + (Math.random() - 0.5)*BIG_RES;
                speed2 = one.speed2 + (Math.random() - 0.5)*SMALL_RES;
            speed3 = one.speed3 + (Math.random() - 0.5)*SMALL_RES;
        }
        
        @Override
        public double getRes(double input)
        {
            double decision = 0.0;
            decision += a*input;
            decision += b*Math.pow(input, 2.0);
            decision += c*Math.pow(input, 0.5);
            decision += d*Math.sin(input*speed);
            decision += e*Math.sin(input*speed2);
            decision = (0.5+0.5*Math.sin(decision*speed3))*subcells.length;
            return subcells[(int) (decision-0.0000000000001)].getRes(input);
        }
    }
    
    
    
    /**
     * 
     * @param currents cell base to optimize from
     * @param inputs   inputs to optimize from
     * @param outputs  outputs to optimize from
     * @param interval interval at which to select inputs/outputs (stochastic)
     * @return 
     */
    public Cell getBest(Cell[] currents, double[] inputs, double[] outputs, int interval)
    {
        Cell currentBest = new Cell();
        int currentBestIndex = Integer.MAX_VALUE;
        double minDist = Double.MAX_VALUE;
        double avgDist = 0;
        long ttemp;
        ttemp = System.currentTimeMillis();
        while (true)
        {
            // TEST // FIND BEST //
            for (int c = 0; c < currents.length; c++)
            {
                avgDist = 0;
                for (int i = ((int) (Math.random()*interval)); i < inputs.length; i+= interval)
                {
                    avgDist += Math.abs(currents[c].getRes(inputs[i])-outputs[i]) / (inputs.length*1.0);
                }
                if (avgDist < minDist)
                {
                    currentBest = currents[c];
                    currentBestIndex = c;
                    minDist = avgDist;
                }
            }
            // GENETICALLY IMPROVE //
            for (int c = 0; c < currents.length; c++)
            {
                if (c == currentBestIndex) continue;
                if (Math.random() < 0.3) currents[c] = new Cell(currentBest);
                else if (Math.random() < 0.3) currents[c] = new Cell(currents[c]);
                else currents[c] = new Cell(currents[c], currentBest);
            }
            // EXIT CONDITION // 1s //
            if (System.currentTimeMillis()-ttemp > 1000) break;
        }
        return currentBest;
    }
    /**
     * 
     * @param currents cell base to optimize from
     * @param inputs   inputs to optimize from
     * @param outputs  outputs to optimize from
     * @param interval interval at which to select inputs/outputs (stochastic)
     * @return 
     */
    public BrainCell getBest(BrainCell[] currents, double[] inputs, double[] outputs, int interval)
    {
        BrainCell currentBest = new BrainCell(currents[0].subcells);
        int currentBestIndex = Integer.MAX_VALUE;
        double minDist = Double.MAX_VALUE;
        double avgDist = 0;
        long ttemp;
        ttemp = System.currentTimeMillis();
        while (true)
        {
            // TEST // FIND BEST //
            for (int c = 0; c < currents.length; c++)
            {
                avgDist = 0;
                for (int i = ((int) (Math.random()*interval)); i < inputs.length; i+= interval)
                {
                    avgDist += Math.abs(currents[c].getRes(inputs[i])-outputs[i]) / (inputs.length*1.0);
                }
                if (avgDist < minDist)
                {
                    currentBest = currents[c];
                    currentBestIndex = c;
                    minDist = avgDist;
                }
            }
            // GENETICALLY IMPROVE //
            for (int c = 0; c < currents.length; c++)
            {
                if (c == currentBestIndex) continue;
                if (Math.random() < 0.3) currents[c] = new BrainCell(currentBest);
                else if (Math.random() < 0.3) currents[c] = new BrainCell(currents[c]);
                else currents[c] = new BrainCell(currents[c], currentBest);
            }
            // EXIT CONDITION // ONE MINUTE //
            if (System.currentTimeMillis()-ttemp > 60000) break;
        }
        return currentBest;
    }
    
    
    
    public void run()
    {
        // LOAD DJIA
        double[][] split = Loader.loadDJIA();
        double[] inputs = split[0];
        double[] outputs = split[1];
        
        for (int i = 0; i < inputs.length; i++)
            System.out.println(inputs[i] + "\t" + outputs[i]);
        
        // CREATE 10 "GOOD" ORGANISMS //
        
        // Container for best organisms
        Cell[] bests = new Cell[10]; 
        //Current index filled of bests
        int bestSent = 0; 
        //Containers to genetically opti.
        Cell[] currents = new Cell[10]; 
        
        while (bestSent < bests.length)
        {
            //Initialize
            for (int i = 0; i < currents.length; i++)
            {
                currents[i] = new Cell();
                currents[i].intercept = outputs[0];
            }
            Cell currentBest = getBest(currents, inputs, outputs, 1);
            // DISPLAY RESULTS
            System.out.println("Input\tOutput\tRes");
            for (int i = 0; i < inputs.length; i+=10)
                System.out.println(inputs[i]+"\t"+outputs[i]+"\t"+currentBest.getRes(inputs[i]));

            System.out.println("Best Cell paramaters: ");
            currentBest.disp();
            bests[bestSent++] = currentBest;
        }
        
        //COMBINE INTO SIMPLE MULTICELLULAR ORGANISM
        BrainCell[] currentBrains = new BrainCell[10];
        for (int i = 0; i < currentBrains.length; i++)
            currentBrains[i] = new BrainCell(bests);
        BrainCell finalCell = getBest(currentBrains, inputs, outputs, 5);
        System.out.println("Input\tOutput\tRes");
        for (int i = 0; i < inputs.length; i+=10)
        {
            System.out.print(inputs[i]+"\t"+outputs[i]+"\t"+finalCell.getRes(inputs[i]));
            for (int b = 0; b < finalCell.subcells.length; b++)
                System.out.print("\t"+finalCell.subcells[b].getRes(inputs[i]));
            System.out.println();
        }

    }
}
