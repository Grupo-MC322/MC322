import PastaBlocos.*;

public class Tabuleiro {
    Blocos[][] tabuleiro;

    Tabuleiro(int tamanhoX, int tamanhoY){
        tabuleiro = new Blocos[tamanhoX][tamanhoY];
        for(int i = 0; i < tamanhoX; i++)
        {
            for(int j = 0; j < tamanhoY; j++)
            {
                tabuleiro[i][j] = new Vazio();
            }
        }
    }
}