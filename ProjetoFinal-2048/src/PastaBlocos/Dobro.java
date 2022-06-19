package PastaBlocos;

public class Dobro implements IBlocos
{
    private String id = "x2";
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public IBlocos junta()
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