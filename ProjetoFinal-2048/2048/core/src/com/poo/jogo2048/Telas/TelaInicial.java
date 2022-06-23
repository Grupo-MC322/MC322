package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.jogo2048;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class TelaInicial extends TelaAbstrata
{
    final jogo2048 jogo;

    OrthographicCamera camera;

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

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 400);
    
        txtrFundo = new Texture(Gdx.files.internal("fundo_tela_inicio.png"));

        txtrBotaoConfig = new Texture(Gdx.files.internal("botao_configurar.png"));
        xBotaoConfig = (float) ((jogo.stage.getWidth() / 2) - (jogo.stage.getWidth() * 0.5 / 2));
        yBotaoConfig = (float) (jogo.stage.getHeight() * 0.44);

        txtrBotaoInstr = new Texture(Gdx.files.internal("botao_instrucoes.png"));
        xBotaoInstr = (float) ((jogo.stage.getWidth() / 2) - (jogo.stage.getWidth() * 0.5 / 2));
        yBotaoInstr = (float) (jogo.stage.getHeight() * 0.27);

    }

    @Override
	public void render(float delta) {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1);

		camera.update();
		jogo.batch.setProjectionMatrix(camera.combined);

        // configurações do batch
		jogo.batch.begin();
        
        jogo.batch.draw(txtrFundo, 0, 0, 400, 400);
        jogo.batch.draw(txtrBotaoConfig, xBotaoConfig, yBotaoConfig, (float) (jogo.stage.getWidth() * 0.5), (float) (jogo.stage.getHeight() * 0.1));
        jogo.batch.draw(txtrBotaoInstr, xBotaoInstr, yBotaoInstr, (float) (jogo.stage.getWidth() * 0.5), (float) (jogo.stage.getHeight() * 0.1));

        // configurações do stage
        criaStage();

		jogo.batch.end();
	}

    public void criaStage()
    {
        // adicionando os atores
        // botão configurar
        Image botaoConfig = new Image(txtrBotaoConfig);
        botaoConfig.setPosition(xBotaoConfig, yBotaoConfig);
        botaoConfig.setSize((float) (jogo.stage.getWidth() * 0.5), (float) (jogo.stage.getHeight() * 0.1));
        jogo.stage.addActor(botaoConfig);

        // botão instruções
        Image botaoInstr = new Image(txtrBotaoInstr);
        botaoInstr.setPosition(xBotaoInstr, yBotaoInstr);
        botaoInstr.setSize((float) (jogo.stage.getWidth() * 0.5), (float) (jogo.stage.getHeight() * 0.1));
        jogo.stage.addActor(botaoInstr);

        // configurações de input dos botões
        botaoConfig.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                jogo.setScreen(new TelaConfiguracoes(jogo, jogo.controle));
            }
        });
        botaoInstr.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                jogo.setScreen(new TelaInstrucoes(jogo));
            }
        });
    }

    @Override
	public void dispose() {
		txtrFundo.dispose();
		txtrBotaoConfig.dispose();
		txtrBotaoInstr.dispose();
	}

}