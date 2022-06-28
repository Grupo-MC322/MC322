package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BlocoBomba implements IBlocosTimer
{
    private static IBlocosTimer instance;
    private String id = "bomba";
    private int vida = 3;
    private int linha;
    private int coluna;
    private boolean ativo = false;
    private boolean juntado = false;
    private float posX;
    private float posY;
    private float size;

    private Texture textura = new Texture(Gdx.files.internal("blocos/bloco_bomba.png"));
    private Image imagem = new Image(textura);
    
    // para implementar o design pattern singleton, é necessário um construtor privado
    private BlocoBomba()
    {}
    
    // implementação do design pattern singleton, garantindo que só uma instância de bomba exista
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

    // a bomba tem 3 vidas até explodir
    public int getVida()
    {
        return vida;
    }

    public void setVida(int mudanca)
    {
        vida += mudanca;
    }

    // a bomba pode estar ativa, ou seja, participando do tabuleiro, ou não ativa
    public boolean getAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean info)
    {
        ativo = info;
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

    public void setTextura(Texture textura) {
        this.textura = textura;
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

    // depois da bomba explodir, seus atributos são renovados, esparando ser posta de volta ao tabuleiro.
    public void reset()
    {
        setVida(3);
        setAtivo(false);
        imagem = new Image(new Texture(Gdx.files.internal("blocos/bloco_bomba.png")));
    }
}
