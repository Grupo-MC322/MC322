import PastaBlocos.*;

public class Controle
{
    private IBlocosVidas bomba;
    private boolean bombaAtiva = false;

    private IBlocosVidas preto;
    private boolean pretoAtivo = false;

    private int xFim = 0;
    private int yFim = 0;
    
    public void realizaComando(char direcao, int xIni, int yIni, Tabuleiro tabuleiro)
    {
        planejaMovimento(direcao, xIni, yIni);

        if(xFim >= 0 && xFim < tabuleiro.getTamanhoX() && yFim >= 0 && yFim < tabuleiro.getTamanhoY())
        {
            movimenta(direcao, xIni, yIni, tabuleiro);
            if(bombaAtiva)
            {
                atualizaVidas(xIni, yIni, bomba);
                if(bomba.getvida() == 0)
                {
                    bombaAtiva = false;
                    miraVizinhos(tabuleiro, bomba.getCoordX(), bomba.getCoordy());
                }
            }
            if (pretoAtivo)
            {
                atualizaVidas(xIni, yIni, preto);
                if (preto.getvida() == 0)
                {
                    pretoAtivo = false;
                    tabuleiro.setBloco(preto.getCoordX(), preto.getCoordY(), new BlocoGenerico(0));    
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
        if(tabuleiro.getId(xFim, yFim) == 0)
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
        else if((tabuleiro.getId(xFim, yFim) == "deleta" && tabuleiro.getId(xIni, yIni) != 0) 
        || (tabuleiro.getId(xIni, yIni) == "deleta" && tabuleiro.getId(xFim, yFim) != 0))
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, new BlocoGenerico(0));
        }

        // quando o bloco dobro (que está no destino do movimento) vai dobrar o outro
        else if(tabuleiro.getId(xFim, yFim) == "x2" && tabuleiro.getId(xIni, yIni) != 0)
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni).junta());
        }
        
        // quando o bloco dobro (que está na origem do movimento) vai dobrar o outro
        else if(tabuleiro.getId(xIni, yIni) == "x2" && tabuleiro.getId(xFim, yFim) != 0)
        {
            tabuleiro.setBloco(xIni, yIni, BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
        }
    }

    public void setAtivo(Bomba bomba)
    {
        bombaAtiva = true;
        this.bomba = bomba;
    }

    public void setAtivo(Preto preto)
    {
        pretoAtivo = true;
        this.preto = preto;
    }

    public boolean getBombaAtiva()
    {
        return bombaAtiva;
    }

    public boolean getPretoAtivo()
    {
        return pretoAtivo;
    }

    private void atualizaVidas(int xIni, int yIni, IBlocosVidas bloco)
    {
        bloco.setVida(-1);
        bloco.setCoordX(bloco.getCoordX() + (xFim - xIni));
        bloco.setCoordY(bloco.getCoordY() + (yFim - yIni));
    }

    private void miraVizinhos(Tabuleiro tabuleiro, int xExplosao, int yExplosao)
    {
        explodeBomba(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        yExplosao--;
        explodeBomba(tabuleiro, xExplosao, yExplosao);
        
        xExplosao++;
        explodeBomba(tabuleiro, xExplosao, yExplosao);

        xExplosao++;
        explodeBomba(tabuleiro, xExplosao, yExplosao);

        yExplosao++;
        explodeBomba(tabuleiro, xExplosao, yExplosao);

        yExplosao++;
        explodeBomba(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        explodeBomba(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        explodeBomba(tabuleiro, xExplosao, yExplosao);

        yExplosao--;
        explodeBomba(tabuleiro, xExplosao, yExplosao);
    }

    private void explodeBomba(Tabuleiro tabuleiro, int xExplosao, int yExplosao)
    {
        if(xExplosao >= 0 && xExplosao < tabuleiro.getTamanhoX() && yExplosao >= 0 && yExplosao < tabuleiro.getTamanhoY())
        {
            tabuleiro.setBloco(xExplosao, yExplosao, new BlocoGenerico(0));
        }
    }
}

// realizar a movimentação a partir do oposto do movimento

// nos atualiza movimento nn está sendo considerado quando eles tao encostados nas paredes