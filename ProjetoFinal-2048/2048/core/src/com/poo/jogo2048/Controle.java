package com.poo.jogo2048;

public class Controle {
    public void juntaBloco(char direcao, int xIni, int yIni, Tabuleiro tabuleiro)
    {
        int xFim = 0, yFim = 0;
        switch (direcao)
        {
            case 'w':
                xFim = xIni;
                yFim = yIni - 1;
                break;
            case 'a':
                xFim = xIni - 1;
                yFim = yIni;
                break;
            case 's':
                xFim = xIni;
                yFim = yIni + 1;
                break;
            case 'd':
                xFim = xIni + 1;
                yFim = yIni;
                break;
        }

        if(xFim < 0 || xFim >= tabuleiro.getTamanhoX() || yFim < 0 || yFim >= tabuleiro.getTamanhoY()) { // se o bloco estiver nas margens do tabuleiro
            return;
        } else {
            if(tabuleiro.getNumero(xFim, yFim) == 0)
            {
                tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
                tabuleiro.setBloco(xIni, yIni, new Blocos(0));
                juntaBloco(direcao, xFim, yFim, tabuleiro);
            }

            // se o numero for igual
            if(tabuleiro.getNumero(xFim, yFim) == tabuleiro.getNumero(xIni, yIni))
            {
                tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).dobraNumero());
                // depois fazer exceção para os blocos especiais
                tabuleiro.setBloco(xIni, yIni, new Blocos(0));
                return;
            }
        }
    }
}
