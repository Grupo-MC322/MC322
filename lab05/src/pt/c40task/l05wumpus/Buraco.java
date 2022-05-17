package pt.c40task.l05wumpus;

public class Buraco extends Componentes 
{
    private int coordX, coordY;
    private char info = 'B';

    public Buraco(int coordX, int coordY, Caverna caverna)
    {
        this.coordX = coordX;
        this.coordY = coordY;

        // posicionamento das brisas em volta do buraco
        if(coordX-1 >= 0)
            caverna.setTabuleiro(coordX-1, coordY, new Brisa());
        if(coordY-1 >= 0)
            caverna.setTabuleiro(coordX, coordY-1, new Brisa());
        if(coordX+1 < 4)
            caverna.setTabuleiro(coordX+1, coordY, new Brisa());
        if(coordY+1 < 4)
            caverna.setTabuleiro(coordX, coordY+1, new Brisa());

    }
}