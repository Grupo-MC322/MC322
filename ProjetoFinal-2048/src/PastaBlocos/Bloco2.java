package PastaBlocos;
public class Bloco2 implements Blocos
{
    private int numero = 2;

    public int getNumero()
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco4();
    }
}