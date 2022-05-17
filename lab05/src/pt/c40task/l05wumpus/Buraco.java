package pt.c40task.l05wumpus;

public class Buraco extends Componentes 
{
    protected int coordX, coordY;
    protected char info = 'B';
    
    // função que coloca as brisas em volta da posição do buraco
    public void colocarBrisas(Caverna caverna)
    {
        if(coordX-1 >= 0 && caverna.getInfo(coordX-1, coordY) == '-')
            caverna.setTabuleiro(coordX-1, coordY, new Brisa());
        if(coordY-1 >= 0 && caverna.getInfo(coordX, coordY-1) == '-')
            caverna.setTabuleiro(coordX, coordY-1, new Brisa());
        if(coordX+1 < 4 && caverna.getInfo(coordX+1, coordY) == '-')
            caverna.setTabuleiro(coordX+1, coordY, new Brisa());
        if(coordY+1 < 4 && caverna.getInfo(coordX, coordY+1) == '-')
            caverna.setTabuleiro(coordX, coordY+1, new Brisa());
    }
}