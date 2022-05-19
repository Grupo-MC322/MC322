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

    public void addComponente(int linha, int coluna, Componentes componente)
    {
        tabuleiro[linha][coluna].addComponente(componente);
    }

    public void delComponente(int linha, int coluna, Componentes componente)
    {
        tabuleiro[linha][coluna].delComponente(componente);
    }

    public Componentes getComponente(int linha, int coluna, char info)
    {
        return tabuleiro[linha][coluna].getComponente(info);
    }

    public char[][] apresentar()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(tabuleiro[i][j].getComponente('-') != null)
                    cave[i][j] = '-';
                else if(tabuleiro[i][j].getComponente('W') != null)
                    cave[i][j] = 'W';
                else if(tabuleiro[i][j].getComponente('O') != null)
                    cave[i][j] = 'O';
                else if(tabuleiro[i][j].getComponente('B') != null)
                        cave[i][j] = 'B';
                else if(tabuleiro[i][j].getComponente('P') != null)
                    cave[i][j] = 'P';
                else if(tabuleiro[i][j].getComponente('f') != null)
                    cave[i][j] = 'f';
                else if(tabuleiro[i][j].getComponente('b') != null)
                    cave[i][j] = 'b';
                else
                    cave[i][j] = '#';
            }
        }
        return cave;
    }
}