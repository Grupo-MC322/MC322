package pt.c40task.l05wumpus;
import java.util.Random;

public class Wumpus extends Componentes
{
    protected int coordX, coordY;
    protected char info = 'W';

    // função que coloca os fedores em volta da posição do wumpus
    public void colocarFedor(Caverna caverna)
    {
        if(coordX-1 >= 0 && (caverna.getInfo(coordX-1, coordY) == '-' || caverna.getInfo(coordX-1, coordY) == 'b' ))
            caverna.setTabuleiro(coordX-1, coordY, new Fedor());
        if(coordY-1 >= 0 && (caverna.getInfo(coordX, coordY-1) == '-' || caverna.getInfo(coordX, coordY-1) == 'b' ))
            caverna.setTabuleiro(coordX, coordY-1, new Fedor());
        if(coordX+1 < 4 && (caverna.getInfo(coordX+1, coordY) == '-' || caverna.getInfo(coordX+1, coordY) == 'b' ))
            caverna.setTabuleiro(coordX+1, coordY, new Fedor());
        if(coordY+1 < 4 && (caverna.getInfo(coordX, coordY+1) == '-' || caverna.getInfo(coordX, coordY+1) == 'b' ))
            caverna.setTabuleiro(coordX, coordY+1, new Fedor());
    }

    public int batalha()
    {
        Random rand = new Random();
        int resultado = rand.nextInt(1);
        if(resultado == 0){ // se o jogador perder
            return 500;
        } else { // se o jogador ganhar
            return -100;
        }
    }
}
