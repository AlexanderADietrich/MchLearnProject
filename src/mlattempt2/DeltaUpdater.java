/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

import mlattempt2.NN.Node;
/**
 * 
 */
public class DeltaUpdater extends Updater{
    public class CalcThread extends Updater.CalcThread
    {
        public CalcThread(Node[] nodes, int start, int stop) {
            super(nodes, start, stop);
        }
        public CalcThread(CalcThread t)
        {
            super(t);
        }
        public void run()
        {
            if (t == null)
            {
                t = new Thread(this, Math.random()+"");
                t.start();
            }
            for (int i = start; i < stop; i++)
                nodes[i].dFeedForward();
        }
    }
    public DeltaUpdater(Node[] nodes) {
        super(nodes);
    }
    
}
