package PastaBlocos;

public class Deleta implements Blocos
{
    private int numero = -1;

    public int getNumero()
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Deleta();
    }

}