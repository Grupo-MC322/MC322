package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BlocoDeleta implements IBlocos {
    private String id = "deleta";
    private boolean juntado = false;
    private Texture imagem = new Texture(Gdx.files.internal("blocos/bloco_deleta.png"));

    public Object getId()
    {
        return id;
    }

    public IBlocos junta()
    {
        return new BlocoDeleta();
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
