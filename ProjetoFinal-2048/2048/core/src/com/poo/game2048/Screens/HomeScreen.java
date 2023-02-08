package com.poo.game2048.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.game2048.Creator;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class HomeScreen extends AbstractScreen
{
    private final Creator creator;
    private Stage stage;
    private SpriteBatch batch;

    private OrthographicCamera camera;

    private Texture txtrFundo;
    private Texture txtrBotaoJogar;
    private float xBotaoJogar;
    private float yBotaoJogar;
    private Texture txtrBotaoInstr;
    private float xBotaoInstr;
    private float yBotaoInstr;

    public HomeScreen(final Creator creator)
    {
        this.creator = creator;
        creator.getStage().clear();
        this.stage = creator.getStage();
        this.batch = creator.getBatch();

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 500, 500);
    
        txtrFundo = new Texture(Gdx.files.internal("telas/fundo_tela_inicio.png"));

        txtrBotaoJogar = new Texture(Gdx.files.internal("botoes/botao_jogar_1.png"));
        xBotaoJogar = (stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2);
        yBotaoJogar = stage.getHeight() * 0.44f;

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
        
        batch.draw(txtrFundo, 0, 0, 500, 500);
        batch.draw(txtrBotaoJogar, xBotaoJogar, yBotaoJogar, stage.getWidth() * 0.5f, stage.getHeight() * 0.1f);
        batch.draw(txtrBotaoInstr, xBotaoInstr, yBotaoInstr, stage.getWidth() * 0.5f, stage.getHeight() * 0.1f);

        // configurações do stage
        criaStage();

		batch.end();
	}

    public void criaStage()
    {
        // adicionando os atores
        // botão configurar
        Image botaoConfig = new Image(txtrBotaoJogar);
        botaoConfig.setPosition(xBotaoJogar, yBotaoJogar);
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
                creator.setScreen(new SettingScreen(creator));
            }
        });
        botaoInstr.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                creator.setScreen(new InstructionScreen(creator));
            }
        });
    }

    @Override
	public void dispose()
    {
		txtrFundo.dispose();
		txtrBotaoJogar.dispose();
		txtrBotaoInstr.dispose();
	}

}