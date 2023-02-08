package com.poo.game2048.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.game2048.IControlSettingScreen;
import com.poo.game2048.ICreatorSettingScreen;
import com.poo.game2048.Creator;

public class SettingScreen extends AbstractScreen
{
    private final ICreatorSettingScreen game;
    private IControlSettingScreen control;
    private Stage stage;
    private SpriteBatch batch;
    private final Creator jogo;
    private OrthographicCamera camera;

    private Texture txtrFundo;
    private Texture txtr4x4;
    private Texture txtr5x5;
    private Texture txtr6x6;
    private Texture txtr7x7;
    private Texture txtrBomba;
    private Texture txtrDeleta;
    private Texture txtrTempo;
    private Texture txtr2x;
    private Texture txtrJogar;
    private Texture txtrBotaoMusica;

    public SettingScreen(final Creator jogo)
    {
        game = jogo;
        this.jogo = jogo;
        control = game.getControle();
        game.getStage().clear();
        this.stage = game.getStage();
        this.batch = game.getBatch();

        // configurações de camera
        camera = new OrthographicCamera();
		camera.setToOrtho(false, 500, 500);
    
        // setup inicial das texturas
        txtrFundo = new Texture(Gdx.files.internal("backgrounds/settings.png"));

        txtr4x4 = new Texture(Gdx.files.internal("buttons/4x4.png"));
        txtr5x5 = new Texture(Gdx.files.internal("buttons/5x5_unselected.png"));
        txtr6x6 = new Texture(Gdx.files.internal("buttons/6x6_unselected.png"));
        txtr7x7 = new Texture(Gdx.files.internal("buttons/7x7_unselected.png"));

        txtrBomba = new Texture(Gdx.files.internal("blocks/bomb.png"));
        txtrDeleta = new Texture(Gdx.files.internal("blocks/del.png"));
        txtrTempo = new Texture(Gdx.files.internal("blocks/time.png"));
        txtr2x = new Texture(Gdx.files.internal("blocks/2x.png"));

        txtrJogar = new Texture(Gdx.files.internal("buttons/play_2.png"));

        txtrBotaoMusica = new Texture(Gdx.files.internal("buttons/music.png"));

        // setup inicial das opções de blocos
        control.setBotaoSelected("bomb", true);
        control.setBotaoSelected("del", true);
        control.setBotaoSelected("time", true);
        control.setBotaoSelected("2x", true);
        control.setBotaoSelected("music", true);
    }

    @Override
	public void render(float delta)
    {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1);

		camera.update();

        // configurações do batch
		batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(txtrFundo, 0, 0, camera.viewportWidth, camera.viewportHeight);

        // criação e configuração do stage
        criaStage(batch, stage);

