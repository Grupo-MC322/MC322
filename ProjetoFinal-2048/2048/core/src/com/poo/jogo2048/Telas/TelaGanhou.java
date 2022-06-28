package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.poo.jogo2048.Criador;

public class TelaGanhou extends TelaAbstrata
{
    final Criador jogo;

    OrthographicCamera camera;
    Stage stage;

    public TelaGanhou(final Criador jogo)
    {
        // conexões
        this.jogo = jogo;
        this.stage = jogo.getStage();

        // setup do stage
        stage.clear();
        criaStage();
    }

    public void criaStage()
    {
        /* adicionando os atores */
        // fundo
        Texture txtrFundo = new Texture(Gdx.files.absolute("telas/fundo_tela_ganhou.png"));
        Image imgFundo = new Image(txtrFundo);
        imgFundo.setPosition(0, 0);
        imgFundo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(imgFundo);

        // confetti caindo
        Texture txtrConfetti = new Texture(Gdx.files.absolute("extras/confetti.png"));
        Image imgConfetti = new Image(txtrConfetti);
        imgConfetti.setPosition(Gdx.graphics.getWidth() * 0.055f, Gdx.graphics.getHeight());
        imgConfetti.setSize(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.95f);
        imgConfetti.setOrigin(Gdx.graphics.getWidth() * 0.055f, Gdx.graphics.getHeight());
        stage.addActor(imgConfetti);

        MoveToAction moveParaBaixo = new MoveToAction();
        moveParaBaixo.setPosition(Gdx.graphics.getWidth() * 0.055f, -Gdx.graphics.getHeight());
        moveParaBaixo.setDuration(7.5f);

        imgConfetti.addAction(moveParaBaixo);

        // botão menu principal
        Texture txtrBotaoMenu = new Texture(Gdx.files.absolute("botoes/botao_menu_principal.png"));
        Image botaoMenu = new Image(txtrBotaoMenu);
        botaoMenu.setPosition((stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2), stage.getHeight() * 0.35f);
        botaoMenu.setSize((float) (stage.getWidth() * 0.5), (float) (stage.getHeight() * 0.1));
        stage.addActor(botaoMenu);

        // botão encerrar
        Texture txtrBotaoEncerrar = new Texture(Gdx.files.absolute("botoes/botao_encerrar.png"));
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
