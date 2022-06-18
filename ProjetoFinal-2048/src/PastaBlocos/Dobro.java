package PastaBlocos;

public class Dobro implements Blocos
{
    private int numero = 222;
    private boolean juntado = false;

    public int getNumero()
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Dobro();
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

// cuidado para nn dobrar o vazio