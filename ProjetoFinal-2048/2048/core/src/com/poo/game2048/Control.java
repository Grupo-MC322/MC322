package com.poo.game2048;

import java.util.Objects;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.poo.game2048.Blocks.*;
import com.poo.game2048.Screens.LooseScreen;

public class Control implements IControlGameScreen, IControlSettingScreen
{
    private final Creator creator;
    private Stage stage;
    private SpriteBatch batch;
    private IBoardControl board;

    private boolean buttonBombSelected;
    private boolean buttonDelSelected;
    private boolean buttonTimeSelected;
    private boolean button2xSelected;
    private boolean buttonMusicSelected;

    private ILifeBlocks timer, bomb;

    private int verticalEnd = 0;
    private int horizontalEnd = 0;

    private boolean nonExistentVoid;
    private boolean smthChanged;

    private boolean win = false;

    public Control(final Creator creator)
    {
        this.creator = creator;
        this.batch = creator.getBatch();
        this.stage = creator.getStage();
        
        bomb = BombBlock.getInstance();
        timer = TimeBlock.getInstance();
    }

    // Adiciona um block entre 1, 2, 4 ou especiais em alguma posição vazia do tabuleiro.
    public void spawnBlock()
    {
        IBlocks blockSpawned = new NumBlock(0);
        Random random = new Random();
        int vertical = random.nextInt(board.getSize());
		int horizontal = random.nextInt(board.getSize());

        // definição das probabilidades de cada block ser adicionado
		if (Objects.equals(board.getId(vertical, horizontal), 0))
		{
			int index = random.nextInt(100);
            if(index < 10)
            {
                blockSpawned = new NumBlock(1);
            }
            else if (index < 60)
            {
                blockSpawned = new NumBlock(2);
            }
            else if (index < 80)
            {
                blockSpawned = new NumBlock(4);
            }
            else if (index < 85 && bomb.getActivated() == false && getButtonSelected("bomb"))
            {
                blockSpawned = bomb;
                
                bomb.setActivated(true);
                bomb.setVertical(vertical);
                bomb.setHorizontal(horizontal);
            }
            else if (index < 90 && timer.getActivated() == false && getButtonSelected("time"))
            {
                blockSpawned = timer;

                timer.setActivated(true);
                timer.setVertical(vertical);
                timer.setHorizontal(horizontal);
            }
            else if (index < 95 && getButtonSelected("del"))
            {
                blockSpawned = new DelBlock();
            }
            else if (index < 100 && getButtonSelected("2x"))
            {
                blockSpawned = new DoubleBlock();
            }
            else
            {
                spawnBlock();
            }
            
            // adicionando o novo block ao tabuleiro e animatendo
            board.setBlock(vertical, horizontal, blockSpawned);
            board.getBlock(vertical, horizontal).getImage().setScale(.75f);
			board.getBlock(vertical, horizontal).getImage().addAction(Actions.scaleTo(1, 1, .25f));
		}
		else
		{
			spawnBlock();
		}
    }

    public void transferInput(char direction)
	{   
		if(direction == 'w')
        {
            for(int horizontal = board.getSize() - 1; horizontal >= 0; horizontal--)
            {
                for(int vertical = 0; vertical < board.getSize(); vertical++)
                {
                    checkViability(vertical, horizontal, direction);
                }
            }
        }
        else if(direction == 's')
        {
            for(int horizontal = 0; horizontal < board.getSize(); horizontal++)
            {
                for(int vertical = board.getSize() - 1; vertical >= 0; vertical--)
                {
                    checkViability(vertical, horizontal, direction);
                }
            }
        }
        else if(direction == 'a')
        {
            for(int vertical = 0; vertical < board.getSize(); vertical++)
            {
                for(int horizontal = 0; horizontal < board.getSize(); horizontal++)
                {
                    checkViability(vertical, horizontal, direction);
                }
            }
        }
        else if(direction == 'd')
        {
            for(int vertical = board.getSize() - 1; vertical >= 0; vertical--)
            {
                for(int horizontal = 0; horizontal < board.getSize(); horizontal++)
                {
                    checkViability(vertical, horizontal, direction);
                }
            }
        }
        
        goThroughBoard();
        if(smthChanged)
        {
            updateLifes();
            spawnBlock();
            smthChanged = false;
        }
	}

    private void checkViability(int vertical, int horizontal, char direction)
    {
        if(!Objects.equals(board.getId(vertical, horizontal), 0) && board.getBlock(vertical, horizontal).getCombined() == false) // se não for 0 e se não juntou
        {
            interpretInput(direction, vertical, horizontal, batch, stage);
        }
    }
    
