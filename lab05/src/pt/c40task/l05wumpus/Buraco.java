package pt.c40task.l05wumpus;

public class Buraco extends Componentes 
{
    protected int coordX, coordY;
    protected char info = 'B';
    
    public void colocarBrisas(Caverna caverna)
    {
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