        batch.end();
	}

    public void criaStage(Batch batch, Stage stage)
    {
        // adicionando os atores

        // botões de tabuleiro
        Image botao4x4 = criaBotao(txtr4x4, 0.13, 0.65, 0.17, 0.17);
        Image botao5x5 = criaBotao(txtr5x5, 0.32, 0.65, 0.17, 0.17);
        Image botao6x6 = criaBotao(txtr6x6, 0.51, 0.65, 0.17, 0.17);
        Image botao7x7 = criaBotao(txtr7x7, 0.70, 0.65, 0.17, 0.17);

        // botões de blocos
        Image botaoBomba = criaBotao(txtrBomba, 0.13, 0.32, 0.17, 0.17);
        Image botaoDeleta = criaBotao(txtrDeleta, 0.32, 0.32, 0.17, 0.17);
        Image botaoTempo = criaBotao(txtrTempo, 0.51, 0.32, 0.17, 0.17);
        Image botao2x = criaBotao(txtr2x, 0.70, 0.32, 0.17, 0.17);

        // botão jogar
        Image botaoJogar = new Image(txtrJogar);
        botaoJogar.setX(500 / 2 - 0.37f * 500 / 2);
        botaoJogar.setY(0.1f * 500);
        botaoJogar.setWidth((float) (0.37 * 500));
        botaoJogar.setHeight((float) (0.1 * 500));
        botaoJogar.draw(batch, 1);
        stage.addActor(botaoJogar);

        // botão voltar
        Texture txtrBotaoVoltar = new Texture(Gdx.files.internal("buttons/back.png"));
        Image botaoVoltar = new Image(txtrBotaoVoltar);
        botaoVoltar.setPosition(stage.getWidth() * 0.05f, stage.getHeight() * 0.85f);
        botaoVoltar.setSize(stage.getWidth() * 0.1f, stage.getHeight() * 0.1f);
        botaoVoltar.draw(batch, 1);
        stage.addActor(botaoVoltar);

        // botão musica
        Image botaoMusica = new Image(txtrBotaoMusica);
        botaoMusica.setPosition(stage.getWidth() * 0.85f, stage.getHeight() * 0.85f);
        botaoMusica.setSize(stage.getWidth() * 0.1f, stage.getHeight() * 0.1f);
        botaoMusica.draw(batch, 1);
        stage.addActor(botaoMusica);

        // configurações de input dos botões
        botao4x4.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                game.setTamanhoTabuleiro(4);
                
                txtr4x4 = new Texture(Gdx.files.internal("buttons/4x4.png"));
                txtr5x5 = new Texture(Gdx.files.internal("buttons/5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("buttons/6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("buttons/7x7_unselected.png"));
            }
        });

        botao5x5.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                game.setTamanhoTabuleiro(5);

                txtr4x4 = new Texture(Gdx.files.internal("buttons/4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("buttons/5x5.png"));
                txtr6x6 = new Texture(Gdx.files.internal("buttons/6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("buttons/7x7_unselected.png"));
            }
        });

        botao6x6.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                game.setTamanhoTabuleiro(6);

                txtr4x4 = new Texture(Gdx.files.internal("buttons/4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("buttons/5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("buttons/6x6.png"));
                txtr7x7 = new Texture(Gdx.files.internal("buttons/7x7_unselected.png"));
            }
        });

        botao7x7.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                game.setTamanhoTabuleiro(7);

                txtr4x4 = new Texture(Gdx.files.internal("buttons/4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("buttons/5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("buttons/6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("buttons/7x7.png"));
            }
        });

        botaoBomba.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(control.getBotaoSelected("bomb"))
                {
                    control.setBotaoSelected("bomb", false);;
                    txtrBomba = new Texture(Gdx.files.internal("blocks/bomb_unselected.png"));
                }
                else
                {
                    control.setBotaoSelected("bomb", true);;
                    txtrBomba = new Texture(Gdx.files.internal("blocks/bomb.png"));
                }
            }
        });

        botaoDeleta.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(control.getBotaoSelected("del"))
                {
                    control.setBotaoSelected("del", false);;
                    txtrDeleta = new Texture(Gdx.files.internal("blocks/del_unselected.png"));
                }
                else
                {
                    control.setBotaoSelected("del", true);;
                    txtrDeleta = new Texture(Gdx.files.internal("blocks/del.png"));
                }
            }
        });

        botaoTempo.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(control.getBotaoSelected("time"))
                {
                    control.setBotaoSelected("time", false);
                    txtrTempo = new Texture(Gdx.files.internal("blocks/time_unselected.png"));
                }
                else
                {
                    control.setBotaoSelected("time", true);
                    txtrTempo = new Texture(Gdx.files.internal("blocks/time.png"));
                }
            }
        });

        botao2x.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(control.getBotaoSelected("2x"))
                {
                    control.setBotaoSelected("2x", false);
                    txtr2x = new Texture(Gdx.files.internal("blocks/2x_unselected.png"));
                }
                else
                {
                    control.setBotaoSelected("2x", true);
                    txtr2x = new Texture(Gdx.files.internal("blocks/2x.png"));
                }
            }
        });

        
        
        botaoMusica.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(control.getBotaoSelected("music"))
                {
                    game.getMusic().pause();
                    control.setBotaoSelected("music", false);
                    txtrBotaoMusica = new Texture(Gdx.files.internal("buttons/music_unselected.png"));
                }
                else
                {
                    game.getMusic().play();
                    control.setBotaoSelected("music", true);
                    txtrBotaoMusica = new Texture(Gdx.files.internal("buttons/music.png"));
                }
                    

            }
        });

        // conexão para o botão voltar inicializar a tela inicial
        adicionaConexao(botaoVoltar, "start");

        // conexão para o botao jogar inicializar a tela jogo
        adicionaConexao(botaoJogar, "game");
    }

    private Image criaBotao(Texture textura, Double posX, Double posY, Double width, Double height)
    {
        Image botao = new Image(textura);
        botao.setX((float) (posX * camera.viewportWidth));
        botao.setY((float) (posY * camera.viewportHeight));
        botao.setWidth((float) (width * camera.viewportWidth));
        botao.setHeight((float) (height * camera.viewportHeight));

        botao.draw(batch, 1);
        stage.addActor(botao);

        return botao;
    }

    private void adicionaConexao(Image botao, String tela)
    {
        if(tela.equals("start"))
        {
            botao.addListener(new ClickListener()
            {
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new HomeScreen(jogo));
                }
            });
        } 
        else if(tela.equals("game"))
        {
            botao.addListener(new ClickListener()
            {
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new GameScreen(jogo));
                }
            });
        }
        else if(tela.equals("instructions"))
        {
            botao.addListener(new ClickListener()
            {
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new InstructionScreen(jogo));
                }
            });
        }
    }
}