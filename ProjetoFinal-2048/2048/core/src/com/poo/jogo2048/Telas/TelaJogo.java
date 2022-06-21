package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.Controle;
import com.poo.jogo2048.Tabuleiro;
import com.poo.jogo2048.jogo2048;

public class TelaJogo extends TelaAbstrata
{
    final jogo2048 jogo;
    Tabuleiro tabuleiro;
    Controle controle;

    OrthographicCamera camera;

    public TelaJogo(final jogo2048 jogo)
    {
        this.jogo = jogo;
        this.tabuleiro = jogo.tabuleiro;
        this.controle = jogo.controle;

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 400);
    }

    @Override
	public void render(float delta) {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1); // definição da cor de fundo

		camera.update();
		jogo.batch.setProjectionMatrix(camera.combined);

        // configurações do batch
		jogo.batch.begin();
        
        for(int i = 0; i < jogo.tabuleiro.getTamanhoX(); i++)
        {
			{
				for(int j = 0; j < jogo.tabuleiro.getTamanhoY(); j++)
				{
					Float posX = (float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / jogo.tabuleiro.getTamanhoX()) * i + (camera.viewportWidth * 0.01) * i);
					Float posY = (float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / jogo.tabuleiro.getTamanhoY()) * j + (camera.viewportHeight * 0.01) * j);
					jogo.batch.draw(jogo.tabuleiro.getBloco(i, j).getImagem(), posX, posY, (float) (camera.viewportWidth * 0.87 / jogo.tabuleiro.getTamanhoX()), (float) (camera.viewportHeight * 0.87 / jogo.tabuleiro.getTamanhoY()));
				}
			}
        }

		jogo.batch.end();

        // ações caso as teclas de comando sejam pressionadas
        if(Gdx.input.isKeyJustPressed(Keys.LEFT))
        {
            jogo.jogada('a');
            jogo2048.spawnBloco(tabuleiro, controle);
            drawTabuleiro(jogo.batch, jogo.tabuleiro);
            
        }
        else if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
        {
            jogo.jogada('d');
            jogo2048.spawnBloco(tabuleiro, controle);
            drawTabuleiro(jogo.batch, jogo.tabuleiro);
        }
        else if(Gdx.input.isKeyJustPressed(Keys.UP))
        {
            jogo.jogada('s');
            jogo2048.spawnBloco(tabuleiro, controle);
            drawTabuleiro(jogo.batch, jogo.tabuleiro);
        }
        else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
        {
            jogo.jogada('w');
            jogo2048.spawnBloco(tabuleiro, controle);
            drawTabuleiro(jogo.batch, jogo.tabuleiro);
        }
	}

    private void drawTabuleiro(Batch batch, Tabuleiro tabuleiro)
	{
		batch.begin();
		for(int i = 0; i < tabuleiro.getTamanhoX(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanhoY(); j++)
            {
				Float posX = (float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / tabuleiro.getTamanhoX()) * i + (camera.viewportWidth * 0.01) * i);
				Float posY = (float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / tabuleiro.getTamanhoY()) * j + (camera.viewportHeight * 0.01) * j);
				batch.draw(tabuleiro.getBloco(i, j).getImagem(), posX, posY, (float) (camera.viewportWidth * 0.87 / tabuleiro.getTamanhoX()), (float) (camera.viewportHeight * 0.87 / tabuleiro.getTamanhoY()));
            }
        }
		batch.end();
	}

    @Override
	public void resize(int width, int height) {
	}

	@Override
	public void show()
    {}

	@Override
	public void hide()
    {}

	@Override
	public void pause()
    {}

	@Override
	public void resume()
    {}

    @Override
	public void dispose() {
		// FAZER
	}
}
