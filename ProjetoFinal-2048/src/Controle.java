import PastaBlocos.*;

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

        if(xFim < 0 || xFim >= tabuleiro.getTamanhoX() || yFim < 0 || yFim >= tabuleiro.getTamanhoY())

        if(tabuleiro.getNumero(xFim, yFim) == 0)
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
            tabuleiro.setBloco(xIni, yIni, new Vazio());
            juntaBloco(direcao, xFim, yFim, tabuleiro);
        }

        // se o numero for igual
        if(tabuleiro.getNumero(xFim, yFim) == tabuleiro.getNumero(xIni, yIni))
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).dobra());
            // depois fazer exceção para os blocos especiais
            tabuleiro.setBloco(xIni, yIni, new Vazio());
            return;
        }









    }
}

// fazer movimentos, insistir
// juntar blocos iguais ou especiais
// mostrar blocos resultantes