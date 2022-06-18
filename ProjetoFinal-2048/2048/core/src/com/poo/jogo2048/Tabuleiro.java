package com.poo.jogo2048;

public class Tabuleiro
{
    private Blocos[][] matriz;
    private int tamanhoX, tamanhoY;

    public Tabuleiro(int tamanhoX, int tamanhoY)
    {
        this.tamanhoX = tamanhoX;
        this.tamanhoY = tamanhoY;
        matriz = new Blocos[tamanhoX][tamanhoY];
        for(int i = 0; i < tamanhoX; i++)
        {
            for(int j = 0; j < tamanhoY; j++)
            {
                matriz[i][j] = new Blocos(0);
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

    public int getNumero(int x, int y)
    {
        return matriz[x][y].getNumero();
    }

    public Blocos getBloco(int x, int y)
    {
        return matriz[x][y];
    }

    public void setBloco(int x, int y, Blocos bloco)
    {
        matriz[x][y] = bloco;
    }
}
