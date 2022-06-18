package PastaBlocos;
public class Vazio implements Blocos
{
    private int id = 0;
    private boolean juntado = false;

    public Object getId()
    {
        return id;
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