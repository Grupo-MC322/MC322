package pt.c40task.l05wumpus;

public class Salas
{
    private int coordX, coordY;
    private Componentes componente = null;
  
    public Salas(int coordX, int coordY)
    {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public void recebe_componentes(Componentes componente)
    {
        this.componente = componente;
        componente.setCoord(coordX, coordY);
    }

    public char getInfo()
    {
        return componente.getInfo();
    }
}