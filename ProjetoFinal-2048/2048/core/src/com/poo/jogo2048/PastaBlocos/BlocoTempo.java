package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BlocoTempo implements IBlocosTimer
{
    private static IBlocosTimer instance;
    private String id = "tempo";
    private int vida = 3;
    private int coordX;
    private int coordY;
    private boolean ativo = false;
    private boolean juntado = false;
    Texture imagem = new Texture(Gdx.files.internal("blocos/bloco_tempo.png"));;

    private BlocoTempo(){}
    
    public static IBlocosTimer getInstance()
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

    public boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean info)
    {
        ativo = info;
    }

    public IBlocos junta()
    {
        return instance;
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
