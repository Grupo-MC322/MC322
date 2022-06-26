package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BlocoBomba implements IBlocosTimer
{
    private static IBlocosTimer instance;
    private String id = "bomba";
    private int vida = 3;
    private int coordX;
    private int coordY;
    private boolean ativo = false;
    private boolean juntado = false;
    private float posX;
    private float posY;
    private float size;

    private Image imagem = new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba_1:3.png")));
    
    private BlocoBomba()
    {}
    
    public static IBlocosTimer getInstance()
    {
        if (instance == null)
        {
            instance = new BlocoBomba();
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

    public boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean info)
    {
        ativo = info;
    }

    public void junta() {}

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

    public void setJuntado(boolean info)
    {
        juntado = info;
    }
    
    public boolean getJuntado()
    {
        return juntado;
    }

    public Image getImagem()
    {
        return imagem;
    }

    public void setImagem(Image imagem)
    {
        this.imagem = imagem;
    }

    public void setPosX(float posX)
    {
        this.posX = posX;
        this.getImagem().setX(posX);
    }

    public float getPosX()
    {
        return posX;
    }

    public void setPosY(float posY)
    {
        this.posY = posY;
        this.getImagem().setY(posY);
    }

    public float getPosY()
    {
        return posY;
    }

    public void setSize(float size)
    {
        this.size = size;
        this.getImagem().setWidth(size);
        this.getImagem().setHeight(size);
    }

    public float getSize()
    {
        return size;
    }
}
