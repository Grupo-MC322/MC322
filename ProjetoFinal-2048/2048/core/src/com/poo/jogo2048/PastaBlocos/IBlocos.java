package com.poo.jogo2048;

public interface IBlocos {
    public Object getId();
    public IBlocos junta();
    public void setJuntado(boolean info);
    public boolean getJuntado();
}
