package com.poo.jogo2048.Telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.poo.jogo2048.jogo2048;

public class TelaInstrucoes extends TelaAbstrata
{
    private Texture txtrFundo;
    OrthographicCamera camera;
    Stage stage;

    public TelaInstrucoes(final jogo2048 jogo)
    {
        this.stage = jogo.stage;
        
        Texture texture = new Texture(Gdx.files.absolute("blocos/1.png"));
    
        int X_left= Gdx.graphics.getWidth()/3-texture.getWidth()/2;
        int X_right = Gdx.graphics.getWidth()*2/3-texture.getWidth()/2;
        int Y_bottom = Gdx.graphics.getHeight()/3-texture.getHeight()/2;
    
        Image image1 = new Image(texture);
        image1.setPosition(X_left,Y_bottom);
        image1.setOrigin(image1.getWidth()/2,image1.getHeight()/2);
        jogo.stage.addActor(image1);
    
        MoveToAction moveBottomRightAction = new MoveToAction();
        moveBottomRightAction.setPosition(X_right,Y_bottom);
        moveBottomRightAction.setDuration(1);
        moveBottomRightAction.setInterpolation(Interpolation.smooth);
    
        image1.addAction(moveBottomRightAction);
    }
    
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}