package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BlocoGenerico implements IBlocos
{
	private int id;
    private Texture textura;
    private Image imagem;
    private boolean juntado = false;
    private float posX;
    private float posY;
    private float size;

    public BlocoGenerico(int id)
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
                textura = new Texture(Gdx.files.internal("blocos/vazio.png"));
                setImagem(new Image(textura));
                break;
            case 1:
                textura = new Texture(Gdx.files.internal("blocos/1.png"));
                setImagem(new Image(textura));
                break;
            case 2:
                textura = new Texture(Gdx.files.internal("blocos/2.png"));
                setImagem(new Image(textura));
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

    public void junta()
    {
        setId(id * 2);
    }

    public void setJuntado(boolean info)
    {
        juntado = info;
    }
    
    public boolean getJuntado()
    {
        return juntado;
    }

    public Texture getTextura() {
        return textura;
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

    public float getPosY() {
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
