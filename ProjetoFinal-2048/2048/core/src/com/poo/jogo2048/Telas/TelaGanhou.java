package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.poo.jogo2048.jogo2048;

public class TelaGanhou extends TelaAbstrata
{
    final jogo2048 jogo;

    OrthographicCamera camera;
    Stage stage;

    public TelaGanhou(final jogo2048 jogo)
    {
        this.jogo = jogo;
        this.stage = jogo.stage;
        
        Texture txtrFundo = new Texture(Gdx.files.absolute("fundo_ganhou.png"));
        Image imgFundo = new Image(txtrFundo);
        imgFundo.setPosition(0, 0);
        imgFundo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(imgFundo);

        Texture txtrConfetti = new Texture(Gdx.files.absolute("confetti.png"));
        Image imgConfetti = new Image(txtrConfetti);
        imgConfetti.setPosition(Gdx.graphics.getWidth() * 0.055f, Gdx.graphics.getHeight());
        imgConfetti.setSize(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getHeight() * 0.95f);
        imgConfetti.setOrigin(Gdx.graphics.getWidth() * 0.055f, Gdx.graphics.getHeight());
        stage.addActor(imgConfetti);

        MoveToAction moveParaBaixo = new MoveToAction();
        moveParaBaixo.setPosition(Gdx.graphics.getWidth() * 0.055f, -Gdx.graphics.getHeight());
        moveParaBaixo.setDuration(5.5f);
        moveParaBaixo.setInterpolation(Interpolation.smooth);

        imgConfetti.addAction(moveParaBaixo);
    
        
    }

    public void criaStage()
    {
        // adicionando os atores
        // botão menu principal
        Texture txtrBotaoMenu = new Texture(Gdx.files.absolute("botao_menu_principal.png"));
        float xBotaoMenu = (stage.getWidth() / 2) - (stage.getWidth() * 0.5f / 2);
        float yBotaoMenu = stage.getHeight() * 0.44f;
        Image botaoMenu = new Image(txtrBotaoMenu);

        botaoMenu.setPosition(xBotaoMenu, yBotaoMenu);
        botaoMenu.setSize((float) (stage.getWidth() * 0.5), (float) (stage.getHeight() * 0.1));
        stage.addActor(botaoMenu);

        // botão instruções
        // Image botaoInstr = new Image(txtrBotaoInstr);
        // botaoInstr.setPosition(xBotaoInstr, yBotaoInstr);
        // botaoInstr.setSize((float) (jogo.stage.getWidth() * 0.5), (float) (jogo.stage.getHeight() * 0.1));
        // jogo.stage.addActor(botaoInstr);

        // configurações de input dos botões
        botaoMenu.addListener(new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                jogo.setScreen(new TelaInicial(jogo));
            }
        });
        // botaoInstr.addListener(new ClickListener()
        // {
        //     public void clicked(InputEvent event, float x, float y)
        //     {
        //         jogo.setScreen(new TelaGanhou(jogo));
        //     }
        // });
    }
    
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
