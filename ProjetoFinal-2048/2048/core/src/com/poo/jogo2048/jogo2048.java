package com.poo.jogo2048;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class jogo2048 extends ApplicationAdapter {
	SpriteBatch batch;
	private Tabuleiro tabuleiro;
	private OrthographicCamera camera;
	private Controle controle;

	enum Screen{
		inicial, mainGame, gameOver;
	}

	Screen currentScreen = Screen.inicial;
	
	@Override
	public void create () {
		// preenchimento do tabuleiro inicial
		tabuleiro = new Tabuleiro(4, 4);
		spawnBloco();
		controle = new Controle();

		// criando camera e SpriteBatch
		camera = new OrthographicCamera();
    	camera.setToOrtho(false, 400, 400);
    	batch = new SpriteBatch();
	}

	// função
	private void spawnBloco() {
		int i = MathUtils.random(0, 3);
		int j = MathUtils.random(0, 3);

		if (tabuleiro.getBloco(i, j).getNumero() == 0) {
			tabuleiro.setBloco(i, j, new Blocos(2));
			return;
		} else {
			spawnBloco();
		}
	}

	@Override
	public void render () {
		if(currentScreen == Screen.inicial){
			
		}
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1); // definição da cor de fundo
		
		// render inicial
		camera.update();
		batch.setProjectionMatrix(camera.combined);
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

		// ações caso as teclas de comando sejam pressionadas
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			spawnBloco();
			jogada('a');
			drawTabuleiro();
			
		} else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			spawnBloco();
			jogada('d');
			drawTabuleiro();
		} else if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			spawnBloco();
			jogada('s');
			drawTabuleiro();
		} else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			spawnBloco();
			jogada('w');
			drawTabuleiro();
		}
	}

	private void jogada(char direcao) {
		for(int i = 0; i < tabuleiro.getTamanhoX(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanhoY(); j++)
            {
				controle.juntaBloco(direcao, i, j, tabuleiro);
            }
        }
	}

	private void drawTabuleiro() {
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
	public void dispose () {
		batch.dispose();
		
		for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
				tabuleiro.getBloco(i, j).disposeImagem();
            }
        }
	}
}
