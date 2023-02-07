package com.poo.jogo2048;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.poo.jogo2048.Telas.HomeScreen;


public class Creator extends Game implements ICreatorSettingScreen
{
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	private int tamanhoTabuleiro = 4;
	private Control control;
	private Music music;

	@Override
	public void create()
	{
		// criação da câmera, do batch e do stage
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 500, 500);
		batch = new SpriteBatch();
		stage = new Stage(new StretchViewport(camera.viewportWidth, camera.viewportHeight));
		Gdx.input.setInputProcessor(stage);

		// criação do control
		control = new Control(this);

		// fazendo o load da música de fundo e a iniciando
		music = Gdx.audio.newMusic(Gdx.files.internal("musicas/Corona-320bit.mp3"));
		/* 
		Corona by Alexander Nakarada | https://www.serpentsoundstudios.com
		Music promoted by https://www.chosic.com/free-music/all/
		Attribution 4.0 International (CC BY 4.0)
		https://creativecommons.org/licenses/by/4.0/ 
		*/
		music.setLooping(true);
		music.play();
 
		// definindo a tela como tela inicial
		this.setScreen(new HomeScreen(this));
	}

	public SpriteBatch getBatch()
	{
		return batch;
	}

	public Stage getStage()
	{
		return stage;
	}

	public void setTamanhoTabuleiro(int tamanhoTabuleiro)
	{
		this.tamanhoTabuleiro = tamanhoTabuleiro;
	}

	public int getTamanhoTabuleiro()
	{
		return tamanhoTabuleiro;
	}

	public Control getControle()
	{
		return control;
	}

	public Music getMusic() {
		return music;
	}
}
