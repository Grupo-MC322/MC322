package com.poo.jogo2048;

import com.poo.jogo2048.PastaBlocos.NumBlock;
import com.poo.jogo2048.PastaBlocos.IBlocks;

public class Board implements IBoardControl
{
    private IBlocks[][] matriz;
    private int tamanho;

    public Board(int tamanho)
    {
        this.tamanho = tamanho;
        matriz = new IBlocks[tamanho][tamanho];
        for(int linha = 0; linha < tamanho; linha++)
        {
            for(int coluna = 0; coluna < tamanho; coluna++)
            {
                matriz[linha][coluna] = new NumBlock(0);
            }
        }
    }

    public int getTamanho()
    {
        return tamanho;
    }

    public Object getId(int linha, int coluna)
    {
        return matriz[linha][coluna].getId();
    }

    public IBlocks getBloco(int linha, int coluna)
    {
        return matriz[linha][coluna];
    }

    public void setBloco(int linha, int coluna, IBlocks bloco)
    {
        matriz[linha][coluna] = bloco;
    }
}
