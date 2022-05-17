package pt.c40task.l05wumpus;

public class Wumpus extends Componentes
{
    protected int coordX, coordY;
    protected char info = 'W';

    public Wumpus(int coordX, int coordY, Caverna caverna)
    {
        this.coordX = coordX;
        this.coordY = coordY;

        // posicionamento dos fedores em volta do buraco
        if(coordX-1 >= 0)
            caverna.setTabuleiro(coordX-1, coordY, new Fedor());
        if(coordY-1 >= 0)
            caverna.setTabuleiro(coordX, coordY-1, new Fedor());
        if(coordX+1 < 4)
            caverna.setTabuleiro(coordX+1, coordY, new Fedor());
        if(coordY+1 < 4)
            caverna.setTabuleiro(coordX, coordY+1, new Fedor());

    }
}
