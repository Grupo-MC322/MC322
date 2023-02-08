package com.poo.game2048.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.poo.game2048.Creator;

public class LooseScreen extends AbstractScreen
{
    private final Creator creator;
    private Stage stage;

    public LooseScreen(final Creator creator)
    {
        // conexões
        this.creator = creator;
        this.stage = creator.getStage();

        // setup do stage
        stage.clear();
        criaStage();
    }

    public void criaStage()
    {
        /* adicionando os atores */
        // fundo
        Texture txtrFundo = new Texture(Gdx.files.internal("backgrounds/loose.png"));
        Image imgFundo = new Image(txtrFundo);
        imgFundo.setPosition(0, 0);
        imgFundo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(imgFundo);

        // botão menu principal
        Texture txtrBotaoMenu = new Texture(Gdx.files.internal("buttons/menu.png"));
        Image botaoMenu = new Image(txtrBotaoMenu);
        botaoMenu.setPosition((stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2), stage.getHeight() * 0.35f);
        botaoMenu.setSize((float) (stage.getWidth() * 0.5), (float) (stage.getHeight() * 0.1));
        stage.addActor(botaoMenu);

        // botão encerrar
        Texture txtrBotaoEncerrar = new Texture(Gdx.files.internal("buttons/close.png"));
        Image botaoEncerrar = new Image(txtrBotaoEncerrar);
        botaoEncerrar.setPosition((stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2), stage.getHeight() * 0.2f);
        botaoEncerrar.setSize((float) (stage.getWidth() * 0.5), (float) (stage.getHeight() * 0.1));
        stage.addActor(botaoEncerrar);

        
        /* configurações de input dos botões */
        botaoMenu.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                creator.setScreen(new HomeScreen(creator));
            }
        });

        botaoEncerrar.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                System.exit(0);
            }
        });
    }
    
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
