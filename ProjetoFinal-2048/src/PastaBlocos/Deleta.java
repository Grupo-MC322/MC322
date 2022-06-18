package PastaBlocos;

public class Deleta implements Blocos
{
    private int numero = -1;
    private boolean juntado = false;

    public int getNumero()
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Deleta();
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

// cuidado para nn deletar o vazio