package pt.c40task.l05wumpus;

public class Caverna
{
    private Salas[][] tabuleiro = new Salas[4][4];
    private char[][] cave = new char[4][4];

    public Caverna()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                tabuleiro[i][j] = new Salas(i, j);
            }
        }
    }

    public char getInfo(int linha, int coluna)
    {
        return tabuleiro[linha][coluna].getInfo();
    }

    public void setTabuleiro(int linha, int coluna, Componentes componente)
    {
        tabuleiro[linha][coluna].recebe_componentes(componente);
    }

    public char[][] apresentar()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                cave[i][j] = tabuleiro[i][j].getInfo();
            }
        }
        return cave;
    }
}