    public void interpretInput(char direction, int verticalIni, int horizontalIni, SpriteBatch batch, Stage stage)
    {
        planMove(direction, verticalIni, horizontalIni);

        if(0 <= verticalEnd && verticalEnd < board.getSize() && 0 <= horizontalEnd && horizontalEnd < board.getSize()) // verifica se está dentro do tabuleiro
        {
            if (board.getBlock(verticalEnd, horizontalEnd).getCombined() == false)
            {
                if(board.getBlock(verticalIni, horizontalIni) instanceof ILifeBlocks && (Objects.equals(board.getId(verticalEnd, horizontalEnd), 0) || Objects.equals(board.getId(verticalEnd, horizontalEnd), "del") || Objects.equals(board.getId(verticalEnd, horizontalEnd), "2x")))
                {
                    ((ILifeBlocks) board.getBlock(verticalIni, horizontalIni)).setVertical(verticalEnd);
                    ((ILifeBlocks) board.getBlock(verticalIni, horizontalIni)).setHorizontal(horizontalEnd);
                }
                move(direction, verticalIni, horizontalIni, batch, stage);
            }
        }
    }

    private void planMove(char direction, int verticalIni, int horizontalIni)
    {
        switch (direction)
        {
            case 'w':
                verticalEnd = verticalIni;
                horizontalEnd = horizontalIni + 1;
                break;
            case 'a':
                verticalEnd = verticalIni - 1;
                horizontalEnd = horizontalIni;
                break;
            case 's':
                verticalEnd = verticalIni;
                horizontalEnd = horizontalIni - 1;
                break;
            case 'd':
                verticalEnd = verticalIni + 1;
                horizontalEnd = horizontalIni;
                break;
        }
    }

    // Realiza a moveção de um block de uma posição inicial indicada para a posição final definida.
    private void move(char direction, int verticalIni, int horizontalIni, SpriteBatch batch, Stage stage)
    {
        // animateção
        float posXBlock = ((500 * 0.05f) + (500 * 0.87f / board.getSize()) * verticalEnd + (500 * 0.01f) * verticalEnd);
        float posYBlock = ((500 * 0.05f) + (500 * 0.87f / board.getSize()) * horizontalEnd + (500 * 0.01f) * horizontalEnd);
        MoveToAction combineBlock = new MoveToAction();
        combineBlock.setPosition(posXBlock,posYBlock);
        combineBlock.setDuration(0.35f);
        combineBlock.setInterpolation(Interpolation.smooth);
        board.getBlock(verticalIni, horizontalIni).getImage().addAction(combineBlock);
        SequenceAction animateBlock = new SequenceAction(combineBlock, Actions.removeActor());

        // quando está vazio na frente, livre para continuar se movendo
        if(Objects.equals(board.getId(verticalEnd, horizontalEnd), 0))
        {
            board.setBlock(verticalEnd, horizontalEnd, board.getBlock(verticalIni, horizontalIni));
            board.getBlock(verticalIni, horizontalIni).getImage().addAction(animateBlock);
            board.setBlock(verticalIni, horizontalIni, new NumBlock(0));
            interpretInput(direction, verticalEnd, horizontalEnd, batch, stage);
            smthChanged = true;
        }

        // quando ambos os blocks são iguais e podem se combiner
        else if(Objects.equals(board.getId(verticalEnd, horizontalEnd), board.getId(verticalIni, horizontalIni)))
        {
            board.getBlock(verticalEnd, horizontalEnd).getImage().addAction(Actions.removeActor());
            if(board.getBlock(verticalEnd, horizontalEnd) instanceof NumBlock)
            {
                ((NumBlock) board.getBlock(verticalEnd, horizontalEnd)).combineDouble();
            }
            board.getBlock(verticalIni, horizontalIni).getImage().addAction(animateBlock);
            board.setBlock(verticalIni, horizontalIni, new NumBlock(0));
            board.getBlock(verticalEnd, horizontalEnd).setCombined(true);
            smthChanged = true;
        }

        // quando o block deleta deleta o outro: ou quando o deleta está na posição final ou na inicial
        else if(Objects.equals(board.getId(verticalEnd, horizontalEnd), "del") || Objects.equals(board.getId(verticalIni, horizontalIni), "del"))
        {
            board.getBlock(verticalIni, horizontalIni).getImage().addAction(animateBlock);
            board.setBlock(verticalIni, horizontalIni, new NumBlock(0));
            board.getBlock(verticalEnd, horizontalEnd).getImage().addAction(Actions.removeActor());
            board.setBlock(verticalEnd, horizontalEnd, new NumBlock(0));
            smthChanged = true;
        }

        // quando o block dobro (que está no destino do movimento) vai combineDoubler o outro 
        else if(Objects.equals(board.getId(verticalEnd, horizontalEnd), "2x"))
        {
            board.getBlock(verticalIni, horizontalIni).getImage().addAction(animateBlock);
            if(board.getBlock(verticalIni, horizontalIni) instanceof NumBlock)
            {
                ((NumBlock) board.getBlock(verticalIni, horizontalIni)).combineDouble();
            }
            board.getBlock(verticalEnd, horizontalEnd).getImage().addAction(Actions.removeActor());
            board.setBlock(verticalEnd, horizontalEnd, board.getBlock(verticalIni, horizontalIni));
            board.getBlock(verticalIni, horizontalIni).getImage().addAction(Actions.removeActor());
            board.setBlock(verticalIni, horizontalIni, new NumBlock(0));
            board.getBlock(verticalIni, horizontalIni).setCombined(true);
            smthChanged = true;
        }
        
        // quando o block dobro (que está na origem do movimento) vai combineDoubler o outro
        else if(Objects.equals(board.getId(verticalIni, horizontalIni), "2x"))
        {
            board.getBlock(verticalIni, horizontalIni).getImage().addAction(animateBlock);
            board.setBlock(verticalIni, horizontalIni, new NumBlock(0));
            board.getBlock(verticalEnd, horizontalEnd).getImage().addAction(Actions.removeActor());
            if(board.getBlock(verticalEnd, horizontalEnd) instanceof NumBlock)
            {
                ((NumBlock) board.getBlock(verticalEnd, horizontalEnd)).combineDouble();
            }
            board.getBlock(verticalEnd, horizontalEnd).setCombined(true);
            smthChanged = true;
        }
    }

