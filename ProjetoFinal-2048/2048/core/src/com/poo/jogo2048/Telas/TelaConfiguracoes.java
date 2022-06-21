package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TelaConfiguracoes extends TelaAbstrata
{
    private Texture txtrFundo;
    private Texture txtr4x4;
    private Texture txtr5x5;


    public TelaConfiguracoes()
    {
        super();
        txtrFundo = new Texture(Gdx.files.internal("fundo_configuracoes.png"));
        txtr4x4 = new Texture(Gdx.files.internal("selecao_4x4.png"));
        txtr5x5 = new Texture(Gdx.files.internal("selecao_5x5_unselected.png"));

    }

    @Override
    public void buildStage()
    {

    }
}
