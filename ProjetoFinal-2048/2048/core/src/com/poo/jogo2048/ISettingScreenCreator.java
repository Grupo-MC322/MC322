package com.poo.jogo2048;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface ISettingScreenCreator
{
    public Controle getControle();
    public SpriteBatch getBatch();
    public Stage getStage();
    public void setTamanhoTabuleiro(int tamanhoTabuleiro);
    public void setScreen(Screen screen);
}