import PastaBlocos.*;

public class Controle
{
    private Blocos bomba;
    private boolean bombaAtiva = false;
    private int xExplosao;
    private int yExplosao;

    private int xFim = 0;
    private int yFim = 0;
    
    public void realizaComando(char direcao, int xIni, int yIni, Tabuleiro tabuleiro)
    {
        planejaMovimento(direcao, xIni, yIni);

        if(xFim < 0 || xFim >= tabuleiro.getTamanhoX() || yFim < 0 || yFim >= tabuleiro.getTamanhoY())
        {
            movimenta(direcao, xIni, yIni, tabuleiro);
            atualizaBomba(xIni, yIni, tabuleiro);
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
            tabuleiro.setBloco(xIni, yIni, new Vazio());
            realizaComando(direcao, xFim, yFim, tabuleiro);
        }

        // quando ambos os blocos são iguais e podem se juntar
        else if(tabuleiro.getId(xFim, yFim) == tabuleiro.getId(xIni, yIni))
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
            tabuleiro.setBloco(xIni, yIni, new Vazio());
        }

        // quando o bloco deleta vai deletar o outro
        else if((tabuleiro.getId(xFim, yFim) == "deleta" && tabuleiro.getId(xIni, yIni) != 0) 
        || (tabuleiro.getId(xIni, yIni) == "deleta" && tabuleiro.getId(xFim, yFim) != 0))
        {
            tabuleiro.setBloco(xIni, yIni, new Vazio());
            tabuleiro.setBloco(xFim, yFim, new Vazio());
        }

        // quando o bloco dobro (que está no destino do movimento) vai dobrar o outro
        else if(tabuleiro.getId(xFim, yFim) == "x2" && tabuleiro.getId(xIni, yIni) != 0)
        {
            tabuleiro.setBloco(xIni, yIni, new Vazio());
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni).junta());
        }
        
        // quando o bloco dobro (que está na origem do movimento) vai dobrar o outro
        else if(tabuleiro.getId(xIni, yIni) == "x2" && tabuleiro.getId(xFim, yFim) != 0)
        {
            tabuleiro.setBloco(xIni, yIni, new Vazio());
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
        }
    }

    public void setBombaAtiva(Blocos bomba)
    {
        bombaAtiva = true;
        this.bomba = bomba;
    }

    private void atualizaBomba(int xIni, int yIni, Tabuleiro tabuleiro)
    {
        if(bombaAtiva == true)
        {
            bomba.setVida(-1);
            bomba.setCoordX(bomba.getCoordX() + (xFim - xIni));
            bomba.setCoordY(bomba.getCoordY() + (yFim - yIni));
            
            if(bomba.getvida() == 0)
            {
                bombaAtiva = false;

                xExplosao = bomba.getCoordX;
                yExplosao = bomba.getCoordY;
                explodeBomba(tabuleiro);

                xExplosao--;
                yExplosao--;
                explodeBomba(tabuleiro);
                
                xExplosao++;
                explodeBomba(tabuleiro);

                xExplosao++;
                explodeBomba(tabuleiro);

                yExplosao++;
                explodeBomba(tabuleiro);

                yExplosao++;
                explodeBomba(tabuleiro);

                xExplosao--;
                explodeBomba(tabuleiro);

                xExplosao--;
                explodeBomba(tabuleiro);

                yExplosao--;
                explodeBomba(tabuleiro);
            }
        }
    }

    private void explodeBomba(Tabuleiro tabuleiro)
    {
        if(xExplosao >= 0 && xExplosao < tabuleiro.getTamanhoX() && yExplosao >= 0 && yExplosao < tabuleiro.getTamanhoY())
        {
            tabuleiro.setBloco(xExplosao, yExplosao, new Vazio());
        }
    }
}

// método randomizar aparecimento, ponderadamente
// soh uma bomba por vez
// fazer junções especiais


// realizar a movimentação a partir do oposto do movimento