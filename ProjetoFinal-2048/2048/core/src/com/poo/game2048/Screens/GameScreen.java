package com.poo.game2048.Screens;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.game2048.Board;
import com.poo.game2048.Creator;
import com.poo.game2048.IControlGameScreen;

public class GameScreen extends AbstractScreen
{
    private final Creator creator;
    private Stage stage;
    private OrthographicCamera camera;
    private Board board;
    private IControlGameScreen control;
    private char direction;

    public GameScreen(final Creator creator)
    {
        // conexões
        this.creator = creator;
        control = creator.getControl();
        stage = creator.getStage();
        stage.clear();

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 500, 500);

        // board creation
        board = new Board(creator.getSizeBoard());
        control.connectBoard(board);

        // board initialization
        for(int vertical = 0; vertical < board.getSize(); vertical++)
            for(int horizontal = 0; horizontal < board.getSize(); horizontal++)
            {
                board.getBlock(vertical, horizontal).setPosX((float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / board.getSize()) * vertical + (camera.viewportWidth * 0.01) * vertical));
                board.getBlock(vertical, horizontal).setPosY((float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / board.getSize()) * horizontal + (camera.viewportHeight * 0.01) * horizontal));
                board.getBlock(vertical, horizontal).setSize((float) (camera.viewportHeight * 0.87 / board.getSize()));
                stage.addActor(board.getBlock(vertical, horizontal).getImage());
            }
        stage.draw();
		
        // adding the first 2 blocks
        control.spawnBlock();
        control.spawnBlock();
    }

    private void readInput()
    {
        if(!control.getWin()){
            if(Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A))
            {
                direction = 'a';
                control.transferInput(direction);
            }
                
            else if(Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D))
            {
                direction = 'd';
                control.transferInput(direction);
            }
                
            else if(Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W))
            {
                direction = 'w';
                control.transferInput(direction);
            }
                
            else if(Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S))
            {
                direction = 's';
                control.transferInput(direction);
            }
        }
        else if(Gdx.input.isKeyJustPressed(Keys.ENTER) && control.getWin())
        {
            stage.clear();
            control.setWin(false);
            creator.setScreen(new WinScreen(creator));
        }
    }

    @Override
	public void render(float delta)
    {
		ScreenUtils.clear(0.32f, 0.41f, 0.42f, 1); // definition of background color

        camera.update();
        
        // board drawing
        for(int vertical = 0; vertical < board.getSize(); vertical++)
            for(int horizontal = 0; horizontal < board.getSize(); horizontal++)
            {
                board.getBlock(vertical, horizontal).setPosX((float) ((camera.viewportWidth * 0.05) + (camera.viewportWidth * 0.87 / board.getSize()) * vertical + (camera.viewportWidth * 0.01) * vertical));
                board.getBlock(vertical, horizontal).setPosY((float) ((camera.viewportHeight * 0.05) + (camera.viewportHeight * 0.87 / board.getSize()) * horizontal + (camera.viewportHeight * 0.01) * horizontal));
                board.getBlock(vertical, horizontal).setSize((float) (camera.viewportHeight * 0.87 / board.getSize()));
                
                if(!Objects.equals(board.getId(vertical, horizontal), 0))
                    stage.addActor(board.getBlock(vertical, horizontal).getImage());
            }

        if(control.getWin())
        {
            Image winMessage = new Image(new Texture(Gdx.files.internal("extras/warning.png")));
            winMessage.setPosition(camera.viewportWidth/2 - camera.viewportWidth * 0.45f, camera.viewportHeight/2 - camera.viewportHeight * 0.13f);
            winMessage.setSize(camera.viewportWidth * 0.9f, camera.viewportHeight * 0.26f);
            stage.addActor(winMessage);
        }

        stage.draw();
        stage.act();

        // next turn
        readInput();
	}
}