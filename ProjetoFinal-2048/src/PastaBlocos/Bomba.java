package PastaBlocos;
import java.util.Random;
import java.lang.Math;

public class Bomba implements Blocos
{
    private int id;
    private int vida = 3;
    private int coordX;
    private int coordY;
    private boolean juntado = false;

    public Bomba()
    {
        Random geraId = new Random();
        id = (int) Math.pow(2, geraId.nextInt(5));
    }
    
    public Object getId()
    {
        return id;
    }

    public int getvida()
    {
        return vida;
    }

    public void setVida(int mudanca)
    {
        vida += mudanca;
    }

    public Blocos junta()
    {
        return new Bomba(); // nn eh bomba, eh o dobro do numero q a bomba representar
    }

    public int getCoordX()
    {
        return coordX;
    }

    public void setCoordX(int x)
    {
        coordX = x;
    }

    public int getCoordY()
    {
        return coordY;
    }

    public void setCoordY(int y)
    {
        coordY = y;
    }

    public void setJuntado(boolean info)
    {
        juntado = info;
    }
    
    public boolean getJuntado()
    {
        return juntado;
    }
}