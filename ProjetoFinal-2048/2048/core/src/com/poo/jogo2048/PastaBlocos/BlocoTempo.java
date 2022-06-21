package com.poo.jogo2048.PastaBlocos;

public class BlocoTempo implements IBlocosTimer {
    private String id = "preto";
    private int vida = 3;
    private int coordX;
    private int coordY;
    private boolean juntado = false;

    public Object getId()
    {
        return id;
    }

    public int getVida()
    {
        return vida;
    }

    public void setVida(int mudanca)
    {
        vida += mudanca;
    }

    public int getCoordX()
    {
        return coordX;
    }

    public void setCoordX(int x)
    {
        coordX = x;
    }

    public int getCoordY()
    {
        return coordY;
    }

    public void setCoordY(int y)
    {
        coordY = y;
    }

    public IBlocos junta()
    {
        return new BlocoTempo();
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
