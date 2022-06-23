package com.poo.jogo2048;

import java.util.Objects;

import com.poo.jogo2048.PastaBlocos.*;

public class Controle {
    private boolean botaoBombaSelected;
    private boolean botaoDeletaSelected;
    private boolean botaoTempoSelected;
    private boolean botao2xSelected;

    private IBlocosTimer blocoBomba;
    private boolean blocoBombaAtiva = false;

    private IBlocosTimer blocoTempo;
    private boolean blocoTempoAtivo = false;

    private int xFim = 0;
    private int yFim = 0;
    
    public void conectaBlocoTimer()
    {
        
    }
    
    public void realizaComando(char direcao, int xIni, int yIni, Tabuleiro tabuleiro)
    {
        planejaMovimento(direcao, xIni, yIni);

        if(0 <= xFim && xFim < tabuleiro.getTamanho() && 0 <= yFim && yFim < tabuleiro.getTamanho())
        {
            if(tabuleiro.getBloco(xIni, yIni).getClass() == IBlocosTimer.class)
            {
                IBlocosTimer bloco = (IBlocosTimer) tabuleiro.getBloco(xIni, yIni);
                bloco.setVida(-1);
                bloco.setCoordX(bloco.getCoordX() + (xFim - xIni));
                bloco.setCoordY(bloco.getCoordY() + (yFim - yIni));
            }
            movimenta(direcao, xIni, yIni, tabuleiro);
        }
    }

    private void planejaMovimento(char direcao, int xIni, int yIni)
    {
        switch (direcao)
        {
            case 'w':
                xFim = xIni;
                yFim = yIni + 1;
                break;
            case 'a':
                xFim = xIni - 1;
                yFim = yIni;
                break;
            case 's':
                xFim = xIni;
                yFim = yIni - 1;
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
        if(Objects.equals(tabuleiro.getId(xFim, yFim), 0))
        {
            if(Objects.equals(tabuleiro.getId(xIni, yIni), "tempo"))
            {
                ((BlocoTempo) tabuleiro.getBloco(xIni, yIni)).setCoordX(xFim);
                ((BlocoTempo) tabuleiro.getBloco(xIni, yIni)).setCoordY(yFim);
            }
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            realizaComando(direcao, xFim, yFim, tabuleiro);
        }

        // quando ambos os blocos são iguais e podem se juntar
        else if(Objects.equals(tabuleiro.getId(xFim, yFim), tabuleiro.getId(xIni, yIni)))
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
        }

        // quando o bloco deleta deleta o outro: ou quando o deleta está na posição final ou na inicial, não sendo o outro bloco vazio
        else if((Objects.equals(tabuleiro.getId(xFim, yFim), "deleta") && !Objects.equals(tabuleiro.getId(xIni, yIni), 0)) 
        || (Objects.equals(tabuleiro.getId(xIni, yIni), "deleta") && !Objects.equals(tabuleiro.getId(xFim, yFim), 0)))
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, new BlocoGenerico(0));
        }

        // quando o bloco dobro (que está no destino do movimento) vai dobrar o outro, não sendo o outro vazio
        else if(Objects.equals(tabuleiro.getId(xFim, yFim), "x2") && !Objects.equals(tabuleiro.getId(xIni, yIni), 0))
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni).junta());
        }
        
        // quando o bloco dobro (que está na origem do movimento) vai dobrar o outro
        else if(Objects.equals(tabuleiro.getId(xIni, yIni), "x2") && !Objects.equals(tabuleiro.getId(xFim, yFim), 0))
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
        }
    }

    public BlocoBomba setAtivo(BlocoBomba blocoBomba)
    {
        blocoBombaAtiva = true;
        this.blocoBomba = blocoBomba;
        return blocoBomba;
    }

    public BlocoTempo setAtivo(BlocoTempo blocoTempo)
    {
        blocoTempoAtivo = true;
        this.blocoTempo = blocoTempo;
        return blocoTempo;
    }

    public boolean getBlocoBombaAtiva()
    {
        return blocoBombaAtiva;
    }

    public boolean getBlocoTempoAtivo()
    {
        return blocoTempoAtivo;
    }

    public void atualizaVidas()
    {
        if(blocoBombaAtiva)
        {
            if(blocoBomba.getVida() == 0)
            {
                blocoBombaAtiva = false;
                miraVizinhos(tabuleiro, blocoBomba.getCoordX(), blocoBomba.getCoordY());
            }
        }
        if (blocoTempoAtivo)
        {
            if (blocoTempo.getVida() == 0)
            {
                blocoTempoAtivo = false;
                tabuleiro.setBloco(blocoTempo.getCoordX(), blocoTempo.getCoordY(), new BlocoGenerico(0));
            }
        }
    }

    private void miraVizinhos(Tabuleiro tabuleiro, int xExplosao, int yExplosao)
    {
        explode(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        yExplosao--;
        explode(tabuleiro, xExplosao, yExplosao);
        
        xExplosao++;
        explode(tabuleiro, xExplosao, yExplosao);

        xExplosao++;
        explode(tabuleiro, xExplosao, yExplosao);

        yExplosao++;
        explode(tabuleiro, xExplosao, yExplosao);

        yExplosao++;
        explode(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        explode(tabuleiro, xExplosao, yExplosao);

        xExplosao--;
        explode(tabuleiro, xExplosao, yExplosao);

        yExplosao--;
        explode(tabuleiro, xExplosao, yExplosao);
    }

    private void explode(Tabuleiro tabuleiro, int xExplosao, int yExplosao)
    {
        if(xExplosao >= 0 && xExplosao < tabuleiro.getTamanho() && yExplosao >= 0 && yExplosao < tabuleiro.getTamanho())
        {
            tabuleiro.setBloco(xExplosao, yExplosao, new BlocoGenerico(0));
        }
    }

    public boolean getBotaoBombaSelected()
    {
        return botaoBombaSelected;
    }

    public void setBotaoBombaSelected(boolean botaoBombaSelected) {
        this.botaoBombaSelected = botaoBombaSelected;
    }

    public boolean getBotaoDeletaSelected()
    {
        return botaoDeletaSelected;
    }

    public void setBotaoDeletaSelected(boolean botaoDeletaSelected) {
        this.botaoDeletaSelected = botaoDeletaSelected;
    }

    public boolean getBotaoTempoSelected()
    {
        return botaoTempoSelected;
    }

    public void setBotaoTempoSelected(boolean botaoTempoSelected) {
        this.botaoTempoSelected = botaoTempoSelected;
    }

    public boolean getBotao2xSelected()
    {
        return botao2xSelected;
    }

    public void setBotao2xSelected(boolean botao2xSelected) {
        this.botao2xSelected = botao2xSelected;
    }
}




// reconhecer se eh um bloco bomba ou tempo antes de entrar na função
// entrar na função(de movimentar cada bloco) com a direção oposta ao que foi jogado
// singleton com bomba e tempo