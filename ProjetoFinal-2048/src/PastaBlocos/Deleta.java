package PastaBlocos;

public class Deleta implements Blocos
{
    private String id = "deleta";
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public Blocos junta()
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