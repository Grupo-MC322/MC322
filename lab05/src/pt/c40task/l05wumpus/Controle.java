package pt.c40task.l05wumpus;
import java.util.Random;

public class Controle 
{
    private String movimentos;
    private static int pontuacao = 0;
    private int posicaoX, posicaoY;
    private boolean achouOuro = false;
    private String status;
    private Heroi heroi;

    public void movimentosArquivo(Caverna caverna, String movimentos)
    {
        this.movimentos = movimentos;
        for(int i = 0; i < movimentos.length(); i++)
        {
            if(posicaoX == 0 && posicaoY == 0 && achouOuro){
                // ganhou FAZER
            }
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

            // definições da flecha
            if(heroi.getFlechaEquipada()) // se a flecha estiver equipada, significa que ela não foi utilizada e foi perdida
            {
                heroi.setFlechaEquipada(false);
            }

            if(movimentoAtual == 'k')
            {
                if(heroi.getFlechaUsada()) // se a flecha já tiver sido utilizada
                {
                    System.out.println("A flecha já foi utilizada.");
                }
                else // equipa a flecha e muda para como sendo utilizada
                {
                    heroi.setFlechaEquipada(true);
                    heroi.setFlechaUsada(true);
                    pontuacao -= 100;
                }
            }

            pontuacao -= 15;

            // write board FAZER
        }
    }

    public void conectaHeroi(Heroi heroi)
    {
        this.heroi = heroi;
    }

    public void movimentar(int xInicio, int yInicio, int xFim, int yFim, Caverna caverna)
    {
        switch(caverna.getInfo(xFim, yFim))
        {
            case 'W':
                if(heroi.getFlechaEquipada() && ganharBatalha()) // se a flecha estiver equipada
                {
                    pontuacao += 500;
                    heroi.setFlechaEquipada(false); 
                } 
                else 
                {
                    pontuacao -= 1000;
                    // perdeu jogo FAZER
                }
                break;

            case 'B':
                pontuacao -= 1000;
                // perdeu jogo FAZER
                break;

            case 'O':
                achouOuro = true;
                break;

            case 'b':
                status = "Brisa";
                break;
            
            case 'f':
                status = "Fedor";
                break;
        }

        caverna.setTabuleiro(xFim, yFim, caverna.getComponente(xInicio, yInicio));
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

    public int getPontuacao()
    {
        return pontuacao;
    }

    public void perdeu()
    {

    }
    
    public void ganhou()
    {

    }
}
