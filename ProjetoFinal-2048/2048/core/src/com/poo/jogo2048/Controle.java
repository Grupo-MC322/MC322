package com.poo.jogo2048;

import java.util.Objects;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.poo.jogo2048.PastaBlocos.*;
import com.poo.jogo2048.Telas.TelaGanhou;
import com.poo.jogo2048.Telas.TelaPerdeu;

public class Controle
{
    final jogo2048 jogo;
    private Tabuleiro tabuleiro;
    private SpriteBatch batch;
    private Stage stage;

    private boolean botaoBombaSelected;
    private boolean botaoDeletaSelected;
    private boolean botaoTempoSelected;
    private boolean botao2xSelected;

    private IBlocosTimer blocoBomba;
    private IBlocosTimer blocoTempo;

    private int xFim = 0;
    private int yFim = 0;

    private boolean vazioInexistente;
    private boolean algoMudou;
    
    public Controle(final jogo2048 jogo)
    {
        this.jogo = jogo;
        this.batch = jogo.getBatch();
        this.stage = jogo.getStage();
        
        blocoBomba = BlocoBomba.getInstance();
        blocoTempo = BlocoTempo.getInstance();
    }

    /* Adiciona um bloco entre 1, 2, 4 ou especiais em alguma posição vazia do tabuleiro. */
    public void spawnBloco()
    {
        IBlocos blocoGerado = new BlocoGenerico(0);
        Random random = new Random();
        int coordX = random.nextInt(tabuleiro.getTamanho());
		int coordY = random.nextInt(tabuleiro.getTamanho());

        // definição das probabilidades de cada bloco ser adicionado
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
            else if (index < 85 && blocoBomba.getAtivo() == false && getBotaoBombaSelected())
            {
                blocoGerado = blocoBomba;
                
                blocoBomba.setAtivo(true);
                blocoBomba.setCoordX(coordX);
                blocoBomba.setCoordY(coordY);
            }
            else if (index < 90 && blocoTempo.getAtivo() == false && getBotaoTempoSelected())
            {
                blocoGerado = blocoTempo;
                blocoTempo.setAtivo(true);
            }
            else if (index < 95 && getBotaoDeletaSelected())
            {
                blocoGerado = new BlocoDeleta();
            }
            else if (index < 100 && getBotao2xSelected())
            {
                blocoGerado = new BlocoDobro();
            }
            else
            {
                spawnBloco();
            }
            
            // adicionando o novo bloco ao tabuleiro e animando
            tabuleiro.setBloco(coordX, coordY, blocoGerado);
            tabuleiro.getBloco(coordX, coordY).getImagem().setScale(.75f);
			tabuleiro.getBloco(coordX, coordY).getImagem().addAction(Actions.scaleTo(1, 1, .25f));
		}
		else
		{
			spawnBloco();
		}
    }

    public void realizaComando(char direcao)
	{
        atualizaVidas();

		if(direcao == 'w')
        {
            for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
            {
                for(int coluna = tabuleiro.getTamanho() - 1; coluna >= 0; coluna--)
                {
                    jogada(linha, coluna, direcao);
                }
            }
        }
        else if(direcao == 's')
        {
            for(int linha = tabuleiro.getTamanho() - 1; linha >= 0; linha--)
            {
                for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
                {
                    jogada(linha, coluna, direcao);
                }
            }
        }
        else if(direcao == 'a')
        {
            for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
            {
                for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
                {
                    jogada(linha, coluna, direcao);
                }
            }
        }
        else if(direcao == 'd')
        {
            for(int linha = tabuleiro.getTamanho() - 1; linha >= 0; linha--)
            {
                for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
                {
                    jogada(linha, coluna, direcao);
                }
            }
        }
        
        percorreTabuleiro();
        if(algoMudou)
        {
            spawnBloco();
            algoMudou = false;
        }
	}

    private void jogada(int linha, int coluna, char direcao)
    {
        if(!Objects.equals(tabuleiro.getId(linha, coluna), 0) && tabuleiro.getBloco(linha, coluna).getJuntado() == false)
        {
            realizaComando(direcao, linha, coluna, batch, stage);
        }
    }
    
    public void realizaComando(char direcao, int xIni, int yIni, SpriteBatch batch, Stage stage)
    {
        planejaMovimento(direcao, xIni, yIni);

        if(0 <= xFim && xFim < tabuleiro.getTamanho() && 0 <= yFim && yFim < tabuleiro.getTamanho())
        {
            if(tabuleiro.getBloco(xIni, yIni) instanceof IBlocosTimer)
            {
                ((IBlocosTimer) tabuleiro.getBloco(xIni, yIni)).setCoordX(xFim);
                ((IBlocosTimer) tabuleiro.getBloco(xIni, yIni)).setCoordY(yFim);
            }
            movimenta(direcao, xIni, yIni, batch, stage);
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

    /* Realiza a movimentação de um bloco de uma posição inicial indicada para a posição final definida. */
    private void movimenta(char direcao, int xIni, int yIni, SpriteBatch batch, Stage stage)
    {
        // animação
        float posicaoXBloco = ((float) ((400 * 0.05) + (400 * 0.87 / tabuleiro.getTamanho()) * xFim + (400 * 0.01) * xFim));
        float posicaoYBloco = ((float) ((400 * 0.05) + (400 * 0.87 / tabuleiro.getTamanho()) * yFim + (400 * 0.01) * yFim));
        MoveToAction juntaBloco = new MoveToAction();
        juntaBloco.setPosition(posicaoXBloco,posicaoYBloco);
        juntaBloco.setDuration(0.25f);
        juntaBloco.setInterpolation(Interpolation.smooth);
        tabuleiro.getBloco(xIni, yIni).getImagem().addAction(juntaBloco);
        SequenceAction animaBloco = new SequenceAction(juntaBloco, Actions.removeActor());

        // quando está vazio na frente, livre para continuar se movendo
        if(Objects.equals(tabuleiro.getId(xFim, yFim), 0))
        {            
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
            tabuleiro.getBloco(xIni, yIni).getImagem().addAction(Actions.removeActor());
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            realizaComando(direcao, xFim, yFim, batch, stage);
            algoMudou = true;
        }

        // quando ambos os blocos são iguais e podem se juntar
        else if(Objects.equals(tabuleiro.getId(xFim, yFim), tabuleiro.getId(xIni, yIni)))
        {
            tabuleiro.getBloco(xFim, yFim).getImagem().addAction(Actions.removeActor());
            tabuleiro.getBloco(xFim, yFim).junta();
            tabuleiro.getBloco(xIni, yIni).getImagem().addAction(animaBloco);
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.getBloco(xFim, yFim).setJuntado(true);
            algoMudou = true;
        }

        // quando o bloco deleta deleta o outro: ou quando o deleta está na posição final ou na inicial
        else if(Objects.equals(tabuleiro.getId(xFim, yFim), "deleta") || Objects.equals(tabuleiro.getId(xIni, yIni), "deleta"))
        {
            tabuleiro.getBloco(xIni, yIni).getImagem().addAction(animaBloco);
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.getBloco(xFim, yFim).getImagem().addAction(Actions.removeActor());
            tabuleiro.setBloco(xFim, yFim, new BlocoGenerico(0));
            algoMudou = true;
        }

        // quando o bloco dobro (que está no destino do movimento) vai dobrar o outro 
        else if(Objects.equals(tabuleiro.getId(xFim, yFim), "2x"))
        {
            tabuleiro.getBloco(xIni, yIni).getImagem().addAction(animaBloco);
            tabuleiro.getBloco(xIni, yIni).junta();
            tabuleiro.getBloco(xFim, yFim).getImagem().addAction(Actions.removeActor());
            tabuleiro.setBloco(xFim, yFim, tabuleiro.getBloco(xIni, yIni));
            tabuleiro.getBloco(xIni, yIni).getImagem().addAction(Actions.removeActor());
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.getBloco(xIni, yIni).setJuntado(true);
            algoMudou = true;
        }
        
        // quando o bloco dobro (que está na origem do movimento) vai dobrar o outro
        else if(Objects.equals(tabuleiro.getId(xIni, yIni), "2x"))
        {
            tabuleiro.getBloco(xIni, yIni).getImagem().addAction(animaBloco);
            tabuleiro.setBloco(xIni, yIni, new BlocoGenerico(0));
            tabuleiro.getBloco(xFim, yFim).getImagem().addAction(Actions.removeActor());
            tabuleiro.getBloco(xFim, yFim).junta();
            tabuleiro.getBloco(xFim, yFim).setJuntado(true);
            algoMudou = true;
        }
    }

    public void atualizaVidas()
    {
        if (blocoBomba.getAtivo())
        {
            Gdx.app.log("bomba", "tem vida maior que 0");
            // diminui 1
            blocoBomba.setVida(-1);

            // setup das imagens para identificação do estado do bloco
            if(blocoBomba.getVida() == 2)
            {
                Gdx.app.log("bomba", "tem vida == 2");
                tabuleiro.getBloco(blocoBomba.getCoordX(), blocoBomba.getCoordY()).getImagem().remove();
                blocoBomba.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba_2:3.png"))));
            }
            else if(blocoBomba.getVida() == 1)
            {
                Gdx.app.log("bomba", "tem vida == 1");
                tabuleiro.getBloco(blocoBomba.getCoordX(), blocoBomba.getCoordY()).getImagem().remove();
                blocoBomba.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba_3:3.png"))));
            }
        }
        if (blocoTempo.getAtivo())
        {
            Gdx.app.log("tempo", "tem vida maior que 0");
            // diminui 1
            blocoTempo.setVida(-1);

            // setup das imagens para identificação do estado do bloco
            if(blocoTempo.getVida() == 3)
            {
                Gdx.app.log("tempo", "tem vida == 3");
                tabuleiro.getBloco(blocoTempo.getCoordX(), blocoTempo.getCoordY()).getImagem().remove();
                blocoTempo.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_3:4.png"))));
            }
            if(blocoTempo.getVida() == 2)
            {
                Gdx.app.log("tempo", "tem vida == 2");
                tabuleiro.getBloco(blocoTempo.getCoordX(), blocoTempo.getCoordY()).getImagem().remove();
                blocoTempo.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_3:4.png"))));
            }
            else if(blocoTempo.getVida() == 1)
            {
                Gdx.app.log("tempo", "tem vida == 1");
                tabuleiro.getBloco(blocoTempo.getCoordX(), blocoTempo.getCoordY()).getImagem().remove();
                blocoTempo.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_2:4.png"))));
            }
        }
        if(blocoBomba.getVida() == 0)
        {
            Gdx.app.log("bomba", "zerou");
            algoMudou = true;
            blocoBomba.reset();
            tabuleiro.getBloco(blocoBomba.getCoordX(), blocoBomba.getCoordY()).getImagem().addAction(Actions.removeActor());
            miraVizinhos(blocoBomba.getCoordX(), blocoBomba.getCoordY());
        }
        if (blocoTempo.getVida() == 0)
        {
            Gdx.app.log("tempo", "zerou");
            algoMudou = true;
            tabuleiro.getBloco(blocoTempo.getCoordX(), blocoTempo.getCoordY()).getImagem().addAction(Actions.removeActor());
            blocoTempo.reset();
            tabuleiro.setBloco(blocoTempo.getCoordX(), blocoTempo.getCoordY(), new BlocoGenerico(0));
        }
    }

    private void miraVizinhos(int xExplosao, int yExplosao)
    {
        explode(xExplosao, yExplosao);

        xExplosao--;
        yExplosao--;
        explode(xExplosao, yExplosao);
        
        xExplosao++;
        explode(xExplosao, yExplosao);

        xExplosao++;
        explode(xExplosao, yExplosao);

        yExplosao++;
        explode(xExplosao, yExplosao);

        yExplosao++;
        explode(xExplosao, yExplosao);

        xExplosao--;
        explode(xExplosao, yExplosao);

        xExplosao--;
        explode(xExplosao, yExplosao);

        yExplosao--;
        explode(xExplosao, yExplosao);
    }

    private void explode(int xExplosao, int yExplosao)
    {
        if(xExplosao >= 0 && xExplosao < tabuleiro.getTamanho() && yExplosao >= 0 && yExplosao < tabuleiro.getTamanho())
        {
            SequenceAction animaExplosao = new SequenceAction(Actions.scaleTo(0, 0, .25f), Actions.removeActor());
            tabuleiro.getBloco(xExplosao, yExplosao).getImagem().addAction(animaExplosao);
            tabuleiro.setBloco(xExplosao, yExplosao, new BlocoGenerico(0));
        }
    }

    public void percorreTabuleiro()
    {
        vazioInexistente = true;
        for(int i = 0; i < tabuleiro.getTamanho(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanho(); j++)
            {
                if(Objects.equals(tabuleiro.getId(i, j), 2048))
                {
                    jogo.setScreen(new TelaGanhou(jogo));
                }
                else if(Objects.equals(tabuleiro.getId(i, j), 0))
                {
                    vazioInexistente = false;
                }
                tabuleiro.getBloco(i, j).setJuntado(false);
            }
        }
        if(vazioInexistente)
        {
            jogo.setScreen(new TelaPerdeu(jogo));
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

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }
}



// fazer exceções para entradas, por exemplo, de direção do movimenti
