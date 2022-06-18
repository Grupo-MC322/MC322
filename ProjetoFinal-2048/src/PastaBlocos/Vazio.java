package PastaBlocos;
public class Vazio implements Blocos
{
    private int numero = 0;
    private boolean juntado = false;

    public int getNumero()
    {
        return numero;
    }
    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public Blocos dobra()
    {
        return new Preto();
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