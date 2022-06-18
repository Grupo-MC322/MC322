package PastaBlocos;
public class Bloco1 implements Blocos
{
    private int id = 1;
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public Blocos junta()
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
