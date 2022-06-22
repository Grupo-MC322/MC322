package com.poo.jogo2048;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.poo.jogo2048.Telas.TelaInicial;


public class jogo2048 extends Game
{
	public SpriteBatch batch;
	public Stage stage;
	private int tamanhoTabuleiro = 4;
	public Controle controle;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		stage = new Stage();
		controle = new Controle();

		Gdx.input.setInputProcessor(stage);
		
		this.setScreen(new TelaInicial(this));

        Controle controle = new Controle();
	}

	public void setTamanhoTabuleiro(int tamanhoTabuleiro) {
		this.tamanhoTabuleiro = tamanhoTabuleiro;
	}

	public int getTamanhoTabuleiro() {
		return tamanhoTabuleiro;
	}

	
	@Override
	public void dispose ()
	{
		batch.dispose();
	}
}
