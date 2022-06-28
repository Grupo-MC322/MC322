package com.poo.jogo2048;

import com.poo.jogo2048.PastaBlocos.BlocoGenerico;
import com.poo.jogo2048.PastaBlocos.IBlocos;

public class Tabuleiro implements IBoardControl
{
    private IBlocos[][] matriz;
    private int tamanho;

    public Tabuleiro(int tamanho)
    {
        this.tamanho = tamanho;
        matriz = new IBlocos[tamanho][tamanho];
        for(int linha = 0; linha < tamanho; linha++)
        {
            for(int coluna = 0; coluna < tamanho; coluna++)
            {
                matriz[linha][coluna] = new BlocoGenerico(0);
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

    public IBlocos getBloco(int linha, int coluna)
    {
        return matriz[linha][coluna];
    }

    public void setBloco(int linha, int coluna, IBlocos bloco)
    {
        matriz[linha][coluna] = bloco;
    }
}
