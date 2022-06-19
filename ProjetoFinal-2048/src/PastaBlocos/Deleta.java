package PastaBlocos;

public class Deleta implements IBlocos
{
    private String id = "deleta";
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public IBlocos junta()
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