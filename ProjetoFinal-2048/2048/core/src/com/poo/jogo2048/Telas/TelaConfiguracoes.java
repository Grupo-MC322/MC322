package com.poo.jogo2048.Telas;

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
import com.poo.jogo2048.ISettingScreenControl;
import com.poo.jogo2048.ISettingScreenCreator;
import com.poo.jogo2048.Criador;

public class TelaConfiguracoes extends TelaAbstrata
{
    private final ISettingScreenCreator game;
    private ISettingScreenControl control;
    private Stage stage;
    private SpriteBatch batch;
    private final Criador jogo;
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

    private boolean botaoMusicaSelected;

    public TelaConfiguracoes(final Criador jogo)
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
        txtrFundo = new Texture(Gdx.files.internal("telas/fundo_configuracoes.png"));

        txtr4x4 = new Texture(Gdx.files.internal("botoes/selecao_4x4.png"));
        txtr5x5 = new Texture(Gdx.files.internal("botoes/selecao_5x5_unselected.png"));
        txtr6x6 = new Texture(Gdx.files.internal("botoes/selecao_6x6_unselected.png"));
        txtr7x7 = new Texture(Gdx.files.internal("botoes/selecao_7x7_unselected.png"));

        txtrBomba = new Texture(Gdx.files.internal("blocos/bloco_bomba.png"));
        txtrDeleta = new Texture(Gdx.files.internal("blocos/bloco_deleta.png"));
        txtrTempo = new Texture(Gdx.files.internal("blocos/bloco_tempo.png"));
        txtr2x = new Texture(Gdx.files.internal("blocos/bloco_2x.png"));

        txtrJogar = new Texture(Gdx.files.internal("botoes/botao_jogar_2.png"));

        txtrBotaoMusica = new Texture(Gdx.files.absolute("botoes/botao_musica.png"));

        // setup inicial das opções de blocos
        control.setBotaoSelected("bomba", true);
        control.setBotaoSelected("deleta", true);
        control.setBotaoSelected("tempo", true);
        control.setBotaoSelected("2x", true);
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
        Texture txtrBotaoVoltar = new Texture(Gdx.files.absolute("botoes/botao_voltar.png"));
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
                
                txtr4x4 = new Texture(Gdx.files.internal("botoes/selecao_4x4.png"));
                txtr5x5 = new Texture(Gdx.files.internal("botoes/selecao_5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("botoes/selecao_6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("botoes/selecao_7x7_unselected.png"));
            }
        });

        botao5x5.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                game.setTamanhoTabuleiro(5);

                txtr4x4 = new Texture(Gdx.files.internal("botoes/selecao_4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("botoes/selecao_5x5.png"));
                txtr6x6 = new Texture(Gdx.files.internal("botoes/selecao_6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("botoes/selecao_7x7_unselected.png"));
            }
        });

        botao6x6.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                game.setTamanhoTabuleiro(6);

                txtr4x4 = new Texture(Gdx.files.internal("botoes/selecao_4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("botoes/selecao_5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("botoes/selecao_6x6.png"));
                txtr7x7 = new Texture(Gdx.files.internal("botoes/selecao_7x7_unselected.png"));
            }
        });

        botao7x7.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                game.setTamanhoTabuleiro(7);

                txtr4x4 = new Texture(Gdx.files.internal("botoes/selecao_4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("botoes/selecao_5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("botoes/selecao_6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("botoes/selecao_7x7.png"));
            }
        });

        botaoBomba.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(control.getBotaoSelected("bomba"))
                {
                    control.setBotaoSelected("bomba", false);;
                    txtrBomba = new Texture(Gdx.files.internal("blocos/bloco_bomba_unselected.png"));
                }
                else
                {
                    control.setBotaoSelected("bomba", true);;
                    txtrBomba = new Texture(Gdx.files.internal("blocos/bloco_bomba.png"));
                }
            }
        });

        botaoDeleta.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(control.getBotaoSelected("deleta"))
                {
                    control.setBotaoSelected("deleta", false);;
                    txtrDeleta = new Texture(Gdx.files.internal("blocos/bloco_deleta_unselected.png"));
                }
                else
                {
                    control.setBotaoSelected("deleta", true);;
                    txtrDeleta = new Texture(Gdx.files.internal("blocos/bloco_deleta.png"));
                }
            }
        });

        botaoTempo.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(control.getBotaoSelected("tempo"))
                {
                    control.setBotaoSelected("tempo", false);
                    txtrTempo = new Texture(Gdx.files.internal("blocos/bloco_tempo_unselected.png"));
                }
                else
                {
                    control.setBotaoSelected("tempo", true);
                    txtrTempo = new Texture(Gdx.files.internal("blocos/bloco_tempo.png"));
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
                    txtr2x = new Texture(Gdx.files.internal("blocos/bloco_2x_unselected.png"));
                }
                else
                {
                    control.setBotaoSelected("2x", true);
                    txtr2x = new Texture(Gdx.files.internal("blocos/bloco_2x.png"));
                }
            }
        });

        
        
        botaoMusica.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(botaoMusicaSelected)
                {
                    game.getMusic().pause();
                    botaoMusicaSelected = false;
                    txtrBotaoMusica = new Texture(Gdx.files.internal("botoes/botao_musica_unselected.png"));
                }
                else
                {
                    game.getMusic().play();
                    botaoMusicaSelected = true;
                    txtrBotaoMusica = new Texture(Gdx.files.internal("botoes/botao_musica.png"));
                }
                    

            }
        });

        // conexão para o botão voltar inicializar a tela inicial
        adicionaConexao(botaoVoltar, "inicial");

        // conexão para o botao jogar inicializar a tela jogo
        adicionaConexao(botaoJogar, "jogo");
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
        if(tela.equals("inicial"))
        {
            botao.addListener(new ClickListener()
            {
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new TelaInicial(jogo));
                }
            });
        } 
        else if(tela.equals("jogo"))
        {
            botao.addListener(new ClickListener()
            {
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new TelaJogo(jogo));
                }
            });
        }
        else if(tela.equals("instrucoes"))
        {
            botao.addListener(new ClickListener()
            {
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new TelaInstrucoes(jogo));
                }
            });
        }
    }
}