package com.poo.jogo2048;

import com.poo.jogo2048.PastaBlocos.IBlocos;

public interface IBoardControl
{
    public int getTamanho();
    public Object getId(int linha, int coluna);
    public IBlocos getBloco(int linha, int coluna);
    public void setBloco(int linha, int coluna, IBlocos bloco);
}