package com.poo.jogo2048.PastaBlocos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BlocoGenerico implements IBlocos
{
	private int id;
    private Texture imagem;
    private boolean juntado = false;

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
                setImagem(new Texture(Gdx.files.internal("blocos/vazio.png")));
                break;
            case 1:
                setImagem(new Texture(Gdx.files.internal("blocos/1.png")));
                break;
            case 2:
                setImagem(new Texture(Gdx.files.internal("blocos/2.png")));
                break;
            case 4:
                setImagem(new Texture(Gdx.files.internal("blocos/4.png")));
                break;
            case 8:
                setImagem(new Texture(Gdx.files.internal("blocos/8.png")));
                break;
            case 16:
                setImagem(new Texture(Gdx.files.internal("blocos/16.png")));
                break;
            case 32:
                setImagem(new Texture(Gdx.files.internal("blocos/32.png")));
                break;
            case 64:
                setImagem(new Texture(Gdx.files.internal("blocos/64.png")));
                break;
            case 128:
                setImagem(new Texture(Gdx.files.internal("blocos/128.png")));
                break;
            case 256:
                setImagem(new Texture(Gdx.files.internal("blocos/256.png")));
                break;
            case 512:
                setImagem(new Texture(Gdx.files.internal("blocos/512.png")));
                break;
            case 1024:
                setImagem(new Texture(Gdx.files.internal("blocos/1024.png")));
                break;
            case 2048:
                setImagem(new Texture(Gdx.files.internal("blocos/2048.png")));
                break;
        }
    }

    public BlocoGenerico junta()
    {
        return new BlocoGenerico(id * 2);
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
