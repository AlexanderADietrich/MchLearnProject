/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;
import mlattempt2.NN.Node;

/** 
 * Multi-threading calculator
 */
public class Updater {
    public static final int THREADS = 4;
    public Node[] nodes;
    public class CalcThread implements Runnable
    {
        public Thread t;
        public CalcThread(Node[] nodes, int start, int stop)
        {
            this.nodes = nodes;
            this.start = start;
            this.stop = stop;
        }
        public CalcThread(CalcThread t)
        {
            this.nodes = t.nodes;
            this.start = t.start;
            this.stop = t.stop;
        }
        public Node[] nodes;
        public int start;
        public int stop;

        
        public void run()
        {
            if (t == null)
            {
                t = new Thread(this, Math.random()+"");
                t.start();
            }
            for (int i = start; i < stop; i++)
                nodes[i].feedForward();
        }
    }
    public Thread[] threads;
    public CalcThread[] calcThreads;
    public Updater(Node[] nodes)
    {
        this.nodes = nodes;
        calcThreads = new CalcThread[THREADS];
        threads = new Thread[THREADS];
        if (nodes.length < THREADS)
        {
            threads = new Thread[1];
            calcThreads = new CalcThread[1];
            calcThreads[0] = new CalcThread(nodes, 0, nodes.length);
        }
        else
        {
            for (int i = 0; i < calcThreads.length; i++)
            {
                calcThreads[i] = new CalcThread(nodes, i*nodes.length/THREADS, (i+1)*nodes.length/THREADS);
            }
            calcThreads[calcThreads.length-1].stop = nodes.length;
        }
    }
    public void run()
    {
        for (int b = 0; b < calcThreads.length; b++)
        {
            calcThreads[b].run();
        }
        boolean breaker = false;
        while (true)
        {
            breaker = true;
            for (CalcThread c : calcThreads)
                if (c.t.isAlive())
                    breaker = false;
            if (breaker) break;
        }
    }
}
