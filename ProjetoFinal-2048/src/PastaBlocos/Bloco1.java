package PastaBlocos;
public class Bloco1 implements Blocos
{
    private int numero = 1;
    private boolean juntado = false;

    public int getNumero()
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco2();
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