    public void updateLifes()
    {
        if (bomb.getActivated())
        {
            // diminui 1
            bomb.setLife(-1);
            board.getBlock(bomb.getVertical(), bomb.getHorizontal()).getImage().addAction(Actions.removeActor());

            // setup das imagens para identificação do estado do block
            if(bomb.getLife() == 2)
            {
                bomb.setImage(new Image(new Texture(Gdx.files.internal("blocks/bomb_2:3.png"))));
            }
            else if(bomb.getLife() == 1)
            {
                bomb.setImage(new Image(new Texture(Gdx.files.internal("blocks/bomb_3:3.png"))));
            }
        }
        if (timer.getActivated())
        {
            // diminui 1
            timer.setLife(-1);
            board.getBlock(timer.getVertical(), timer.getHorizontal()).getImage().addAction(Actions.removeActor());

            // setup das imagens para identificação do estado do block
            if(timer.getLife() == 3)
            {
                timer.setImage(new Image(new Texture(Gdx.files.internal("blocks/time_3:4.png"))));
            }
            else if(timer.getLife() == 2)
            {
                timer.setImage(new Image(new Texture(Gdx.files.internal("blocks/time_2:4.png"))));
            }
            else if(timer.getLife() == 1)
            {
                timer.setImage(new Image(new Texture(Gdx.files.internal("blocks/time_1:4.png"))));
            }
        }
        if(bomb.getLife() == 0)
        {
            smthChanged = true;
            bomb.reset();
            aimNeighbors(bomb.getVertical(), bomb.getHorizontal());
        }
        if (timer.getLife() == 0)
        {
            smthChanged = true;
            timer.reset();
            board.setBlock(timer.getVertical(), timer.getHorizontal(), new NumBlock(0));
        }
    }

    private void aimNeighbors(int vertical, int horizontal)
    {
        explode(vertical, horizontal);

        vertical--;
        horizontal--;
        explode(vertical, horizontal);
        
        vertical++;
        explode(vertical, horizontal);

        vertical++;
        explode(vertical, horizontal);

        horizontal++;
        explode(vertical, horizontal);

        horizontal++;
        explode(vertical, horizontal);

        vertical--;
        explode(vertical, horizontal);

        vertical--;
        explode(vertical, horizontal);

        horizontal--;
        explode(vertical, horizontal);
    }

    private void explode(int vertical, int horizontal)
    {
        if(vertical >= 0 && vertical < board.getSize() && horizontal >= 0 && horizontal < board.getSize())
        {
            SequenceAction animateExplosion = new SequenceAction(Actions.scaleTo(0, 0, .25f), Actions.removeActor());
            board.getBlock(vertical, horizontal).getImage().addAction(animateExplosion);
            board.setBlock(vertical, horizontal, new NumBlock(0));
        }
    }

    public void goThroughBoard()
    {
        nonExistentVoid = true;
        for(int i = 0; i < board.getSize(); i++)
        {
            for(int j = 0; j < board.getSize(); j++)
            {
                if(Objects.equals(board.getId(i, j), 2048))
                {
                    win = true;
                }
                else if(Objects.equals(board.getId(i, j), 0))
                {
                    nonExistentVoid = false;
                }
                board.getBlock(i, j).setCombined(false);
            }
        }
        if(nonExistentVoid)
        {
            creator.setScreen(new LooseScreen(creator));
        }
    }

    public void setButtonSelected(String idButton, boolean selected)
    {
        switch(idButton)
        {
            case("bomb"):
                buttonBombSelected = selected;
                break;
            case("del"):
                buttonDelSelected = selected;
                break;
            case("time"):
                buttonTimeSelected = selected;
                break;
            case("2x"):
                button2xSelected = selected;
                break;
            case("music"):
                buttonMusicSelected = selected;
                break;
            default:
                break;
        }
    }

    public boolean getButtonSelected(String idButton)
    {
        switch(idButton)
        {
            case("bomb"):
                return buttonBombSelected;
            case("del"):
                return buttonDelSelected;
            case("time"):
                return buttonTimeSelected;
            case("2x"):
                return button2xSelected;
            case("music"):
                return buttonMusicSelected;
            default:
                return false;
        }
    }

    public void connectBoard(Board tabuleiro)
    {
        board = tabuleiro;
    }

    public boolean getWin()
    {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}