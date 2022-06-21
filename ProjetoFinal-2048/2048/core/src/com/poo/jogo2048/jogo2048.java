package com.poo.jogo2048;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.poo.jogo2048.PastaBlocos.*;
import com.poo.jogo2048.Telas.TelaInicial;


public class jogo2048 extends Game
{
	public SpriteBatch batch;
	public Stage stage;
	public Tabuleiro tabuleiro;
	public Controle controle;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		stage = new Stage();
		controle = new Controle();

		Gdx.input.setInputProcessor(stage);
		
		this.setScreen(new TelaInicial(this));

		// preenchimento do tabuleiro inicial
		tabuleiro = new Tabuleiro(4, 4);
		spawnBloco(tabuleiro, controle);
	}

	// função
	public static void spawnBloco(Tabuleiro tabuleiro, Controle controle)
    {
        IBlocos blocoGerado = new BlocoGenerico(0);
        Random random = new Random();
        int coordX = random.nextInt(tabuleiro.getTamanhoX());
		int coordY = random.nextInt(tabuleiro.getTamanhoY());

		if (tabuleiro.getId(coordX, coordY) == (Object) 0)
		{
			int index = random.nextInt(100);
            if(index < 20)
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
                controle.setAtivo(new BlocoBomba());
            }
            else if (index < 90 && controle.getBlocoTempoAtivo() == false)
            {
                controle.setAtivo(new BlocoTempo());
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

	public void jogada(char direcao)
	{
		for(int i = 0; i < tabuleiro.getTamanhoX(); i++)
        {
            for(int j = 0; j < tabuleiro.getTamanhoY(); j++)
            {
				//controle.junta(direcao, i, j, tabuleiro);
            }
        }
	}

	
	@Override
	public void dispose ()
	{
		batch.dispose();
	}
}
