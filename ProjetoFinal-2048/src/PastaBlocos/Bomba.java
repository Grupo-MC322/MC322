package PastaBlocos;
import java.util.Random;
import java.lang.Math;

public class Bomba implements Blocos
{
    private int numero;
    private int vida = 3;

    public Bomba()
    {
        Random gera_numero = new Random();
        numero = (int) Math.pow(2, gera_numero.nextInt(5));
    }
    public int getNumero()
    {
        return numero;
    }
    public void diminuiVida()
    {
        vida--;
    }
    public int getvida()
    {
        return vida;
    }

}