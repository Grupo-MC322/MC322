package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.poo.jogo2048.jogo2048;

public class TelaPerdeu extends TelaAbstrata
{
    final jogo2048 jogo;

    OrthographicCamera camera;
    Stage stage;

    public TelaPerdeu(final jogo2048 jogo)
    {
        this.jogo = jogo;
        jogo.getStage().clear();
        this.stage = jogo.getStage();

        criaStage();
    }

    public void criaStage()
    {
        /* adicionando os atores */
        // fundo
        Texture txtrFundo = new Texture(Gdx.files.absolute("fundo_tela_perdeu.png"));
        Image imgFundo = new Image(txtrFundo);
        imgFundo.setPosition(0, 0);
        imgFundo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(imgFundo);

        // botão menu principal
        Texture txtrBotaoMenu = new Texture(Gdx.files.absolute("botao_menu_principal.png"));
        Image botaoMenu = new Image(txtrBotaoMenu);
        botaoMenu.setPosition((stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2), stage.getHeight() * 0.35f);
        botaoMenu.setSize((float) (stage.getWidth() * 0.5), (float) (stage.getHeight() * 0.1));
        stage.addActor(botaoMenu);

        // botão encerrar
        Texture txtrBotaoEncerrar = new Texture(Gdx.files.absolute("botao_encerrar.png"));
        Image botaoEncerrar = new Image(txtrBotaoEncerrar);
        botaoEncerrar.setPosition((stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2), stage.getHeight() * 0.2f);
        botaoEncerrar.setSize((float) (stage.getWidth() * 0.5), (float) (stage.getHeight() * 0.1));
        stage.addActor(botaoEncerrar);

        
        /* configurações de input dos botões */
        botaoMenu.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                jogo.setScreen(new TelaInicial(jogo));
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
