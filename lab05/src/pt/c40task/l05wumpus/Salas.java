package pt.c40task.l05wumpus;

public class Salas
{
    private int coordX, coordY;
    private char info = '-';
    private Componentes componente = null;
    
    public Salas(int coordX, int coordY)
    {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public void recebe_componentes(Componentes componente)
    {
        this.componente = componente;
    }

    public void setInfo(char info)
    {
        this.info = info;
    }

    public char getInfo()
    {
        return info;
    }
}