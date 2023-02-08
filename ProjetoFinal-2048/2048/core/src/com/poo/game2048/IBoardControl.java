package com.poo.game2048;

import com.poo.game2048.Blocks.IBlocks;

public interface IBoardControl
{
    public int getTamanho();
    public Object getId(int linha, int coluna);
    public IBlocks getBloco(int linha, int coluna);
    public void setBloco(int linha, int coluna, IBlocks bloco);
}