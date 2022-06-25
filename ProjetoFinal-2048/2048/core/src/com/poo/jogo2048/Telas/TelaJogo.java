package com.poo.jogo2048.Telas;

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

    @Override
	public void render(float delta) {
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

        // configuração dos inputs das teclas de comando
        if(Gdx.input.isKeyJustPressed(Keys.LEFT))
            jogada('a');

        else if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
            jogada('d');

        else if(Gdx.input.isKeyJustPressed(Keys.UP))
            jogada('w');
        
        else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
            jogada('s');
        
        jogo.batch.end();
	}

    public void jogada(char direcao)
	{
		for(int i = 0; i < tabuleiro.getTamanho(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanho(); j++)
            {
				controle.realizaComando(direcao, i, j, tabuleiro, jogo.batch, stage);
            }
        }
        controle.spawnBloco(tabuleiro, controle);
	}

    @Override
	public void dispose() {
		// FAZER
	}
}
