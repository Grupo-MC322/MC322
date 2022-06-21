package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.poo.jogo2048.ScreenEnum;
import com.poo.jogo2048.UIFactory;

public class TelaInicial extends TelaAbstrata
{
    private Texture txtrFundo;
    private Texture txtrBotaoConfig;
    private Texture txtrBotaoInstr;

    public TelaInicial()
    {
        super();
        txtrFundo = new Texture(Gdx.files.internal("fundo_tela_inicio.png"));
        txtrBotaoConfig = new Texture(Gdx.files.internal("botao_configurar.png"));
        txtrBotaoInstr = new Texture(Gdx.files.internal("botao_instrucoes.png"));
    }

    @Override
    public void buildStage()
    {
        // adicionando os atores

        // imagem de fundo
        Image fundo = new Image(txtrFundo);
        addActor(fundo);

        // botão configurar
        ImageButton botaoConfig = UIFactory.createButton(txtrBotaoConfig);
        botaoConfig.setPosition((float) ((getWidth() / 2) - (getWidth() * 0.5 / 2)), (float) (getHeight() * 0.44));
        botaoConfig.setSize((float) (getWidth() * 0.5), (float) (getHeight() * 0.1));
        addActor(botaoConfig);

        // botão instruções
        ImageButton botaoInstr = UIFactory.createButton(txtrBotaoInstr);
        botaoInstr.setPosition((float) ((getWidth() / 2) - (getWidth() * 0.5 / 2)), (float) (getHeight() * 0.27));
        botaoInstr.setSize((float) (getWidth() * 0.5), (float) (getHeight() * 0.1));
        addActor(botaoInstr);

        botaoConfig.addListener(UIFactory.createListener(ScreenEnum.CONFIGURACOES));

        botaoInstr.addListener(UIFactory.createListener(ScreenEnum.INSTRUCOES));

    }

    @Override
	public void dispose() {
		super.dispose();
		txtrFundo.dispose();
		txtrBotaoConfig.dispose();
		txtrBotaoInstr.dispose();
	}
}
