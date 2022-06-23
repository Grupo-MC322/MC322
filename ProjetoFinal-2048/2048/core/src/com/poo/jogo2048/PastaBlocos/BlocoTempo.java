package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BlocoTempo implements IBlocosTimer {
    private IBlocosTimer instance;
    private String id = "tempo";
    private int vida = 3;
    private int coordX;
    private int coordY;
    private boolean juntado = false;
    Texture imagem = new Texture(Gdx.files.internal("blocos/bloco_tempo.png"));;

    public IBlocosTimer getInstance()
    {
        if (instance == null)
        {
            instance = new BlocoTempo();
        }
        return instance;
    }
    
    public Object getId()
    {
        return id;
    }

    public int getVida()
    {
        return vida;
    }

    public void setVida(int mudanca)
    {
        vida += mudanca;
    }

    public int getCoordX()
    {
        return coordX;
    }

    public void setCoordX(int x)
    {
        coordX = x;
    }

    public int getCoordY()
    {
        return coordY;
    }

    public void setCoordY(int y)
    {
        coordY = y;
    }

    public IBlocos junta()
    {
        return new BlocoTempo();
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

    public void setImagem(Texture imagem)
    {
        this.imagem = imagem;
    }

    public void disposeImagem()
    {
        imagem.dispose();
    }
}
