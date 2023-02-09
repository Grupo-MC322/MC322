package com.poo.game2048;

import com.poo.game2048.Blocks.IBlocks;
import com.poo.game2048.Blocks.NumBlock;

public class Board implements IBoardControl
{
    private IBlocks[][] matrix;
    private int size;

    public Board(int size)
    {
        this.size = size;
        matrix = new IBlocks[size][size];
        for(int vertical = 0; vertical < size; vertical++)
        {
            for(int horizontal = 0; horizontal < size; horizontal++)
            {
                matrix[vertical][horizontal] = new NumBlock(0);
            }
        }
    }

    public int getSize()
    {
        return size;
    }

    public Object getId(int vertical, int horizontal)
    {
        return matrix[vertical][horizontal].getId();
    }

    public IBlocks getBlock(int vertical, int horizontal)
    {
        return matrix[vertical][horizontal];
    }

    public void setBlock(int vertical, int horizontal, IBlocks block)
    {
        matrix[vertical][horizontal] = block;
    }
}
