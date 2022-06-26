package com.poo.jogo2048;

import java.util.Objects;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.poo.jogo2048.PastaBlocos.*;

public class Controle
{
    private boolean botaoBombaSelected;
    private boolean botaoDeletaSelected;
    private boolean botaoTempoSelected;
    private boolean botao2xSelected;

    private IBlocosTimer blocoBomba;
    private IBlocosTimer blocoTempo;

    private int xFim = 0;
    private int yFim = 0;
    
    public Controle()
    {
        blocoBomba = BlocoBomba.getInstance();
        blocoTempo = BlocoTempo.getInstance();
    }

    public void spawnBloco(Tabuleiro tabuleiro, Controle controle)
    {
        IBlocos blocoGerado = new BlocoGenerico(0);
        Random random = new Random();
        int coordX = random.nextInt(tabuleiro.getTamanho());
		int coordY = random.nextInt(tabuleiro.getTamanho());

		if (Objects.equals(tabuleiro.getId(coordX, coordY), 0))
		{
			int index = random.nextInt(100);
            if(index < 10)
            {
                blocoGerado = new BlocoGenerico(1);
            }
            else if (index < 60)
            {
                blocoGerado = new BlocoGenerico(2);
            }
            else if (index < 80)
            {
                blocoGerado = new BlocoGenerico(4);
            }
            else if (index < 85 && blocoBomba.getAtivo() == false && controle.getBotaoBombaSelected())
            {
                
                blocoGerado = blocoBomba;
                blocoBomba.setAtivo(true);
            }
            else if (index < 90 && blocoTempo.getAtivo() == false && controle.getBotaoTempoSelected())
            {
                blocoGerado = blocoTempo;
                blocoTempo.setAtivo(true);
            }
            else if (index < 95 && controle.getBotaoDeletaSelected())
            {
                blocoGerado = new BlocoDeleta();
            }
            else if (index < 100 && controle.getBotao2xSelected())
            {
                blocoGerado = new BlocoDobro();
            }
            else
            {
                spawnBloco(tabuleiro, controle);
            }
            
            tabuleiro.setBloco(coordX, coordY, blocoGerado);
		}
		else
		{
			spawnBloco(tabuleiro, controle);
		}
    }
    
    public void realizaComando(char direcao, int xIni, int yIni, Tabuleiro tabuleiro, SpriteBatch batch, Stage stage)
    {
        planejaMovimento(direcao, xIni, yIni);

        if(0 <= xFim && xFim < tabuleiro.getTamanho() && 0 <= yFim && yFim < tabuleiro.getTamanho())
        {
            if(tabuleiro.getBloco(xIni, yIni) instanceof IBlocosTimer)
            {
                IBlocosTimer bloco = (IBlocosTimer) tabuleiro.getBloco(xIni, yIni);
                bloco.setVida(-1);
                bloco.setCoordX(xFim);
                bloco.setCoordY(yFim);
            }
            movimenta(direcao, xIni, yIni, tabuleiro, batch, stage);
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

    private void movimenta(char direcao, int xIni, int yIni, Tabuleiro tabuleiro, SpriteBatch batch, Stage stage)
    {
        // quando está vazio na frente, livre para continuar se movendo
        if(Objects.equals(tabuleiro.getId(xFim, yFim), 0))
        {
            float posicaoXBloco = ((float) ((400 * 0.05) + (400 * 0.87 / tabuleiro.getTamanho()) * xFim + (400 * 0.01) * xFim));
            float posicaoYBloco = ((float) ((400 * 0.05) + (400 * 0.87 / tabuleiro.getTamanho()) * yFim + (400 * 0.01) * yFim));

            MoveToAction moveBottomRightAction = new MoveToAction();
            moveBottomRightAction.setPosition(posicaoXBloco,posicaoYBloco);
            moveBottomRightAction.setDuration(1);
            moveBottomRightAction.setInterpolation(Interpolation.smooth);
    
            tabuleiro.getBloco(xIni, yIni).getImagem().addAction(moveBottomRightAction);
            stage.draw();

            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            realizaComando(direcao, xFim, yFim, tabuleiro, batch, stage);
        }

        // quando ambos os blocos são iguais e podem se juntar
        else if(Objects.equals(tabuleiro.getId(xFim, yFim), tabuleiro.getId(xIni, yIni)))
        {
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.getBloco(xFim, yFim).setJuntado(true);
        }

        // quando o bloco deleta deleta o outro: ou quando o deleta está na posição final ou na inicial
        else if(Objects.equals(tabuleiro.getId(xFim, yFim), "deleta") || Objects.equals(tabuleiro.getId(xIni, yIni), "deleta"))
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, new BlocoGenerico(0));
        }

        // quando o bloco dobro (que está no destino do movimento) vai dobrar o outro 
        else if(Objects.equals(tabuleiro.getId(xFim, yFim), "2x"))
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni).junta());
            tabuleiro.getBloco(xIni, yIni).setJuntado(true);
        }
        
        // quando o bloco dobro (que está na origem do movimento) vai dobrar o outro
        else if(Objects.equals(tabuleiro.getId(xIni, yIni), "2x"))
        {
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xFim, yFim).junta());
            tabuleiro.getBloco(xFim, yFim).setJuntado(true);
        }
    }

    public void atualizaVidas(Tabuleiro tabuleiro)
    {
        if(blocoBomba.getAtivo() && blocoBomba.getVida() == 0)
        {
            blocoBomba.setAtivo(false);
            blocoBomba.setVida(3);
            miraVizinhos(tabuleiro, blocoBomba.getCoordX(), blocoBomba.getCoordY());
        }
        if (blocoTempo.getAtivo() && blocoTempo.getVida() == 0)
        {
            blocoTempo.setAtivo(false);
            blocoTempo.setVida(3);
            tabuleiro.setBloco(blocoTempo.getCoordX(), blocoTempo.getCoordY(), new BlocoGenerico(0));
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

    public void zeraTabuleiro(Tabuleiro tabuleiro)
    {
        for(int i = 0; i < tabuleiro.getTamanho(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanho(); j++)
            {
                tabuleiro.getBloco(i, j).setJuntado(false);
            }
        }
    }

    public boolean getBotaoBombaSelected()
    {
        return botaoBombaSelected;
    }

    public void setBotaoBombaSelected(boolean botaoBombaSelected)
    {
        this.botaoBombaSelected = botaoBombaSelected;
    }

    public boolean getBotaoDeletaSelected()
    {
        return botaoDeletaSelected;
    }

    public void setBotaoDeletaSelected(boolean botaoDeletaSelected)
    {
        this.botaoDeletaSelected = botaoDeletaSelected;
    }

    public boolean getBotaoTempoSelected()
    {
        return botaoTempoSelected;
    }

    public void setBotaoTempoSelected(boolean botaoTempoSelected)
    {
        this.botaoTempoSelected = botaoTempoSelected;
    }

    public boolean getBotao2xSelected()
    {
        return botao2xSelected;
    }

    public void setBotao2xSelected(boolean botao2xSelected)
    {
        this.botao2xSelected = botao2xSelected;
    }
}



// fazer exceções para entradas, por exemplo, de direção do movimenti
