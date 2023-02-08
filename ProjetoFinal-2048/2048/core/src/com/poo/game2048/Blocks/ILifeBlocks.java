package com.poo.game2048.Blocks;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public interface ILifeBlocks extends IBlocks
{
    public void setImagem(Image imagem);
    public int getVida();
    public void setVida(int mudanca);
    public boolean getAtivo();
    public void setAtivo(boolean info);
    public int getLinha();
    public void setLinha(int linha);
    public int getColuna();
    public void setColuna(int coluna);
    public void reset();
}
