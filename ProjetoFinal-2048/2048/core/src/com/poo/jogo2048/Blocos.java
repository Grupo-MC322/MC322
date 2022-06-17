package com.poo.jogo2048;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Blocos {
	private int numero;
    private Texture imagem;

    public Blocos(int numero){
        setNumero(numero);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        switch(numero){
            case 0:
                setImagem(new Texture(Gdx.files.internal("vazio.png")));
                break;
            case 2:
                setImagem(new Texture(Gdx.files.internal("2.png")));
                break;
            case 4:
                setImagem(new Texture(Gdx.files.internal("4.png")));
                break;
            case 8:
                setImagem(new Texture(Gdx.files.internal("8.png")));
                break;
            case 16:
                setImagem(new Texture(Gdx.files.internal("16.png")));
                break;
            case 32:
                setImagem(new Texture(Gdx.files.internal("32.png")));
                break;
            case 64:
                setImagem(new Texture(Gdx.files.internal("64.png")));
                break;
            case 128:
                setImagem(new Texture(Gdx.files.internal("128.png")));
                break;
            case 256:
                setImagem(new Texture(Gdx.files.internal("256.png")));
                break;
            case 512:
                setImagem(new Texture(Gdx.files.internal("512.png")));
                break;
            case 1024:
                setImagem(new Texture(Gdx.files.internal("1024.png")));
                break;
            case 2048:
                setImagem(new Texture(Gdx.files.internal("2048.png")));
                break;
        }
    }

    public Blocos dobraNumero() {
        return new Blocos(numero * 2);
    }

    public Texture getImagem() {
        return imagem;
    }

    public void setImagem(Texture imagem) {
        this.imagem = imagem;
    }

    public void disposeImagem() {
        imagem.dispose();
    }

}
