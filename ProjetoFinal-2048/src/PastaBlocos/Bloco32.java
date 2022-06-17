package PastaBlocos;
public class Bloco32 implements Blocos
{
    private int numero = 32;

    public int getNumero() 
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco32(); //mudar para Blocos64 quando existir
    }
}
