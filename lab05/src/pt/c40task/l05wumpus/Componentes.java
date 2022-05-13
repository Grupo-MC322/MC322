package pt.c40task.l05wumpus;

public abstract class Componentes
{
    protected int coordX, coordY;
    protected char info;

    public void setCoord(int coordX, int coordY)
    {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public char getInfo()
    {
        return info;
    }
}