package com.poo.jogo2048.Telas;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.Controle;
import com.poo.jogo2048.Tabuleiro;
import com.poo.jogo2048.jogo2048;

public class TelaJogo extends TelaAbstrata
{
    final jogo2048 jogo;
    Tabuleiro tabuleiro;
    Controle controle;
    Stage stage;
    private char direcao;

    OrthographicCamera camera;

    public TelaJogo(final jogo2048 jogo)
    {
        // conexões
        this.jogo = jogo;
        this.controle = jogo.getControle();
        stage = jogo.getStage();
        stage.clear();

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 500, 500);

        // criação do tabuleiro
        tabuleiro = new Tabuleiro(jogo.getTamanhoTabuleiro());
        jogo.setTabuleiro(tabuleiro);
        controle.setTabuleiro(tabuleiro);

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
        controle.spawnBloco();
        controle.spawnBloco();
    }

    private void leComando()
    {
        if(Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A))
        {
            direcao = 'a';
            controle.realizaComando(direcao);
        }
            
        else if(Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D))
        {
            direcao = 'd';
            controle.realizaComando(direcao);
        }
            
        else if(Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W))
        {
            direcao = 'w';
            controle.realizaComando(direcao);
        }
            
        else if(Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S))
        {
            direcao = 's';
            controle.realizaComando(direcao);
        }
        // else if(Gdx.input.isKeyPressed())
        // {
        //     // System.out.println("Digite um movimento válido no teclado: W, A, S, D ou setas de direção")
        // }
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
        stage.draw();
        stage.act();

        // leitura da próxima jogada
        leComando();
	}
}