package PastaBlocos;
public class Bloco4 implements Blocos
{
    private int numero = 4;

    public int getNumero() 
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco8();
    }
}
