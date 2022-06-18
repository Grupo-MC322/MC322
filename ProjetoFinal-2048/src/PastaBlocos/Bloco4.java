package PastaBlocos;
public class Bloco4 implements Blocos
{
    private int numero = 4;
    private boolean juntado = false;

    public int getNumero() 
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco8();
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
