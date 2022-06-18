package PastaBlocos;

public class Bloco8 implements Blocos
{
    private int id = 8;
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public Blocos junta()
    {
        return new Bloco16();
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
