package PastaBlocos;

public class Bloco8 implements Blocos
{
    private int numero = 8;

    public int getNumero() 
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco16();
    }
}
