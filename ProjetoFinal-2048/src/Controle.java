import PastaBlocos.*;

public class Controle {

    Blocos bomba = new Bomba();
    
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

        if(xFim < 0 || xFim >= tabuleiro.getTamanhoX() || yFim < 0 || yFim >= tabuleiro.getTamanhoY() || tabuleiro.get)
        {
            if(tabuleiro.getNumero(xFim, yFim) == 0)
            {
                tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
                tabuleiro.setBloco(xIni, yIni, new Vazio());
                juntaBloco(direcao, xFim, yFim, tabuleiro);
            }
            else if(tabuleiro.getNumero(xFim, yFim) == tabuleiro.getNumero(xIni, yIni))
            {
                tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).dobra());
                tabuleiro.setBloco(xIni, yIni, new Vazio());
            }

            if(bomba.getAtiva() == true)
            {
                bomba.setVida(-1);
                bomba.setCoordX(bomba.getCoordX() + (xFim - xIni));
                bomba.setCoordY(bomba.getCoordY() + (yFim - yIni));
                
                if(bomba.getvida() == 0)
                {
                    bomba.setAtiva(false);
                    bomba.setExplosao(true);
                    bomba.setVida(3);

                }
            }
        }
        









    }
}

// fazer movimentos, insistir
// juntar blocos iguais ou especiais
// mostrar blocos resultantes