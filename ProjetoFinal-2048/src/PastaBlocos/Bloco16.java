package PastaBlocos;
public class Bloco16 implements Blocos
{
    private int numero = 16;

    public int getNumero() 
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco32();
    }
}