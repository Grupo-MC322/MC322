package com.poo.jogo2048;

import com.poo.jogo2048.PastaBlocos.BlocoGenerico;
import com.poo.jogo2048.PastaBlocos.IBlocos;

public class Tabuleiro
{
    private IBlocos[][] matriz;
    private int tamanho;

    public Tabuleiro(int tamanho)
    {
        this.tamanho = tamanho;
        matriz = new IBlocos[tamanho][tamanho];
        for(int i = 0; i < tamanho; i++)
        {
            for(int j = 0; j < tamanho; j++)
            {
                matriz[i][j] = new BlocoGenerico(0);
            }
        }
    }

    public int getTamanho()
    {
        return tamanho;
    }

    public Object getId(int x, int y)
    {
        return matriz[x][y].getId();
    }

    public IBlocos getBloco(int x, int y)
    {
        return matriz[x][y];
    }

    public void setBloco(int x, int y, IBlocos bloco)
    {
        matriz[x][y] = bloco;
    }
}
