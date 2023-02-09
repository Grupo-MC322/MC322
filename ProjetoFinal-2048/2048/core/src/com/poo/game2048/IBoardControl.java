package com.poo.game2048;

import com.poo.game2048.Blocks.IBlocks;

public interface IBoardControl
{
    public int getSize();
    public Object getId(int vertical, int horizontal);
    public IBlocks getBlock(int vertical, int horizontal);
    public void setBlock(int vertical, int horizontal, IBlocks block);
}