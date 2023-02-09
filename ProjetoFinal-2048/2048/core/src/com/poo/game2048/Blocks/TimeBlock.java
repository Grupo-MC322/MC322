package com.poo.game2048.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TimeBlock implements ILifeBlocks
{
    private static ILifeBlocks instance;
    private String id = "time";
    private Texture txtr = new Texture(Gdx.files.internal("blocks/time.png"));
    private Image img = new Image(txtr);
    private boolean combined = false;
    private float posX;
    private float posY;
    private float size;
    private int life = 4;
    private int vertical;
    private int horizontal;
    private boolean activated = false;
    
    

    // para implementar o design pattern singleton, é necessário um construtor privado
    private TimeBlock()
    {}
    
    // implementação do design pattern singleton, garantindo que só uma instância de bloco tempo exista
    public static ILifeBlocks getInstance()
    {
        if (instance == null)
        {
            instance = new TimeBlock();
        }

        return instance;
    }
    
    public Object getId()
    {
        return id;
    }

    public Texture getTexture() {
        return txtr;
    }

    public void setTexture(Texture txtr) {
        this.txtr = txtr;
    }

    public boolean getCombined()
    {
        return combined;
    }

    public void setCombined(boolean info)
    {
        combined = info;
    }

    public Image getImage()
    {
        return img;
    }

    public void setImage(Image img)
    {
        this.img = img;
    }

    public float getPosX()
    {
        return posX;
    }

    public void setPosX(float posX)
    {
        this.posX = posX;
        this.getImage().setX(posX);
    }

    public float getPosY()
    {
        return posY;
    }

    public void setPosY(float posY)
    {
        this.posY = posY;
        this.getImage().setY(posY);
    }

    public float getSize()
    {
        return size;
    }

    public void setSize(float size)
    {
        this.size = size;
        this.getImage().setWidth(size);
        this.getImage().setHeight(size);
    }

    // o bloco tempo tem 4 lifes até desaparecer
    public int getLife()
    {
        return life;
    }

    public void setLife(int addition)
    {
        life += addition;
    }

    public int getVertical()
    {
        return vertical;
    }

    public void setVertical(int vertical)
    {
        this.vertical = vertical;
    }

    public int getHorizontal()
    {
        return horizontal;
    }

    public void setHorizontal(int horizontal)
    {
        this.horizontal = horizontal;
    }

    // o bloco tempo pode estar activated, ou seja, participando do tabuleiro, ou não activated
    public boolean getActivated()
    {
        return activated;
    }

    public void setActivated(boolean info)
    {
        activated = info;
    }


    // depois do bloco tempo sumir, seus atributos são renovados, esparando ser posto de volta ao tabuleiro
    public void reset()
    {
        life = 4;
        activated = false;
        img = new Image(new Texture(Gdx.files.internal("blocks/time.png")));
    }
}
