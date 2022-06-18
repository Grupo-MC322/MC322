package PastaBlocos;
public class Preto implements Blocos
{
    private String id = "preto";
    private int vida = 3;
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public void diminuiVida()
    {
        vida--;
    }

    public int getvida()
    {
        return vida;
    }

    public Blocos junta()
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