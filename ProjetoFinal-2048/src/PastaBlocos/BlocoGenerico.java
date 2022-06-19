package PastaBlocos;
public class BlocoGenerico implements IBlocos
{
    private int id;
    private boolean juntado = false;

    public BlocoGenerico (int id)
    {
        this.id = id;
    }
    
    public Object getId()
    {
        return id;
    }

    public IBlocos junta()
    {
        return new BlocoGenerico(id * 2);
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
