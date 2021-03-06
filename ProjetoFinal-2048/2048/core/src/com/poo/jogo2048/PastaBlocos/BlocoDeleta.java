package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BlocoDeleta implements IBlocos
{
    private String id = "deleta";
    private Texture textura = new Texture(Gdx.files.internal("blocos/bloco_deleta.png"));
    private Image imagem = new Image(textura);
    private boolean juntado = false;
    private float posX;
    private float posY;
    private float size;

    public Object getId()
    {
        return id;
    }

    public Texture getTextura() {
        return textura;
    }

    public Image getImagem()
    {
        return imagem;
    }

    public boolean getJuntado()
    {
        return juntado;
    }

    public void setJuntado(boolean info)
    {
        juntado = info;
    }

    public float getPosX()
    {
        return posX;
    }
    
    public void setPosX(float posX)
    {
        this.posX = posX;
        this.getImagem().setX(posX);
    }

    public float getPosY()
    {
        return posY;
    }

    public void setPosY(float posY)
    {
        this.posY = posY;
        this.getImagem().setY(posY);
    }

    public float getSize()
    {
        return size;
    }

    public void setSize(float size)
    {
        this.size = size;
        this.getImagem().setWidth(size);
        this.getImagem().setHeight(size);
    }
}
