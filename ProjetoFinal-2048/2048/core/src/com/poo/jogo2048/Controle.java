package com.poo.jogo2048;

import com.poo.jogo2048.PastaBlocos.*;

public class Controle {
    
    private BlocoBomba blocoBomba;
    private boolean blocoBombaAtiva = false;

    private BlocoTempo blocoTempo;
    private boolean blocoTempoAtivo = false;

    private int xFim = 0;
    private int yFim = 0;
    
    public void realizaComando(char direcao, int xIni, int yIni, Tabuleiro tabuleiro)
    {
        planejaMovimento(direcao, xIni, yIni);

        if(xFim >= 0 && xFim < tabuleiro.getTamanhoX() && yFim >= 0 && yFim < tabuleiro.getTamanhoY())
        {
            movimenta(direcao, xIni, yIni, tabuleiro);
            if(blocoBombaAtiva)
            {
                atualizaVidas(xIni, yIni, blocoBomba);
                if(blocoBomba.getVida() == 0)
                {
                    blocoBombaAtiva = false;
                    miraVizinhos(tabuleiro, blocoBomba.getCoordX(), blocoBomba.getCoordY());
                }
            }
            if (blocoTempoAtivo)
            {
                atualizaVidas(xIni, yIni, blocoTempo);
                if (blocoTempo.getVida() == 0)
                {
                    blocoTempoAtivo = false;
                    tabuleiro.setBloco(blocoTempo.getCoordX(), blocoTempo.getCoordY(), new BlocoGenerico(0));    
                }
            }
        }
    }

    private void planejaMovimento(char direcao, int xIni, int yIni)
    {
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
    }

    private void movimenta(char direcao, int xIni, int yIni, Tabuleiro tabuleiro)
    {
        // quando está vazio na frente, livre para continuar se movendo
        if(tabuleiro.getId(xFim, yFim) == (Object) 0)
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            realizaComando(direcao, xFim, yFim, tabuleiro);
        }

        // quando ambos os blocos são iguais e podem se juntar
        else if(tabuleiro.getId(xFim, yFim) == tabuleiro.getId(xIni, yIni))
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
        }

        // quando o bloco deleta vai deletar o outro
        else if((tabuleiro.getId(xFim, yFim) == "deleta" && tabuleiro.getId(xIni, yIni) != (Object) 0) 
        || (tabuleiro.getId(xIni, yIni) == "deleta" && tabuleiro.getId(xFim, yFim) != (Object) 0))
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, new BlocoGenerico(0));
        }

        // quando o bloco dobro (que está no destino do movimento) vai dobrar o outro
        else if(tabuleiro.getId(xFim, yFim) == "x2" && tabuleiro.getId(xIni, yIni) != (Object) 0)
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni).junta());
        }
        
        // quando o bloco dobro (que está na origem do movimento) vai dobrar o outro
        else if(tabuleiro.getId(xIni, yIni) == "x2" && tabuleiro.getId(xFim, yFim) != (Object) 0)
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
        }
    }

    public void setAtivo(BlocoBomba blocoBomba)
    {
        blocoBombaAtiva = true;
        this.blocoBomba = blocoBomba;
    }

    public void setAtivo(BlocoTempo blocoTempo)
    {
        blocoTempoAtivo = true;
        this.blocoTempo = blocoTempo;
    }

    public boolean getBlocoBombaAtiva()
    {
        return blocoBombaAtiva;
    }

    public boolean getBlocoTempoAtivo()
    {
        return blocoTempoAtivo;
    }

    private void atualizaVidas(int xIni, int yIni, IBlocosTimer bloco)
    {
        bloco.setVida(-1);
        bloco.setCoordX(bloco.getCoordX() + (xFim - xIni));
        bloco.setCoordY(bloco.getCoordY() + (yFim - yIni));
    }

    private void miraVizinhos(Tabuleiro tabuleiro, int xExplosao, int yExplosao)
    {
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        yExplosao--;
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);
        
        xExplosao++;
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);

        xExplosao++;
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);

        yExplosao++;
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);

        yExplosao++;
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);

        yExplosao--;
        explodeblocoBomba(tabuleiro, xExplosao, yExplosao);
    }

    private void explodeblocoBomba(Tabuleiro tabuleiro, int xExplosao, int yExplosao)
    {
        if(xExplosao >= 0 && xExplosao < tabuleiro.getTamanhoX() && yExplosao >= 0 && yExplosao < tabuleiro.getTamanhoY())
        {
            tabuleiro.setBloco(xExplosao, yExplosao, new BlocoGenerico(0));
        }
    }
}
