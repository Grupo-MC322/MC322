package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.Controle;
import com.poo.jogo2048.jogo2048;

public class TelaConfiguracoes extends TelaAbstrata
{
    private final jogo2048 jogo;
    private Controle controle;
    private Stage stage;

    OrthographicCamera camera;

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

    public TelaConfiguracoes(final jogo2048 jogo)
    {
        this.jogo = jogo;
        this.controle = jogo.getControle();
        jogo.getStage().clear();
        this.stage = jogo.getStage();

        // configurações de camera
        camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 400);
    
        // setup inicial das texturas
        txtrFundo = new Texture(Gdx.files.internal("fundo_configuracoes.png"));

        txtr4x4 = new Texture(Gdx.files.internal("selecao_4x4.png"));
        txtr5x5 = new Texture(Gdx.files.internal("selecao_5x5_unselected.png"));
        txtr6x6 = new Texture(Gdx.files.internal("selecao_6x6_unselected.png"));
        txtr7x7 = new Texture(Gdx.files.internal("selecao_7x7_unselected.png"));

        txtrBomba = new Texture(Gdx.files.internal("blocos/bloco_bomba.png"));
        txtrDeleta = new Texture(Gdx.files.internal("blocos/bloco_deleta.png"));
        txtrTempo = new Texture(Gdx.files.internal("blocos/bloco_tempo.png"));
        txtr2x = new Texture(Gdx.files.internal("blocos/bloco_2x.png"));

        txtrJogar = new Texture(Gdx.files.internal("botao_jogar.png"));

        // setup inicial das opções de blocos
        controle.setBotaoBombaSelected(true);
        controle.setBotaoDeletaSelected(true);
        controle.setBotaoTempoSelected(true);
        controle.setBotao2xSelected(true);
    }

    @Override
	public void render(float delta)
    {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1);

		camera.update();

        // configurações do batch
		jogo.batch.setProjectionMatrix(camera.combined);
        jogo.batch.begin();
        jogo.batch.draw(txtrFundo, 0, 0, camera.viewportWidth, camera.viewportHeight);

        // criação e configuração do stage
        criaStage(jogo.batch, stage);

        jogo.batch.end();
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
        botaoJogar.setX((float) (400 / 2 - 0.37 * 200));
        botaoJogar.setY((float) (0.1 * 400));
        botaoJogar.setWidth((float) (0.37 * 400));
        botaoJogar.setHeight((float) (0.1 * 400));
        botaoJogar.draw(batch, 1);
        stage.addActor(botaoJogar);

        // configurações de input dos botões
        botao4x4.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                jogo.setTamanhoTabuleiro(4);
                
                txtr4x4 = new Texture(Gdx.files.internal("selecao_4x4.png"));
                txtr5x5 = new Texture(Gdx.files.internal("selecao_5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("selecao_6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("selecao_7x7_unselected.png"));
            }
        });

        botao5x5.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                jogo.setTamanhoTabuleiro(5);

                txtr4x4 = new Texture(Gdx.files.internal("selecao_4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("selecao_5x5.png"));
                txtr6x6 = new Texture(Gdx.files.internal("selecao_6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("selecao_7x7_unselected.png"));
            }
        });

        botao6x6.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                jogo.setTamanhoTabuleiro(6);

                txtr4x4 = new Texture(Gdx.files.internal("selecao_4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("selecao_5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("selecao_6x6.png"));
                txtr7x7 = new Texture(Gdx.files.internal("selecao_7x7_unselected.png"));
            }
        });

        botao7x7.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                jogo.setTamanhoTabuleiro(7);

                txtr4x4 = new Texture(Gdx.files.internal("selecao_4x4_unselected.png"));
                txtr5x5 = new Texture(Gdx.files.internal("selecao_5x5_unselected.png"));
                txtr6x6 = new Texture(Gdx.files.internal("selecao_6x6_unselected.png"));
                txtr7x7 = new Texture(Gdx.files.internal("selecao_7x7.png"));
            }
        });

        botaoBomba.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(controle.getBotaoBombaSelected())
                {
                    controle.setBotaoBombaSelected(false);
                    txtrBomba = new Texture(Gdx.files.internal("blocos/bloco_bomba_unselected.png"));
                }
                else
                {
                    controle.setBotaoBombaSelected(true);
                    txtrBomba = new Texture(Gdx.files.internal("blocos/bloco_bomba.png"));
                }
            }
        });

        botaoDeleta.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(controle.getBotaoDeletaSelected())
                {
                    controle.setBotaoDeletaSelected(false);
                    txtrDeleta = new Texture(Gdx.files.internal("blocos/bloco_deleta_unselected.png"));
                }
                else
                {
                    controle.setBotaoDeletaSelected(true);
                    txtrDeleta = new Texture(Gdx.files.internal("blocos/bloco_deleta.png"));
                }
            }
        });

        botaoTempo.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(controle.getBotaoTempoSelected())
                {
                    controle.setBotaoTempoSelected(false);
                    txtrTempo = new Texture(Gdx.files.internal("blocos/bloco_tempo_unselected.png"));
                }
                else
                {
                    controle.setBotaoTempoSelected(true);
                    txtrTempo = new Texture(Gdx.files.internal("blocos/bloco_tempo.png"));
                }
            }
        });

        botao2x.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(controle.getBotao2xSelected())
                {
                    controle.setBotao2xSelected(false);
                    txtr2x = new Texture(Gdx.files.internal("blocos/bloco_2x_unselected.png"));
                }
                else
                {
                    controle.setBotao2xSelected(true);
                    txtr2x = new Texture(Gdx.files.internal("blocos/bloco_2x.png"));
                }
            }
        });

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

        botao.draw(jogo.batch, 1);
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
                    jogo.setScreen(new TelaInicial(jogo));
                }
            });
        } 
        else if(tela.equals("jogo"))
        {
            botao.addListener(new ClickListener()
            {
                public void clicked(InputEvent event, float x, float y) {
                    jogo.setScreen(new TelaJogo(jogo));
                }
            });
        }
        else if(tela.equals("instrucoes"))
        {
            botao.addListener(new ClickListener()
            {
                public void clicked(InputEvent event, float x, float y) {
                    jogo.setScreen(new TelaInstrucoes(jogo));
                }
            });
        }
    }


    @Override
	public void dispose() {
		// FAZER DISPOSE DAS TEXTURASSSSS AAAAAAAAAAAAAAAAAAAAAAA
	}
    
}