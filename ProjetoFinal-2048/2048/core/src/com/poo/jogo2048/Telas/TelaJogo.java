package com.poo.jogo2048.Telas;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.Controle;
import com.poo.jogo2048.Tabuleiro;
import com.poo.jogo2048.jogo2048;

public class TelaJogo extends TelaAbstrata
{
    final jogo2048 jogo;
    Tabuleiro tabuleiro;
    Controle controle;
    Stage stage;
    private char direcao;

    OrthographicCamera camera;

    public TelaJogo(final jogo2048 jogo)
    {
        stage = new Stage();

        this.jogo = jogo;
        this.controle = jogo.controle;

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 400);

        tabuleiro = new Tabuleiro(jogo.getTamanhoTabuleiro());
        controle.spawnBloco(tabuleiro, controle);
    }

    private void leComando()
    {
        if(Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A))
            direcao = 'a';
        else if(Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D))
            direcao = 'd';
        else if(Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W))
            direcao = 'w';
        else if(Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S))
            direcao = 's';
        else
        {
            // System.out.println("Digite um movimento válido no teclado: W, A, S, D ou setas de direção")
            leComando();
        }
        
    }

    @Override
	public void render(float delta)
    {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1); // definição da cor de fundo

        // configurações de camera
		camera.update();
		jogo.batch.setProjectionMatrix(camera.combined);

        // configurações do batch
		jogo.batch.begin();
        
        // desenho inicial do tabuleiro
        for(int i = 0; i < tabuleiro.getTamanho(); i++)
        {
			{
				for(int j = 0; j < tabuleiro.getTamanho(); j++)
				{
					tabuleiro.getBloco(i, j).setPosX((float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / tabuleiro.getTamanho()) * i + (camera.viewportWidth * 0.01) * i));
					tabuleiro.getBloco(i, j).setPosY((float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()) * j + (camera.viewportHeight * 0.01) * j));
                    tabuleiro.getBloco(i, j).setSize((float) (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()));
                    tabuleiro.getBloco(i, j).getImagem().draw(jogo.batch, 1);
                    stage.addActor(tabuleiro.getBloco(i, j).getImagem());
				}
			}
        }

        stage.draw();
        stage.act();

        leComando();        
        iteraTabuleiro();
        jogo.batch.end();
	}

    private void jogada(int linha, int coluna)
    {
        if(!Objects.equals(tabuleiro.getId(linha, coluna), 0) && tabuleiro.getBloco(linha, coluna).getJuntado() == false)
        {
            controle.realizaComando(direcao, linha, coluna, tabuleiro, jogo.batch, stage);
        }
    }

    private void iteraTabuleiro()
	{
		if(direcao == 'w')
        {
            for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
            {
                for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
                {
                    jogada(linha, coluna);
                }
            }
        }
        else if(direcao == 's')
        {
            for(int linha = tabuleiro.getTamanho(); linha > 0; linha--)
            {
                for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
                {
                    jogada(linha, coluna);
                }
            }
        }
        else if(direcao == 'a')
        {
            for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
            {
                for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
                {
                    jogada(linha, coluna);
                }
            }
        }
        else if(direcao == 'd')
        {
            for(int coluna = tabuleiro.getTamanho(); coluna > 0; coluna--)
            {
                for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
                {
                    jogada(linha, coluna);
                }
            }
        }
        
        controle.atualizaVidas(tabuleiro);
        controle.zeraTabuleiro(tabuleiro);
        controle.spawnBloco(tabuleiro, controle);
	}

    @Override
	public void dispose()
    {
		// FAZER
	}
}
