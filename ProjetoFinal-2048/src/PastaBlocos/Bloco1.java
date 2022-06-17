package PastaBlocos;
public class Bloco1 implements Blocos
{
    private int numero = 1;

    public int getNumero()
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco2();
    }
}
