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

public class Controle implements IGameScreenControl, ISettingScreenControl
{
    final ICreatorControl game;
    final Criador jogo;
    private Stage stage;
    private SpriteBatch batch;
    private IBoardControl board;

    private boolean botaoBombaSelected;
    private boolean botaoDeletaSelected;
    private boolean botaoTempoSelected;
    private boolean botao2xSelected;

    private IBombControl bomb;
    private ITimerControl timer;

    private int linhaFim = 0;
    private int colunaFim = 0;

    private boolean vazioInexistente;
    private boolean algoMudou;

    public Controle(final Criador jogo)
    {
        this.jogo = jogo;
        game = jogo;
        this.batch = game.getBatch();
        this.stage = game.getStage();
        
        bomb = BlocoBomba.getInstance();
        timer = BlocoTempo.getInstance();
    }

    // Adiciona um bloco entre 1, 2, 4 ou especiais em alguma posição vazia do tabuleiro.
    public void spawnBloco()
    {
        IBlocos blocoGerado = new BlocoGenerico(0);
        Random random = new Random();
        int linha = random.nextInt(board.getTamanho());
		int coluna = random.nextInt(board.getTamanho());

        // definição das probabilidades de cada bloco ser adicionado
		if (Objects.equals(board.getId(linha, coluna), 0))
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
            else if (index < 85 && bomb.getAtivo() == false && getBotaoSelected("bomba"))
            {
                blocoGerado = bomb;
                
                bomb.setAtivo(true);
                bomb.setLinha(linha);
                bomb.setColuna(coluna);
            }
            else if (index < 90 && timer.getAtivo() == false && getBotaoSelected("tempo"))
            {
                blocoGerado = timer;

                timer.setAtivo(true);
                timer.setLinha(linha);
                timer.setColuna(coluna);
            }
            else if (index < 95 && getBotaoSelected("deleta"))
            {
                blocoGerado = new BlocoDeleta();
            }
            else if (index < 100 && getBotaoSelected("2x"))
            {
                blocoGerado = new BlocoDobro();
            }
            else
            {
                spawnBloco();
            }
            
            // adicionando o novo bloco ao tabuleiro e animando
            board.setBloco(linha, coluna, blocoGerado);
            board.getBloco(linha, coluna).getImagem().setScale(.75f);
			board.getBloco(linha, coluna).getImagem().addAction(Actions.scaleTo(1, 1, .25f));
		}
		else
		{
			spawnBloco();
		}
    }

    public void transfereComando(char direcao)
	{
        if(algoMudou)
        {
            atualizaVidas();
            algoMudou = false;
        }
        
		if(direcao == 'w')
        {
            for(int coluna = board.getTamanho() - 1; coluna >= 0; coluna--)
            {
                for(int linha = 0; linha < board.getTamanho(); linha++)
                {
                    verificaViabilidade(linha, coluna, direcao);
                }
            }
        }
        else if(direcao == 's')
        {
            for(int coluna = 0; coluna < board.getTamanho(); coluna++)
            {
                for(int linha = board.getTamanho() - 1; linha >= 0; linha--)
                {
                    verificaViabilidade(linha, coluna, direcao);
                }
            }
        }
        else if(direcao == 'a')
        {
            for(int linha = 0; linha < board.getTamanho(); linha++)
            {
                for(int coluna = 0; coluna < board.getTamanho(); coluna++)
                {
                    verificaViabilidade(linha, coluna, direcao);
                }
            }
        }
        else if(direcao == 'd')
        {
            for(int linha = board.getTamanho() - 1; linha >= 0; linha--)
            {
                for(int coluna = 0; coluna < board.getTamanho(); coluna++)
                {
                    verificaViabilidade(linha, coluna, direcao);
                }
            }
        }
        
        percorreTabuleiro();
        if(algoMudou)
        {
            spawnBloco();
        }
	}

    private void verificaViabilidade(int linha, int coluna, char direcao)
    {
        if(!Objects.equals(board.getId(linha, coluna), 0) && board.getBloco(linha, coluna).getJuntado() == false)
        {
            interpretaComando(direcao, linha, coluna, batch, stage);
        }
    }
    
    public void interpretaComando(char direcao, int linhaIni, int colunaIni, SpriteBatch batch, Stage stage)
    {
        planejaMovimento(direcao, linhaIni, colunaIni);

        if(0 <= linhaFim && linhaFim < board.getTamanho() && 0 <= colunaFim && colunaFim < board.getTamanho())
        {
            if(board.getBloco(linhaIni, colunaIni) instanceof IBlocosVidas && (Objects.equals(board.getId(linhaFim, colunaFim), 0) || Objects.equals(board.getId(linhaFim, colunaFim), "deleta") || Objects.equals(board.getId(linhaFim, colunaFim), "2x")))
            {
                ((IBlocosVidas) board.getBloco(linhaIni, colunaIni)).setLinha(linhaFim);
                ((IBlocosVidas) board.getBloco(linhaIni, colunaIni)).setColuna(colunaFim);
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

    // Realiza a movimentação de um bloco de uma posição inicial indicada para a posição final definida.
    private void movimenta(char direcao, int linhaIni, int colunaIni, SpriteBatch batch, Stage stage)
    {
        // animação
        float posicaoXBloco = ((500 * 0.05f) + (500 * 0.87f / board.getTamanho()) * linhaFim + (500 * 0.01f) * linhaFim);
        float posicaoYBloco = ((500 * 0.05f) + (500 * 0.87f / board.getTamanho()) * colunaFim + (500 * 0.01f) * colunaFim);
        MoveToAction juntaBloco = new MoveToAction();
        juntaBloco.setPosition(posicaoXBloco,posicaoYBloco);
        juntaBloco.setDuration(0.35f);
        juntaBloco.setInterpolation(Interpolation.smooth);
        board.getBloco(linhaIni, colunaIni).getImagem().addAction(juntaBloco);
        SequenceAction animaBloco = new SequenceAction(juntaBloco, Actions.removeActor());

        // quando está vazio na frente, livre para continuar se movendo
        if(Objects.equals(board.getId(linhaFim, colunaFim), 0))
        {
            board.setBloco(linhaFim, colunaFim, board.getBloco(linhaIni, colunaIni));
            board.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            board.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            interpretaComando(direcao, linhaFim, colunaFim, batch, stage);
            algoMudou = true;
        }

        // quando ambos os blocos são iguais e podem se juntar
        else if(Objects.equals(board.getId(linhaFim, colunaFim), board.getId(linhaIni, colunaIni)))
        {
            board.getBloco(linhaFim, colunaFim).getImagem().addAction(Actions.removeActor());
            if(board.getBloco(linhaFim, colunaFim) instanceof BlocoGenerico)
            {
                ((BlocoGenerico) board.getBloco(linhaFim, colunaFim)).dobra();
            }
            board.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            board.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            board.getBloco(linhaFim, colunaFim).setJuntado(true);
            algoMudou = true;
        }

        // quando o bloco deleta deleta o outro: ou quando o deleta está na posição final ou na inicial
        else if(Objects.equals(board.getId(linhaFim, colunaFim), "deleta") || Objects.equals(board.getId(linhaIni, colunaIni), "deleta"))
        {
            board.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            board.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            board.getBloco(linhaFim, colunaFim).getImagem().addAction(Actions.removeActor());
            board.setBloco(linhaFim, colunaFim, new BlocoGenerico(0));
            algoMudou = true;
        }

        // quando o bloco dobro (que está no destino do movimento) vai dobrar o outro 
        else if(Objects.equals(board.getId(linhaFim, colunaFim), "2x"))
        {
            board.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            if(board.getBloco(linhaIni, colunaIni) instanceof BlocoGenerico)
            {
                ((BlocoGenerico) board.getBloco(linhaIni, colunaIni)).dobra();
            }
            board.getBloco(linhaFim, colunaFim).getImagem().addAction(Actions.removeActor());
            board.setBloco(linhaFim, colunaFim, board.getBloco(linhaIni, colunaIni));
            board.getBloco(linhaIni, colunaIni).getImagem().addAction(Actions.removeActor());
            board.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            board.getBloco(linhaIni, colunaIni).setJuntado(true);
            algoMudou = true;
        }
        
        // quando o bloco dobro (que está na origem do movimento) vai dobrar o outro
        else if(Objects.equals(board.getId(linhaIni, colunaIni), "2x"))
        {
            board.getBloco(linhaIni, colunaIni).getImagem().addAction(animaBloco);
            board.setBloco(linhaIni, colunaIni, new BlocoGenerico(0));
            board.getBloco(linhaFim, colunaFim).getImagem().addAction(Actions.removeActor());
            if(board.getBloco(linhaFim, colunaFim) instanceof BlocoGenerico)
            {
                ((BlocoGenerico) board.getBloco(linhaFim, colunaFim)).dobra();
            }
            board.getBloco(linhaFim, colunaFim).setJuntado(true);
            algoMudou = true;
        }
    }

    public void atualizaVidas()
    {
        if (bomb.getAtivo())
        {
            // diminui 1
            bomb.setVida(-1);
            board.getBloco(bomb.getLinha(), bomb.getColuna()).getImagem().addAction(Actions.removeActor());

            // setup das imagens para identificação do estado do bloco
            if(bomb.getVida() == 2)
            {
                bomb.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba_2:3.png"))));
            }
            else if(bomb.getVida() == 1)
            {
                bomb.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba_3:3.png"))));
            }
        }
        if (timer.getAtivo())
        {
            // diminui 1
            timer.setVida(-1);
            board.getBloco(timer.getLinha(), timer.getColuna()).getImagem().addAction(Actions.removeActor());

            // setup das imagens para identificação do estado do bloco
            if(timer.getVida() == 3)
            {
                timer.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_3:4.png"))));
            }
            else if(timer.getVida() == 2)
            {
                timer.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_2:4.png"))));
            }
            else if(timer.getVida() == 1)
            {
                timer.setImagem(new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo_1:4.png"))));
            }
        }
        if(bomb.getVida() == 0)
        {
            algoMudou = true;
            bomb.reset();
            miraVizinhos(bomb.getLinha(), bomb.getColuna());
        }
        if (timer.getVida() == 0)
        {
            algoMudou = true;
            timer.reset();
            board.setBloco(timer.getLinha(), timer.getColuna(), new BlocoGenerico(0));
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
        if(linha >= 0 && linha < board.getTamanho() && coluna >= 0 && coluna < board.getTamanho())
        {
            SequenceAction animaExplosao = new SequenceAction(Actions.scaleTo(0, 0, .25f), Actions.removeActor());
            board.getBloco(linha, coluna).getImagem().addAction(animaExplosao);
            board.setBloco(linha, coluna, new BlocoGenerico(0));
        }
    }

    public void percorreTabuleiro()
    {
        vazioInexistente = true;
        for(int i = 0; i < board.getTamanho(); i++)
        {
            for(int j = 0; j < board.getTamanho(); j++)
            {
                if(Objects.equals(board.getId(i, j), 2048))
                {
                    jogo.setScreen(new TelaGanhou(jogo));
                }
                else if(Objects.equals(board.getId(i, j), 0))
                {
                    vazioInexistente = false;
                }
                board.getBloco(i, j).setJuntado(false);
            }
        }
        if(vazioInexistente)
        {
            jogo.setScreen(new TelaPerdeu(jogo));
        }
    }

    public void setBotaoSelected(String idBotao, boolean selected)
    {
        switch(idBotao)
        {
            case("bomba"):
                botaoBombaSelected = selected;
                break;
            case("deleta"):
                botaoDeletaSelected = selected;
                break;
            case("tempo"):
                botaoTempoSelected = selected;
                break;
            case("2x"):
                botao2xSelected = selected;
                break;
        }
    }

    public boolean getBotaoSelected(String idBotao)
    {
        switch(idBotao)
        {
            case("bomba"):
                return botaoBombaSelected;
            case("deleta"):
                return botaoDeletaSelected;
            case("tempo"):
                return botaoTempoSelected;
            case("2x"):
                return botao2xSelected;
            default:
                return false;
        }
    }

    public void conectaTabuleiro(Tabuleiro tabuleiro)
    {
        board = tabuleiro;
    }
}
