package pt.c40task.l05wumpus;

import javax.swing.text.html.HTMLDocument.RunElement;

public class Controle 
{
    String[] movimentos;
    protected int pontuacao = 0;
    int posicaoX, posicaoY;

    public void movimentosArquivo(Caverna caverna, String[] movimentos)
    {
        this.movimentos = movimentos;
        for(int i = 0; i < movimentos.length; i++)
        {
            if(movimentos[i].equals("w") && posicaoY-1 >= 0)
            {
                if(caverna.getInfo(posicaoX, posicaoY-1) == 'W')
                {
                    Wumpus wumpus = new Wumpus();
                    int resultado;
                    resultado = wumpus.batalha();
                    pontuacao += resultado;
                    if (resultado == -100)
                    {
                        // reset jogo
                    }
        

                }
                posicaoY = posicaoY-1;
                //caverna.setTabuleiro(posicaoX, posicaoY, Heroi heroi);
            }

            // write board
        }
    }

    public void perdeu()
    {

    }
    
    public void ganhou()
    {

    }
}
