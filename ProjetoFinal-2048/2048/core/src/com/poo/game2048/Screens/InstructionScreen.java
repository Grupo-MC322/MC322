package com.poo.game2048.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.poo.game2048.Creator;

public class InstructionScreen extends AbstractScreen
{
    private final Creator creator;
    private Stage stage;
    private Texture txtrFundo;
    private int pagina = 1;
    
    public InstructionScreen(final Creator creator)
    {
        this.creator = creator;
        creator.getStage().clear();
        this.stage = creator.getStage();

        criaStage();
    }

    public void criaStage()
    {
        stage.clear();

        /* adicionando os atores */
        // fundo
        if(pagina == 1)
            txtrFundo = new Texture(Gdx.files.internal("backgrounds/instructions_1:4.png"));
        else if(pagina == 2)
            txtrFundo = new Texture(Gdx.files.internal("backgrounds/instructions_2:4.png"));
        else if(pagina == 3)
            txtrFundo = new Texture(Gdx.files.internal("backgrounds/instructions_3:4.png"));
        else if(pagina == 4)
            txtrFundo = new Texture(Gdx.files.internal("backgrounds/instructions_4:4.png"));

        Image imgFundo = new Image(txtrFundo);
        imgFundo.setPosition(0, 0);
        imgFundo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(imgFundo);

        // botão próxima página
        Texture txtrBotaoProx = new Texture(Gdx.files.internal("buttons/next.png"));
        Image botaoProx = new Image(txtrBotaoProx);
        botaoProx.setPosition((stage.getWidth() / 2) + (stage.getWidth() * 0.01f), stage.getHeight() * 0.1f);
        botaoProx.setSize(stage.getWidth() * 0.11f, stage.getHeight() * 0.11f);
        stage.addActor(botaoProx);

        // botão página anterior
        Texture txtrBotaoAnt = new Texture(Gdx.files.internal("buttons/previous.png"));
        Image botaoAnt = new Image(txtrBotaoAnt);
        botaoAnt.setPosition((stage.getWidth() / 2) - (stage.getWidth() * 0.11f) - (stage.getWidth() * 0.01f), stage.getHeight() * 0.1f);
        botaoAnt.setSize(stage.getWidth() * 0.11f, stage.getHeight() * 0.11f);
        stage.addActor(botaoAnt);

        // botão voltar
        Texture txtrBotaoVoltar = new Texture(Gdx.files.internal("buttons/back.png"));
        Image botaoVoltar = new Image(txtrBotaoVoltar);
        botaoVoltar.setPosition((stage.getWidth() * 0.05f), stage.getHeight() * 0.85f);
        botaoVoltar.setSize(stage.getWidth() * 0.1f, stage.getHeight() * 0.1f);
        stage.addActor(botaoVoltar);
        
        /* configurações de input dos botões */
        botaoProx.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(pagina < 4)
                {
                    pagina++;
                    criaStage();
                }
            }
        });

        botaoAnt.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                if(pagina > 1)
                {
                    pagina--;
                    criaStage();
                }
            }
        });

        botaoVoltar.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                creator.setScreen(new HomeScreen(creator));
            }
        });
    }
    
    @Override
    public void render(float delta)
    {
        // input das teclas para mudar as páginas
        if(Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D))
        {
            if(pagina < 4)
            {
                pagina++;
                criaStage();
            }
        }
        else if(Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A))
        {
            if(pagina > 1)
            {
                pagina--;
                criaStage();
            }
        }

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}