package PastaBlocos;
public class Bloco32 implements Blocos
{
    private int numero = 32;
    private boolean juntado = false;

    public int getNumero() 
    {
        return numero;
    }

    public Blocos dobra()
    {
        return new Bloco32(); //mudar para Blocos64 quando existir
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
