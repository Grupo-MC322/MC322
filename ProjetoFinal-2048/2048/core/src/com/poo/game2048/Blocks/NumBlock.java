package com.poo.game2048.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class NumBlock implements IBlocks
{
	private int id;
    private Image imagem;
    private boolean juntado = false;
    private float posX;
    private float posY;
    private float size;

    public NumBlock(int id)
    {
        setId(id);
    }

    public Object getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
        switch(id)
        {
            case 0:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/vazio.png"))));
                break;
            case 1:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/1.png"))));
                break;
            case 2:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/2.png"))));
                break;
            case 4:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/4.png"))));
                break;
            case 8:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/8.png"))));
                break;
            case 16:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/16.png"))));
                break;
            case 32:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/32.png"))));
                break;
            case 64:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/64.png"))));
                break;
            case 128:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/128.png"))));
                break;
            case 256:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/256.png"))));
                break;
            case 512:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/512.png"))));
                break;
            case 1024:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/1024.png"))));
                break;
            case 2048:
                setImagem(new Image(new Texture(Gdx.files.internal("blocos/2048.png"))));
                break;
        }
    }

    public void dobra()
    {
        setId(id * 2);
    }

    public boolean getJuntado()
    {
        return juntado;
    }

    public void setJuntado(boolean info)
    {
        juntado = info;
    }

    public Image getImagem()
    {
        return imagem;
    }

    public void setImagem(Image imagem)
    {
        this.imagem = imagem;
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

    public float getPosY() {
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
