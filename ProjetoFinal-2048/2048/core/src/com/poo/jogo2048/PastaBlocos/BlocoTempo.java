package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BlocoTempo implements ITimerControl
{
    private static ITimerControl instance;
    private String id = "tempo";
    private Texture textura = new Texture(Gdx.files.internal("blocos/bloco_tempo.png"));
    private Image imagem = new Image(textura);
    private boolean juntado = false;
    private float posX;
    private float posY;
    private float size;
    private int vida = 4;
    private int linha;
    private int coluna;
    private boolean ativo = false;
    
    

    // para implementar o design pattern singleton, é necessário um construtor privado
    private BlocoTempo()
    {}
    
    // implementação do design pattern singleton, garantindo que só uma instância de bloco tempo exista
    public static ITimerControl getInstance()
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

    public Texture getTextura() {
        return textura;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
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

    // o bloco tempo tem 4 vidas até desaparecer
    public int getVida()
    {
        return vida;
    }

    public void setVida(int mudanca)
    {
        vida += mudanca;
    }

    public int getLinha()
    {
        return linha;
    }

    public void setLinha(int linha)
    {
        this.linha = linha;
    }

    public int getColuna()
    {
        return coluna;
    }

    public void setColuna(int coluna)
    {
        this.coluna = coluna;
    }

    // o bloco tempo pode estar ativo, ou seja, participando do tabuleiro, ou não ativo
    public boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean info)
    {
        ativo = info;
    }


    // depois do bloco tempo sumir, seus atributos são renovados, esparando ser posto de volta ao tabuleiro
    public void reset()
    {
        vida = 4;
        ativo = false;
        imagem = new Image(new Texture(Gdx.files.internal("blocos/bloco_tempo.png")));
    }
}
