package com.poo.jogo2048;

public interface IGameScreenControl
{
    public void conectaTabuleiro(Tabuleiro tabuleiro);
    public void spawnBloco();
    public void transfereComando(char direcao);
	public boolean getGanhou();
    public void setGanhou(boolean b);
}