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

    private int linhaFim = 0;
    private int colunaFim = 0;

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
        int linha = random.nextInt(tabuleiro.getTamanho());
		int coluna = random.nextInt(tabuleiro.getTamanho());

        // definição das probabilidades de cada bloco ser adicionado
		if (Objects.equals(tabuleiro.getId(linha, coluna), 0))
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
                blocoBomba.setLinha(linha);
                blocoBomba.setColuna(coluna);
            }
            else if (index < 90 && blocoTempo.getAtivo() == false && getBotaoTempoSelected())
            {
                blocoGerado = blocoTempo;

                blocoTempo.setAtivo(true);
                blocoTempo.setLinha(linha);
                blocoTempo.setColuna(coluna);
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
            tabuleiro.setBloco(linha, coluna, blocoGerado);
            tabuleiro.getBloco(linha, coluna).getImagem().setScale(.75f);
			tabuleiro.getBloco(linha, coluna).getImagem().addAction(Actions.scaleTo(1, 1, .25f));
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
            for(int coluna = tabuleiro.getTamanho() - 1; coluna >= 0; coluna--)
            {
                for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
                {
                    jogada(linha, coluna, direcao);
                }
            }
        }
        else if(direcao == 's')
        {
            for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
            {
                for(int linha = tabuleiro.getTamanho() - 1; linha >= 0; linha--)
                {
                    jogada(linha, coluna, direcao);
                }
            }
        }
        else if(direcao == 'a')
        {
            for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
            {
                for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
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
    
    public void realizaComando(char direcao, int linhaIni, int colunaIni, SpriteBatch batch, Stage stage)
    {
        planejaMovimento(direcao, linhaIni, colunaIni);

        if(0 <= linhaFim && linhaFim < tabuleiro.getTamanho() && 0 <= colunaFim && colunaFim < tabuleiro.getTamanho())
        {
            if(tabuleiro.getBloco(linhaIni, colunaIni) instanceof IBlocosTimer && (Objects.equals(tabuleiro.getId(linhaFim, colunaFim), 0) || Objects.equals(tabuleiro.getId(linhaFim, colunaFim), "deleta") || Objects.equals(tabuleiro.getId(linhaFim, colunaFim), "2x")))
            {
                ((IBlocosTimer) tabuleiro.getBloco(linhaIni, colunaIni)).setLinha(linhaFim);
                ((IBlocosTimer) tabuleiro.getBloco(linhaIni, colunaIni)).setColuna(colunaFim);
            }
            movimenta(direcao, linhaIni, colunaIni, batch, stage);
        }
    }

    private void planejaMovimento(char direcao, int linhaIni, int colunaIni)
    {
        switch (direcao)
        {
            case 'w':
                linhaFim = linhaIni;
                colunaFim = colunaIni + 1;
                break;
            case 'a':
                linhaFim = linhaIni - 1;
                colunaFim = colunaIni;
                break;
            case 's':
                linhaFim = linhaIni;
                colunaFim = colunaIni - 1;
                break;
            case 'd':
                linhaFim = linhaIni + 1;
                colunaFim = colunaIni;
                break;
        }
    }

    /* Realiza a movimentação de um bloco de uma posição inicial indicada para a posição final definida. */
    private void movimenta(char direcao, int linhaIni, int colunaIni, SpriteBatch batch, Stage stage)
    {
        // animação
        float posicaoXBloco = ((500 * 0.05f) + (500 * 0.87f / tabuleiro.getTamanho()) * linhaFim + (500 * 0.01f) * linhaFim);
        float posicaoYBloco = ((500 * 0.05f) + (500 * 0.87f / tabuleiro.getTamanho()) * colunaFim + (500 * 0.01f) * colunaFim);
        MoveToAction juntaBloco = new MoveToAction();
        juntaBloco.setPosition(posicaoXBloco,posicaoYBloco);
        juntaBloco.setDuration(0.25f);
        juntaBloco.setInterpolation(Interpolation.smooth);
        tabuleiro.getBloco(linhaIni, colunaIni).getImagem().addAction(juntaBloco);
        SequenceAction animaBloco = new SequenceAction(juntaBloco, Actions.removeActor());

        // quando está vazio na frente, livre para continuar se movendo
        if(Objects.equals(tabuleiro.getId(linhaFim, colunaFim), 0))
        {
            tabuleiro.setBloco(linhaFim, colunaFim, tabuleiro.getBloco(linhaIni, colunaIni));
            tabuleiro.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            tabuleiro.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            realizaComando(direcao, linhaFim, colunaFim, batch, stage);
            algoMudou = true;
        }

        // quando ambos os blocos são iguais e podem se juntar
        else if(Objects.equals(tabuleiro.getId(linhaFim, colunaFim), tabuleiro.getId(linhaIni, colunaIni)))
        {
            tabuleiro.getBloco(linhaFim, colunaFim).getImagem().addAction(Actions.removeActor());
            if(tabuleiro.getBloco(linhaFim, colunaFim) instanceof BlocoGenerico)
            {
                ((BlocoGenerico) tabuleiro.getBloco(linhaFim, colunaFim)).dobra();
            }
            tabuleiro.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            tabuleiro.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            tabuleiro.getBloco(linhaFim, colunaFim).setJuntado(true);
            algoMudou = true;
        }

        // quando o bloco deleta deleta o outro: ou quando o deleta está na posição final ou na inicial
        else if(Objects.equals(tabuleiro.getId(linhaFim, colunaFim), "deleta") || Objects.equals(tabuleiro.getId(linhaIni, colunaIni), "deleta"))
        {
            tabuleiro.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            tabuleiro.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            tabuleiro.getBloco(linhaFim, colunaFim).getImagem().addAction(Actions.removeActor());
            tabuleiro.setBloco(linhaFim, colunaFim, new BlocoGenerico(0));
            algoMudou = true;
        }

        // quando o bloco dobro (que está no destino do movimento) vai dobrar o outro 
        else if(Objects.equals(tabuleiro.getId(linhaFim, colunaFim), "2x"))
        {
            tabuleiro.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            if(tabuleiro.getBloco(linhaIni, colunaIni) instanceof BlocoGenerico)
            {
                ((BlocoGenerico) tabuleiro.getBloco(linhaIni, colunaIni)).dobra();
            }
            tabuleiro.getBloco(linhaFim, colunaFim).getImagem().addAction(Actions.removeActor());
            tabuleiro.setBloco(linhaFim, colunaFim, tabuleiro.getBloco(linhaIni, colunaIni));
            tabuleiro.getBloco(linhaIni, colunaIni).getImagem().addAction(Actions.removeActor());
            tabuleiro.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            tabuleiro.getBloco(linhaIni, colunaIni).setJuntado(true);
            algoMudou = true;
        }
        
        // quando o bloco dobro (que está na origem do movimento) vai dobrar o outro
        else if(Objects.equals(tabuleiro.getId(linhaIni, colunaIni), "2x"))
        {
            tabuleiro.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            tabuleiro.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            tabuleiro.getBloco(linhaFim, colunaFim).getImagem().addAction(Actions.removeActor());
            if(tabuleiro.getBloco(linhaFim, colunaFim) instanceof BlocoGenerico)
            {
                ((BlocoGenerico) tabuleiro.getBloco(linhaFim, colunaFim)).dobra();
            }
            tabuleiro.getBloco(linhaFim, colunaFim).setJuntado(true);
            algoMudou = true;
        }
    }

    public void atualizaVidas()
    {
        if (blocoBomba.getAtivo())
        {
            // diminui 1
            blocoBomba.setVida(-1);
            tabuleiro.getBloco(blocoBomba.getLinha(), blocoBomba.getColuna()).getImagem().addAction(Actions.removeActor());

            // setup das imagens para identificação do estado do bloco
            if(blocoBomba.getVida() == 2)
            {
                blocoBomba.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba_2:3.png"))));
            }
            else if(blocoBomba.getVida() == 1)
            {
                blocoBomba.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba_3:3.png"))));
            }
        }
        if (blocoTempo.getAtivo())
        {
            // diminui 1
            blocoTempo.setVida(-1);
            tabuleiro.getBloco(blocoTempo.getLinha(), blocoTempo.getColuna()).getImagem().addAction(Actions.removeActor());

            // setup das imagens para identificação do estado do bloco
            if(blocoTempo.getVida() == 3)
            {
                blocoTempo.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_3:4.png"))));
            }
            else if(blocoTempo.getVida() == 2)
            {
                blocoTempo.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_2:4.png"))));
            }
            else if(blocoTempo.getVida() == 1)
            {
                blocoTempo.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_1:4.png"))));
            }
        }
        if(blocoBomba.getVida() == 0)
        {
            algoMudou = true;
            blocoBomba.reset();
            miraVizinhos(blocoBomba.getLinha(), blocoBomba.getColuna());
        }
        if (blocoTempo.getVida() == 0)
        {
            algoMudou = true;
            blocoTempo.reset();
            tabuleiro.setBloco(blocoTempo.getLinha(), blocoTempo.getColuna(), new BlocoGenerico(0));
        }
    }

    private void miraVizinhos(int linha, int coluna)
    {
        explode(linha, coluna);

        linha--;
        coluna--;
        explode(linha, coluna);
        
        linha++;
        explode(linha, coluna);

        linha++;
        explode(linha, coluna);

        coluna++;
        explode(linha, coluna);

        coluna++;
        explode(linha, coluna);

        linha--;
        explode(linha, coluna);

        linha--;
        explode(linha, coluna);

        coluna--;
        explode(linha, coluna);
    }

    private void explode(int linha, int coluna)
    {
        if(linha >= 0 && linha < tabuleiro.getTamanho() && coluna >= 0 && coluna < tabuleiro.getTamanho())
        {
            SequenceAction animaExplosao = new SequenceAction(Actions.scaleTo(0, 0, .25f), Actions.removeActor());
            tabuleiro.getBloco(linha, coluna).getImagem().addAction(animaExplosao);
            tabuleiro.setBloco(linha, coluna, new BlocoGenerico(0));
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
