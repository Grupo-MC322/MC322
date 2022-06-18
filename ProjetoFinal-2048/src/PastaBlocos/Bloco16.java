package PastaBlocos;
public class Bloco16 implements Blocos
{
    private int numero = 16;
    private boolean juntado = false;

    public int getNumero() 
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco32();
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