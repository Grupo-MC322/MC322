package PastaBlocos;

public class Dobro implements Blocos
{
    private int numero = 222;

    public int getNumero()
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Dobro();
    }

}