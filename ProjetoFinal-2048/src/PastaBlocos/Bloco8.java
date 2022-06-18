package PastaBlocos;

public class Bloco8 implements Blocos
{
    private int numero = 8;
    private boolean juntado = false;

    public int getNumero() 
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco16();
    }

    public void setJuntado(boolean info)
    {
        juntado = info;
    }
    public boolean getJuntado()
    {
        return juntado;
    }
}
