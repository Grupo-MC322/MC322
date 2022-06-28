package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public interface IBlocosTimer extends IBlocos
{
    public int getVida();
    public void setVida(int mudanca);
    public boolean getAtivo();
    public void setAtivo(boolean info);
    public int getLinha();
    public void setLinha(int x);
    public int getColuna();
    public void setColuna(int y);
    public void setImagem(Image imagem);
    public void reset();
}
