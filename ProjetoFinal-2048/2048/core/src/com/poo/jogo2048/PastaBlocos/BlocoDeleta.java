package com.poo.jogo2048.PastaBlocos;

public class BlocoDeleta implements IBlocos {
    private String id = "deleta";
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public IBlocos junta()
    {
        return new BlocoDeleta();
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
