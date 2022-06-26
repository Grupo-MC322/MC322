package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public interface IBlocosTimer extends IBlocos
{
    public int getVida();
    public void setVida(int mudanca);
    public boolean getAtivo();
    public void setAtivo(boolean info);
    public int getCoordX();
    public void setCoordX(int x);
    public int getCoordY();
    public void setCoordY(int y);
    public void setImagem(Image imagem);
}
