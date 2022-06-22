package com.poo.jogo2048.Telas;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.jogo2048.Controle;
import com.poo.jogo2048.Tabuleiro;
import com.poo.jogo2048.jogo2048;
import com.poo.jogo2048.PastaBlocos.*;

public class TelaJogo extends TelaAbstrata
{
    final jogo2048 jogo;
    Tabuleiro tabuleiro;
    Controle controle;

    OrthographicCamera camera;

    public TelaJogo(final jogo2048 jogo)
    {
        this.jogo = jogo;
        this.controle = jogo.controle;

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 400);

        tabuleiro = new Tabuleiro(jogo.getTamanhoTabuleiro());
        spawnBloco(tabuleiro, controle);
    }

    @Override
	public void render(float delta) {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1); // definição da cor de fundo

        // configurações de camera
		camera.update();
		jogo.batch.setProjectionMatrix(camera.combined);

        // configurações do batch
		jogo.batch.begin();
        
        // desenho inicial do tabuleiro
        for(int i = 0; i < tabuleiro.getTamanho(); i++)
        {
			{
				for(int j = 0; j < tabuleiro.getTamanho(); j++)
				{
					Float posX = (float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / tabuleiro.getTamanho()) * i + (camera.viewportWidth * 0.01) * i);
					Float posY = (float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()) * j + (camera.viewportHeight * 0.01) * j);
					jogo.batch.draw(tabuleiro.getBloco(i, j).getImagem(), posX, posY, (float) (camera.viewportWidth * 0.87 / tabuleiro.getTamanho()), (float) (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()));
				}
			}
        }

		jogo.batch.end();

        // configuração dos inputs das teclas de comando
        if(Gdx.input.isKeyJustPressed(Keys.LEFT))
        {
            jogada('a');
            spawnBloco(tabuleiro, controle);
            
        }
        else if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
        {
            jogada('d');
            spawnBloco(tabuleiro, controle);
        }
        else if(Gdx.input.isKeyJustPressed(Keys.UP))
        {
            jogada('w');
            spawnBloco(tabuleiro, controle);
        }
        else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
        {
            jogada('s');
            spawnBloco(tabuleiro, controle);
        }
	}

    public void jogada(char direcao)
	{
		for(int i = 0; i < tabuleiro.getTamanho(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanho(); j++)
            {
				controle.realizaComando(direcao, i, j, tabuleiro);
            }
        }
	}

    public static void spawnBloco(Tabuleiro tabuleiro, Controle controle)
    {
        IBlocos blocoGerado = new BlocoGenerico(0);
        Random random = new Random();
        int coordX = random.nextInt(tabuleiro.getTamanho());
		int coordY = random.nextInt(tabuleiro.getTamanho());

		if (tabuleiro.getId(coordX, coordY) == (Object) 0)
		{
			int index = random.nextInt(100);
            if(index < 10)
            {
                blocoGerado = new BlocoGenerico(1);
            }
            else if (index < 60)
            {
                blocoGerado = new BlocoGenerico(2);
            }
            else if (index < 80)
            {
                blocoGerado = new BlocoGenerico(4);
            }
            else if (index < 85 && controle.getBlocoBombaAtiva() == false)
            {
                blocoGerado = controle.setAtivo(new BlocoBomba());
            }
            else if (index < 90 && controle.getBlocoTempoAtivo() == false)
            {
                blocoGerado = controle.setAtivo(new BlocoTempo());
            }
            else if (index < 95)
            {
                blocoGerado = new BlocoDeleta();
            }
            else if (index < 100)
            {
                blocoGerado = new BlocoDobro();
            }
            
            tabuleiro.setBloco(coordX, coordY, blocoGerado);
		}
		else
		{
			spawnBloco(tabuleiro, controle);
		}
    }

    // private void drawTabuleiro(Batch batch, Tabuleiro tabuleiro)
	// {
	// 	batch.begin();
	// 	for(int i = 0; i < tabuleiro.getTamanho(); i++)
    //     {
    //         for(int j = 0; j < tabuleiro.getTamanho(); j++)
    //         {
	// 			Float posX = (float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / tabuleiro.getTamanho()) * i + (camera.viewportWidth * 0.01) * i);
	// 			Float posY = (float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()) * j + (camera.viewportHeight * 0.01) * j);
	// 			batch.draw(tabuleiro.getBloco(i, j).getImagem(), posX, posY, (float) (camera.viewportWidth * 0.87 / tabuleiro.getTamanho()), (float) (camera.viewportHeight * 0.87 / tabuleiro.getTamanho()));
    //         }
    //     }
	// 	batch.end();
	// }

    @Override
	public void dispose() {
		// FAZER
	}
}
