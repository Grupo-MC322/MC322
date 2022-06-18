package PastaBlocos;
public class Preto implements Blocos
{
    private int numero = -2;
    private int vida = 3;
    private boolean juntado = false;

    public int getNumero()
    {
        return numero;
    }
    public void diminuiVida()
    {
        vida--;
    }
    public int getvida()
    {
        return vida;
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