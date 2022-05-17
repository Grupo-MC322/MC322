package pt.c40task.l05wumpus;

public class Heroi extends Componentes
{
    private char info = 'P';
    private boolean flechaUsada = false;
    private boolean flechaEquipada = false;

    public char getInfo() {
        return info;
    }

    public boolean getFlechaUsada()
    {
        return flechaUsada;
    }

    public void setFlechaUsada(boolean flechaUsada)
    {
        this.flechaUsada = flechaUsada;
    }

    public boolean getFlechaEquipada()
    {
        return flechaEquipada;
    }

    public void setFlechaEquipada(boolean flechaEquipada)
    {
        this.flechaEquipada = flechaEquipada;
    }

}