package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public interface IBlocos
{
    public Object getId(); // cada bloco tem o seu ID, algo que o identifique, podendo ser uma String, int, ...
    public void setJuntado(boolean info);
    public boolean getJuntado();

    public Image getImagem();
    public void setPosX(float posX);
    public float getPosX();
    public void setPosY(float posY);
    public float getPosY();
    public void setSize(float size);
    public float getSize();
}
