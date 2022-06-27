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
	private Tabuleiro tabuleiro;
	private int tamanhoTabuleiro = 4;
	private Controle controle;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		stage = new Stage();
		controle = new Controle(this);

		Gdx.input.setInputProcessor(stage);
		
		this.setScreen(new TelaInicial(this));
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Stage getStage() {
		return stage;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public void setTamanhoTabuleiro(int tamanhoTabuleiro)
	{
		this.tamanhoTabuleiro = tamanhoTabuleiro;
	}

	public int getTamanhoTabuleiro()
	{
		return tamanhoTabuleiro;
	}

	public Controle getControle() {
		return controle;
	}

	
	@Override
	public void dispose ()
	{
		batch.dispose();
	}
}
