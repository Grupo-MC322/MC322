package PastaBlocos;
import java.util.Random;
import java.lang.Math;

public class Bomba implements Blocos
{
    private int numero;
    private int vida = 3;
    private boolean ativa = false;
    private boolean explosao = false;
    private int coordX;
    private int coordY;

    public Bomba()
    {
        Random gera_numero = new Random();
        numero = (int) Math.pow(2, gera_numero.nextInt(5));
    }
    
    public int getNumero()
    {
        return numero;
    }

    public int getvida()
    {
        return vida;
    }

    public void setVida(int mudanca)
    {
        vida += mudanca;
    }

    public Blocos dobra()
    {
        return new Bomba();
    }

    public void setAtiva(boolean info)
    {
        ativa = info;
    }

    public boolean getAtiva()
    {
        return ativa;
    }

    public int getCoordX()
    {
        return CoordX;
    }

    public void setCoordX(int x)
    {
        CoordX = x;
    }

    public int getCoordY()
    {
        return CoordY;
    }

    public void setCoordY(int y)
    {
        CoordY = y;
    }

    public boolean getExplosao()
    {
        return explosao;
    }

    public void setExplosao(boolean info)
    {
        explosao = info;
    }

}