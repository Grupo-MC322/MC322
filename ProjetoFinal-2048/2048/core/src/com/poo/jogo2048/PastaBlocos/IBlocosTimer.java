package com.poo.jogo2048;

public interface IBlocosTimer extends IBlocos {
    public int getVida();
    public void setVida(int mudanca);
    public int getCoordX();
    public void setCoordX(int x);
    public int getCoordY();
    public void setCoordY(int y);
}
