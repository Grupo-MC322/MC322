package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BlocoDobro implements IBlocos {
    private String id = "2x";
    private boolean juntado = false;
    private Texture imagem = new Texture(Gdx.files.internal("blocos/bloco_2x.png"));

    public Object getId()
    {
        return id;
    }

    public IBlocos junta()
    {
        return new BlocoDobro();
    }

    public void setJuntado(boolean info)
    {
        juntado = info;
    }
    
    public boolean getJuntado()
    {
        return juntado;
    }

    public Texture getImagem()
    {
        return imagem;
    }

    public void disposeImagem()
    {
        imagem.dispose();
    }
}