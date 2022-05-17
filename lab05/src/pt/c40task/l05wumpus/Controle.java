package pt.c40task.l05wumpus;
import java.util.Random;

public class Controle 
{
    private String movimentos;
    private int pontuacao = 0;
    private int posicaoX, posicaoY;
    private boolean achouOuro = false;
    private String status;

    public void movimentosArquivo(Caverna caverna, String movimentos)
    {
        this.movimentos = movimentos;
        Componentes componenteAnterior;
        for(int i = 0; i < movimentos.length(); i++)
        {
            char movimentoAtual = movimentos.charAt(i);
            if(movimentoAtual == 'w' && posicaoY-1 >= 0)
            {
                movimentar(posicaoX, posicaoY, posicaoX, posicaoY-1, caverna);
                posicaoY = posicaoY-1;
            } 
            else if(movimentoAtual == 's' && posicaoY+1 < 4)
            {
                movimentar(posicaoX, posicaoY, posicaoX, posicaoY+1, caverna);
                posicaoY = posicaoY+1;
            } 
            else if(movimentoAtual == 'a' && posicaoX-1 >= 0)
            {
                movimentar(posicaoX, posicaoY, posicaoX-1, posicaoY, caverna);
                posicaoY = posicaoX-1;
            }
            else if(movimentoAtual == 'd' && posicaoX+1 < 4)
            {
                movimentar(posicaoX, posicaoY, posicaoX+1, posicaoY, caverna);
                posicaoY = posicaoX+1;
            }

            caverna.setTabuleiro(posicaoX, posicaoY-1, caverna.getComponente(posicaoX, posicaoY));

            // write board
        }
    }

    public void movimentar(int xInicio, int yInicio, int xFim, int yFim, Caverna caverna)
    {
        if(caverna.getInfo(xFim, yFim) == 'W') // se for Wumpus
        {
            if(ganharBatalha())
            {
                pontuacao += 500;
                caverna.setTabuleiro(xFim, yFim, caverna.getComponente(xInicio, yInicio));
            } else {
                pontuacao -= 1000;
                // perdeu jogo
            }
        } else if(caverna.getInfo(xFim, yFim) == 'B')
        {
            pontuacao += -1000;
            // perdeu jogo
        } else if(caverna.getInfo(xFim, yFim) == 'O')
        {
            achouOuro = true;
        } else if(caverna.getInfo(xFim, yFim) == 'b'){
            status = "Brisa";
        } else if(caverna.getInfo(xFim, yFim) == 'f'){
            status = "Fedor";
        } else if(caverna.getInfo(xFim, yFim) == '-'){
        
        }
    }

    public int getPontuacao(){
        return pontuacao;
    }

    public void perdeu()
    {

    }
    
    public void ganhou()
    {

    }

    public boolean ganharBatalha()
    {
        Random rand = new Random();
        int resultado = rand.nextInt(1);
        if(resultado == 0){ // se o jogador perder
            return false;
        }
        return true; // se o jogador ganhar
    }
}
