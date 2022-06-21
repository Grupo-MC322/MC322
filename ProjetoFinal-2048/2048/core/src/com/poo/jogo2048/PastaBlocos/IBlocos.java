package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.graphics.Texture;

public interface IBlocos {
    public Object getId();
    public IBlocos junta();
    public void setJuntado(boolean info);
    public boolean getJuntado();

    public Texture getImagem();
    public void disposeImagem();

}
