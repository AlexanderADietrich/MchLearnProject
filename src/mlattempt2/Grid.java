/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

import java.util.HashMap;

/**
 *
 * @author voice
 */
public class Grid {
    public double reLu(double d)
    {
        return (d < 0) ? 0 : d;
    }
    public class GridNode
    {
        double currentInput = 0.0;
        HashMap<GridNode, Double> links;
        
        public GridNode()
        {
            links = new HashMap<>();
        }
        public void passLinks(GridNode[] links)
        {
            for (GridNode g : links)
                this.links.put(g, Math.random()-0.5);
        }
        public void pullVal()
        {
            currentInput *= 0.3;
            for (GridNode g : links.keySet())
            {
                currentInput += g.links.get(this)*reLu(g.currentInput);
            }
        }
    }
    public GridNode[][] grid;
    public GridUpdater[] updaters;
    public GridNode[] inputNodes;
    public GridNode[] outputNodes;
    public Grid(int size)
    {
        //initialize
        grid = new GridNode[size][size];
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[y].length; x++)
                grid[y][x] = new GridNode();
        //pass links
        int sent = 0;
        GridNode[] temp;
        GridNode[] copy;
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                sent = 0;
                temp = new GridNode[4];
                if (!(x+1 < 0 || x+1 >= size || y < 0 || y >= size))//right
                    temp[sent++] = grid[x+1][y];
                if (!(x < 0 || x >= size || y+1 < 0 || y+1 >= size))//down
                    temp[sent++] = grid[x][y+1];
                if (!(x-1 < 0 || x-1 >= size || y < 0 || y >= size))//left
                    temp[sent++] = grid[x-1][y];
                if (!(x < 0 || x >= size || y-1 < 0 || y-1 >= size))//up
                    temp[sent++] = grid[x][y-1];
                copy = new GridNode[sent];
                for(int i = 0; i < sent; i++)
                    copy[i] = temp[i];
                grid[x][y].passLinks(copy);
            }
        }
        sent = 0;
        updaters = new GridUpdater[size];
        for (GridNode[] list : grid)
        {
            updaters[sent++] = new GridUpdater(list);
        }
        this.inputNodes = new GridNode[size];
        this.outputNodes = new GridNode[size*size];
        for (int r = 0; r < size; r++)
        {
            this.inputNodes[r] = grid[(int)(Math.random()*size)][(int)(Math.random()*size)];
        }
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                outputNodes[y*size + x] = grid[y][x];
    }
    
    public void tick()
    {
        for (GridUpdater g : updaters)
            g.run();
    }
    public void disp()
    {
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                System.out.print(grid[x][y].currentInput + "\t");
            }
            System.out.println();
        }
    }
    public class GridUpdater implements Runnable
    {
        public Thread t;
        public GridNode[] nodes;
        public String ID;
        @Override
        public void run() {
            if (t == null)
            {
                t = new Thread(this, ID);
                t.start();
            }
            for (GridNode g : nodes)
                g.pullVal();
        }
        public GridUpdater(GridNode[] nodes)
        {
            this.nodes = nodes;
            this.ID = Math.random()+"";
        }
    }
}
