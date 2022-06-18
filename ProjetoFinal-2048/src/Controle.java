import PastaBlocos.*;

public class Controle
{
    private Blocos bomba;
    private boolean bombaAtiva = false;
    private int xExplosao;
    private int yExplosao;

    private int xFim = 0;
    private int yFim = 0;
    
    public void juntaBloco(char direcao, int xIni, int yIni, Tabuleiro tabuleiro)
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
        if(tabuleiro.getId(xFim, yFim) == 0)
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
            tabuleiro.setBloco(xIni, yIni, new Vazio());
            juntaBloco(direcao, xFim, yFim, tabuleiro);
        }
        else if(tabuleiro.getId(xFim, yFim) == tabuleiro.getId(xIni, yIni))
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
            tabuleiro.setBloco(xIni, yIni, new Vazio());
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