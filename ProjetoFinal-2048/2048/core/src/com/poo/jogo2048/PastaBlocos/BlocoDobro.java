package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BlocoDobro extends Actor implements IBlocos {
    private String id = "2x";
    private boolean juntado = false;
    private Image imagem = new Image(new Texture(Gdx.files.internal("blocos/bloco_2x.png")));
    private float posX;
    private float posY;
    private float size;

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

    public Image getImagem()
    {
        return imagem;
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

    public void moveToPosition(float x, float y)
    {
		addAction(Actions.moveTo(x, y, .075f));
	}
}