package pt.c40task.l05wumpus;

public class Buraco extends Componentes 
{
    private char info = 'B';

    public Buraco(int coordX, int coordY, Caverna caverna)
    {
        // posicionamento das brisas em volta do buraco
        if(coordX-1 >= 0)
            caverna.addComponente(coordX-1, coordY, new Brisa());
        if(coordY-1 >= 0)
            caverna.addComponente(coordX, coordY-1, new Brisa());
        if(coordX+1 < 4)
            caverna.addComponente(coordX+1, coordY, new Brisa());
        if(coordY+1 < 4)
            caverna.addComponente(coordX, coordY+1, new Brisa());

    }
}