package com.poo.game2048;

public interface IControlGameScreen
{
    public void conectaTabuleiro(Board tabuleiro);
    public void spawnBloco();
    public void transfereComando(char direcao);
	public boolean getGanhou();
    public void setGanhou(boolean b);
}