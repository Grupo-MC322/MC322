import PastaBlocos.*;

public class Controle {
    Controle()
    {

    }

    void juntaBloco(int posX, int posY, int direcao, Blocos[][] tabuleiro){
        // direcao: 0-> cima, 1->baixo, 2->esquerda, 3->direita

        // se for para cima (0)
        // se for vazio
        if(tabuleiro[posX][posY-1].getNumero() == 0)
        {
            tabuleiro[posX][posY-1] = tabuleiro[posX][posY];
            tabuleiro[posX][posY] = new Vazio();
            juntaBloco(posX, posY-1, direcao, tabuleiro);
        }

        // se o numero for igual
        if(tabuleiro[posX][posY-1].getNumero() == tabuleiro[posX][posY].getNumero())
        {
            tabuleiro[posX][posY-1] = tabuleiro[posX][posY];
            tabuleiro[posX][posY] = new Vazio();
            return;
        }



    }
}
