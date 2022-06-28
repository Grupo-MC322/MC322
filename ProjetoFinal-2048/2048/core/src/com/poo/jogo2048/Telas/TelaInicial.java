package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.jogo2048;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class TelaInicial extends TelaAbstrata
{
    private final jogo2048 jogo;
    private Stage stage;
    private SpriteBatch batch;

    private OrthographicCamera camera;

    private Texture txtrFundo;
    private Texture txtrBotaoConfig;
    private float xBotaoConfig;
    private float yBotaoConfig;
    private Texture txtrBotaoInstr;
    private float xBotaoInstr;
    private float yBotaoInstr;

    public TelaInicial(final jogo2048 jogo)
    {
        this.jogo = jogo;
        jogo.getStage().clear();
        this.stage = jogo.getStage();
        this.batch = jogo.getBatch();

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 600);
    
        txtrFundo = new Texture(Gdx.files.internal("telas/fundo_tela_inicio.png"));

        txtrBotaoConfig = new Texture(Gdx.files.internal("botoes/botao_configurar.png"));
        xBotaoConfig = (stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2);
        yBotaoConfig = stage.getHeight() * 0.44f;

        txtrBotaoInstr = new Texture(Gdx.files.internal("botoes/botao_instrucoes.png"));
        xBotaoInstr = (stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2);
        yBotaoInstr = stage.getHeight() * 0.27f;

    }

    @Override
	public void render(float delta)
    {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

        // configurações do batch
		batch.begin();
        
        batch.draw(txtrFundo, 0, 0, 600, 600);
        batch.draw(txtrBotaoConfig, xBotaoConfig, yBotaoConfig, stage.getWidth() * 0.5f, stage.getHeight() * 0.1f);
        batch.draw(txtrBotaoInstr, xBotaoInstr, yBotaoInstr, stage.getWidth() * 0.5f, stage.getHeight() * 0.1f);

        // configurações do stage
        criaStage();

		batch.end();
	}

    public void criaStage()
    {
        // adicionando os atores
        // botão configurar
        Image botaoConfig = new Image(txtrBotaoConfig);
        botaoConfig.setPosition(xBotaoConfig, yBotaoConfig);
        botaoConfig.setSize(stage.getWidth() * 0.5f, stage.getHeight() * 0.1f);
        stage.addActor(botaoConfig);

        // botão instruções
        Image botaoInstr = new Image(txtrBotaoInstr);
        botaoInstr.setPosition(xBotaoInstr, yBotaoInstr);
        botaoInstr.setSize(stage.getWidth() * 0.5f, stage.getHeight() * 0.1f);
        stage.addActor(botaoInstr);

        // configurações de input dos botões
        botaoConfig.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                jogo.setScreen(new TelaConfiguracoes(jogo));
            }
        });
        botaoInstr.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                jogo.setScreen(new TelaInstrucoes(jogo));
            }
        });
    }

    @Override
	public void dispose()
    {
		txtrFundo.dispose();
		txtrBotaoConfig.dispose();
		txtrBotaoInstr.dispose();
	}

}