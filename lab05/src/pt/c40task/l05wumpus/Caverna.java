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
                tabuleiro[i][j] = new Salas();
            }
        }
    }

    public char getInfo(int linha, int coluna)
    {
        return tabuleiro[linha][coluna].getComponente().getInfo();
    }

    public void addComponente(int linha, int coluna, Componentes componente)
    {
        tabuleiro[linha][coluna].addComponente(componente);
    }

    public Componentes getComponente(int linha, int coluna)
    {
        return tabuleiro[linha][coluna].getComponente();
    }

    public void delComponente(int linha, int coluna, Componentes componente)
    {
        tabuleiro[linha][coluna].delComponente(componente);
    }

    public Componentes getHeroi(int linha, int coluna)
    {
        return tabuleiro[linha][coluna].getHeroi();
    }

    public Componentes getFedor(int linha, int coluna)
    {
        return tabuleiro[linha][coluna].getFedor();
    }

    public char[][] apresentar()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                cave[i][j] = tabuleiro[i][j].getComponente().getInfo();
            }
        }
        return cave;
    }
}