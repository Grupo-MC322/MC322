package com.poo.jogo2048.Telas;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.Tabuleiro;
import com.poo.jogo2048.Criador;
import com.poo.jogo2048.IGameScreenControl;

public class TelaJogo extends TelaAbstrata
{
    private final Criador jogo;
    private Stage stage;
    private OrthographicCamera camera;
    private Tabuleiro tabuleiro;
    private IGameScreenControl control;
    private char direcao;

    public TelaJogo(final Criador jogo)
    {
        // conexões
        this.jogo = jogo;
        control = jogo.getControle();
        stage = jogo.getStage();
        stage.clear();

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 500, 500);

        // criação do tabuleiro
        tabuleiro = new Tabuleiro(jogo.getTamanhoTabuleiro());
        control.conectaTabuleiro(tabuleiro);

        // desenho inicial do tabuleiro
        for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
        {
            for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
            {
                tabuleiro.getBloco(linha, coluna).setPosX((float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / tabuleiro.getTamanho()) * linha + (camera.viewportWidth * 0.01) * linha));
                tabuleiro.getBloco(linha, coluna).setPosY((float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()) * coluna + (camera.viewportHeight * 0.01) * coluna));
                tabuleiro.getBloco(linha, coluna).setSize((float) (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()));
                stage.addActor(tabuleiro.getBloco(linha, coluna).getImagem());
            }
        }
        stage.draw();
		
        // adicionando os dois primeiros blocos
        control.spawnBloco();
        control.spawnBloco();
    }

    private void leComando()
    {
        if(!control.getGanhou()){
            if(Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A))
            {
                direcao = 'a';
                control.transfereComando(direcao);
            }
                
            else if(Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D))
            {
                direcao = 'd';
                control.transfereComando(direcao);
            }
                
            else if(Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W))
            {
                direcao = 'w';
                control.transfereComando(direcao);
            }
                
            else if(Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S))
            {
                direcao = 's';
                control.transfereComando(direcao);
            }
        }
        else if(Gdx.input.isKeyJustPressed(Keys.ENTER) && control.getGanhou())
        {
            stage.clear();
            control.setGanhou(false);
            jogo.setScreen(new TelaGanhou(jogo));
        }
    }

    @Override
	public void render(float delta)
    {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1); // definição da cor de fundo

        camera.update();
        
        // desenho do tabuleiro
        for(int linha = 0; linha < tabuleiro.getTamanho(); linha++)
        {
            for(int coluna = 0; coluna < tabuleiro.getTamanho(); coluna++)
            {
                tabuleiro.getBloco(linha, coluna).setPosX((float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / tabuleiro.getTamanho()) * linha + (camera.viewportWidth * 0.01) * linha));
                tabuleiro.getBloco(linha, coluna).setPosY((float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()) * coluna + (camera.viewportHeight * 0.01) * coluna));
                tabuleiro.getBloco(linha, coluna).setSize((float) (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()));
                
                if(!Objects.equals(tabuleiro.getId(linha, coluna), 0))
                    stage.addActor(tabuleiro.getBloco(linha, coluna).getImagem());
            }
        }

        if(control.getGanhou())
        {
            Image aviso = new Image(new Texture(Gdx.files.internal("extras/aviso.png")));
            aviso.setPosition(camera.viewportWidth/2 - camera.viewportWidth * 0.45f, camera.viewportHeight/2 - camera.viewportHeight * 0.13f);
            aviso.setSize(camera.viewportWidth * 0.9f, camera.viewportHeight * 0.26f);
            stage.addActor(aviso);
        }

        stage.draw();
        stage.act();

        // leitura da próxima jogada
        leComando();
	}
}