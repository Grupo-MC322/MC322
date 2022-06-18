package PastaBlocos;

public class Dobro implements Blocos
{
    private String id = "dobro";
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public Blocos junta()
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

// cuidado para nn juntar o vazio