package com.poo.jogo2048;

import com.poo.jogo2048.PastaBlocos.BlocoGenerico;

public class Tabuleiro
{
    private BlocoGenerico[][] matriz;
    private int tamanhoX, tamanhoY;

    public Tabuleiro(int tamanhoX, int tamanhoY)
    {
        this.tamanhoX = tamanhoX;
        this.tamanhoY = tamanhoY;
        matriz = new BlocoGenerico[tamanhoX][tamanhoY];
        for(int i = 0; i < tamanhoX; i++)
        {
            for(int j = 0; j < tamanhoY; j++)
            {
                matriz[i][j] = new BlocoGenerico(0);
            }
        }
    }

    public int getTamanhoX()
    {
        return tamanhoX;
    }

    public int getTamanhoY()
    {
        return tamanhoY;
    }

    public Object getId(int x, int y)
    {
        return matriz[x][y].getId();
    }

    public BlocoGenerico getBloco(int x, int y)
    {
        return matriz[x][y];
    }

    public void setBloco(int x, int y, BlocoGenerico bloco)
    {
        matriz[x][y] = bloco;
    }
}
