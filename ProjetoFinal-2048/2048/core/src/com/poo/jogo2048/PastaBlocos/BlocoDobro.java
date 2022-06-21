package com.poo.jogo2048.PastaBlocos;

public class BlocoDobro implements IBlocos {
    private String id = "x2";
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public IBlocos junta()
    {
        return new BlocoDobro();
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
