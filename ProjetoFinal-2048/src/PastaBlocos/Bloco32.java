package PastaBlocos;
public class Bloco32 implements Blocos
{
    private int id = 32;
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public Blocos junta()
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
