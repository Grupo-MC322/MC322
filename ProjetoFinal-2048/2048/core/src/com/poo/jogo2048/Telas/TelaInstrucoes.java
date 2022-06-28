package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.poo.jogo2048.Criador;

public class TelaInstrucoes extends TelaAbstrata
{
    final Criador jogo;
    OrthographicCamera camera;
    Stage stage;
    int pagina = 1;
    Texture txtrFundo;

    public TelaInstrucoes(final Criador jogo)
    {
        this.jogo = jogo;
        jogo.getStage().clear();
        this.stage = jogo.getStage();

        criaStage();
    }

    public void criaStage()
    {
        stage.clear();

        /* adicionando os atores */
        // fundo
        if(pagina == 1)
            txtrFundo = new Texture(Gdx.files.absolute("telas/fundo_instrucoes_1:4.png"));
        else if(pagina == 2)
            txtrFundo = new Texture(Gdx.files.absolute("telas/fundo_instrucoes_2:4.png"));
        else if(pagina == 3)
            txtrFundo = new Texture(Gdx.files.absolute("telas/fundo_instrucoes_3:4.png"));
        else if(pagina == 4)
            txtrFundo = new Texture(Gdx.files.absolute("telas/fundo_instrucoes_4:4.png"));

        Image imgFundo = new Image(txtrFundo);
        imgFundo.setPosition(0, 0);
        imgFundo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(imgFundo);

        // botão próxima página
        Texture txtrBotaoProx = new Texture(Gdx.files.absolute("botoes/botao_proximo.png"));
        Image botaoProx = new Image(txtrBotaoProx);
        botaoProx.setPosition((stage.getWidth() / 2) + (stage.getWidth() * 0.01f), stage.getHeight() * 0.1f);
        botaoProx.setSize(stage.getWidth() * 0.11f, stage.getHeight() * 0.11f);
        stage.addActor(botaoProx);

        // botão página anterior
        Texture txtrBotaoAnt = new Texture(Gdx.files.absolute("botoes/botao_anterior.png"));
        Image botaoAnt = new Image(txtrBotaoAnt);
        botaoAnt.setPosition((stage.getWidth() / 2) - (stage.getWidth() * 0.11f) - (stage.getWidth() * 0.01f), stage.getHeight() * 0.1f);
        botaoAnt.setSize(stage.getWidth() * 0.11f, stage.getHeight() * 0.11f);
        stage.addActor(botaoAnt);

        // botão voltar
        Texture txtrBotaoVoltar = new Texture(Gdx.files.absolute("botoes/botao_voltar.png"));
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
                jogo.setScreen(new TelaInicial(jogo));
            }
        });
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