package PastaBlocos;
public class Bloco2 implements Blocos
{
    private int numero = 2;
    private boolean juntado = false;

    public int getNumero()
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco4();
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