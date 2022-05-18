package pt.c40task.l05wumpus;

public class Wumpus extends Componentes
{
    private char info = 'W';

    public Wumpus(int coordX, int coordY, Caverna caverna)
    {
        // posicionamento dos fedores em volta do buraco
        if(coordX-1 >= 0)
            caverna.addComponente(coordX-1, coordY, new Fedor());
        if(coordY-1 >= 0)
            caverna.addComponente(coordX, coordY-1, new Fedor());
        if(coordX+1 < 4)
            caverna.addComponente(coordX+1, coordY, new Fedor());
        if(coordY+1 < 4)
            caverna.addComponente(coordX, coordY+1, new Fedor());

    }
}
