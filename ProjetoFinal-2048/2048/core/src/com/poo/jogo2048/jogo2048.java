package com.poo.jogo2048;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.PastaBlocos.BlocoGenerico;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class jogo2048 extends ApplicationAdapter
{
	private SpriteBatch batch;
	private Stage stage;
	private Tabuleiro tabuleiro;
	private OrthographicCamera camera;
	private Controle controle;

	enum Screen
	{
		inicial, instrucoes, configuracoes, jogo, gameOver;
	}

	Screen currentScreen = Screen.inicial;
	
	@Override
	public void create()
	{
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
	private void spawnBloco()
	{
		int i = MathUtils.random(0, 3);
		int j = MathUtils.random(0, 3);

		if (tabuleiro.getBloco(i, j).getNumero() == 0)
		{
			tabuleiro.setBloco(i, j, new BlocoGenerico(2));
			return;
		}
		else
		{
			spawnBloco();
		}
	}

	@Override
	public void render ()
	{
		if(currentScreen == Screen.inicial)
		{
			ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1); // definição da cor de fundo
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			batch.draw(new Texture(Gdx.files.internal("fundo_tela_inicio.png")), 0, 0, camera.viewportWidth, camera.viewportHeight);
			stage = new Stage();

			// configuração do botao configurar
			Image imagemBotao_configurar = new Image(new Texture(Gdx.files.internal("botao_configurar.png")));
			ImageButton botao_configurar = new ImageButton(imagemBotao_configurar.getDrawable());

			botao_configurar.setX((float) ((camera.viewportWidth / 2) - (camera.viewportWidth * 0.5 / 2)));
			botao_configurar.setY((float) (camera.viewportHeight * 0.44));
			botao_configurar.setWidth((float) (camera.viewportWidth * 0.5));
			botao_configurar.setHeight((float) (camera.viewportHeight * 0.1));

			botao_configurar.draw(batch, 1);
			stage.addActor(botao_configurar);

			Gdx.input.setInputProcessor(stage);

			botao_configurar.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					currentScreen = Screen.configuracoes;
				}
			});

			// configuração do botão instruções
			Image imagemBotao_instrucoes = new Image(new Texture(Gdx.files.internal("botao_instrucoes.png")));
			ImageButton botao_instrucoes = new ImageButton(imagemBotao_instrucoes.getDrawable());

			botao_instrucoes.setX((float) ((camera.viewportWidth / 2) - (camera.viewportWidth * 0.5 / 2)));
			botao_instrucoes.setY((float) (camera.viewportHeight * 0.27));
			botao_instrucoes.setWidth((float) (camera.viewportWidth * 0.5));
			botao_instrucoes.setHeight((float) (camera.viewportHeight * 0.1));

			botao_instrucoes.draw(batch, 1);
			stage.addActor(botao_instrucoes);

			botao_instrucoes.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					currentScreen = Screen.jogo;
				}
			});

			batch.end();
		}
		else if(currentScreen == Screen.configuracoes)
		{
			ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1); // definição da cor de fundo
			stage.clear();

			// render inicial
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();

			batch.draw(new Texture(Gdx.files.internal("fundo_configuracoes.png")), 0, 0, camera.viewportWidth, camera.viewportHeight);
			stage = new Stage();

			Image imagemBotao_4x4 = new Image(new Texture(Gdx.files.internal("selecao_4x4.png")));
			Image imagemBotao_4x4_unselec = new Image(new Texture(Gdx.files.internal("selecao_4x4_unselected.png")));
			Image imagemBotao_5x5 = new Image(new Texture(Gdx.files.internal("selecao_5x5.png")));
			Image imagemBotao_5x5_unselec = new Image(new Texture(Gdx.files.internal("selecao_5x5_unselected.png")));
			Image imagemBotao_6x6 = new Image(new Texture(Gdx.files.internal("selecao_6x6.png")));
			Image imagemBotao_6x6_unselec = new Image(new Texture(Gdx.files.internal("selecao_6x6_unselected.png")));

			// configuração do botao 4x4
			ImageButton botao_4x4 = new ImageButton(imagemBotao_4x4.getDrawable());

			botao_4x4.setX((float) (0.13 * camera.viewportWidth));
			botao_4x4.setY((float) (0.65 * camera.viewportHeight));
			botao_4x4.setWidth((float) (0.17 * camera.viewportWidth));
			botao_4x4.setHeight((float) (0.17 * camera.viewportHeight));

			botao_4x4.draw(batch, 1);
			stage.addActor(botao_4x4);

			botao_4x4.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					
				}
			});

			// configuração do botao 5x5
			ImageButton botao_5x5 = new ImageButton(imagemBotao_5x5_unselec.getDrawable());

			botao_5x5.setX((float) (0.32 * camera.viewportWidth));
			botao_5x5.setY((float) (0.65 * camera.viewportHeight));
			botao_5x5.setWidth((float) (0.17 * camera.viewportWidth));
			botao_5x5.setHeight((float) (0.17 * camera.viewportHeight));

			botao_5x5.draw(batch, 1);
			stage.addActor(botao_5x5);

			// configuração do botao 6x6
			ImageButton botao_6x6 = new ImageButton(imagemBotao_6x6_unselec.getDrawable());

			botao_6x6.setX((float) (0.51 * camera.viewportWidth));
			botao_6x6.setY((float) (0.65 * camera.viewportHeight));
			botao_6x6.setWidth((float) (0.17 * camera.viewportWidth));
			botao_6x6.setHeight((float) (0.17 * camera.viewportHeight));

			botao_6x6.draw(batch, 1);
			stage.addActor(botao_6x6);

			// configuração do botao 7x7
			Image imagemBotao_7x7 = new Image(new Texture(Gdx.files.internal("selecao_7x7_unselected.png")));
			ImageButton botao_7x7 = new ImageButton(imagemBotao_7x7.getDrawable());

			botao_7x7.setX((float) (0.70 * camera.viewportWidth));
			botao_7x7.setY((float) (0.65 * camera.viewportHeight));
			botao_7x7.setWidth((float) (0.17 * camera.viewportWidth));
			botao_7x7.setHeight((float) (0.17 * camera.viewportHeight));

			botao_7x7.draw(batch, 1);
			stage.addActor(botao_7x7);

			// configuração do botao bomba
			Image imagemBotao_bomba = new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba.png")));
			ImageButton botao_bomba = new ImageButton(imagemBotao_bomba.getDrawable());

			botao_bomba.setX((float) (0.13 * camera.viewportWidth));
			botao_bomba.setY((float) (0.32 * camera.viewportHeight));
			botao_bomba.setWidth((float) (0.17 * camera.viewportWidth));
			botao_bomba.setHeight((float) (0.17 * camera.viewportHeight));

			botao_bomba.draw(batch, 1);
			stage.addActor(botao_bomba);

			// configuração do botao deleta
			Image imagemBotao_deleta = new Image(new Texture(Gdx.files.internal("blocos/bloco_deleta.png")));
			ImageButton botao_deleta = new ImageButton(imagemBotao_deleta.getDrawable());

			botao_deleta.setX((float) (0.32 * camera.viewportWidth));
			botao_deleta.setY((float) (0.32 * camera.viewportHeight));
			botao_deleta.setWidth((float) (0.17 * camera.viewportWidth));
			botao_deleta.setHeight((float) (0.17 * camera.viewportHeight));

			botao_deleta.draw(batch, 1);
			stage.addActor(botao_deleta);

			// configuração do botao timer
			Image imagemBotao_timer = new Image(new Texture(Gdx.files.internal("blocos/bloco_timer.png")));
			ImageButton botao_timer = new ImageButton(imagemBotao_timer.getDrawable());

			botao_timer.setX((float) (0.51 * camera.viewportWidth));
			botao_timer.setY((float) (0.32 * camera.viewportHeight));
			botao_timer.setWidth((float) (0.17 * camera.viewportWidth));
			botao_timer.setHeight((float) (0.17 * camera.viewportHeight));

			botao_timer.draw(batch, 1);
			stage.addActor(botao_timer);

			// configuração do botao timer
			Image imagemBotao_2x = new Image(new Texture(Gdx.files.internal("blocos/bloco_2x.png")));
			ImageButton botao_2x = new ImageButton(imagemBotao_2x.getDrawable());

			botao_2x.setX((float) (0.70 * camera.viewportWidth));
			botao_2x.setY((float) (0.32 * camera.viewportHeight));
			botao_2x.setWidth((float) (0.17 * camera.viewportWidth));
			botao_2x.setHeight((float) (0.17 * camera.viewportHeight));

			botao_2x.draw(batch, 1);
			stage.addActor(botao_2x);

			// configuração do botao jogar
			Image imagemBotao_jogar = new Image(new Texture(Gdx.files.internal("botao_jogar.png")));
			ImageButton botao_jogar = new ImageButton(imagemBotao_jogar.getDrawable());

			botao_jogar.setX((float) (400 / 2 - 0.37 * 200));
			botao_jogar.setY((float) (0.1 * 400));
			botao_jogar.setWidth((float) (0.37 * 400));
			botao_jogar.setHeight((float) (0.17 * 400));

			botao_jogar.draw(batch, 1);
			stage.addActor(botao_jogar);

			botao_jogar.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					currentScreen = Screen.jogo;
				}
			});

			batch.end();
			stage.clear();
		}
		else if(currentScreen == Screen.jogo)
		{
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
			if(Gdx.input.isKeyJustPressed(Keys.LEFT))
			{
				jogada('a');
				spawnBloco();
				drawTabuleiro();
				
			}
			else if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
			{
				jogada('d');
				spawnBloco();
				drawTabuleiro();
			}
			else if(Gdx.input.isKeyJustPressed(Keys.UP))
			{
				jogada('s');
				spawnBloco();
				drawTabuleiro();
			}
			else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
			{
				jogada('w');
				spawnBloco();
				drawTabuleiro();
			}
		}
	}

	private void jogada(char direcao)
	{
		for(int i = 0; i < tabuleiro.getTamanhoX(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanhoY(); j++)
            {
				controle.juntaBloco(direcao, i, j, tabuleiro);
            }
        }
	}

	private void drawTabuleiro()
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
	public void dispose ()
	{
		batch.dispose();
		
		for(int i = 0; i < tabuleiro.getTamanhoX(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanhoY(); j++)
            {
				tabuleiro.getBloco(i, j).disposeImagem();
            }
        }
	}
}
