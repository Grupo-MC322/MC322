package pt.c40task.l05wumpus;
import java.util.Random;

public class Controle 
{
    private String movimentos;
    private static int pontuacao = 0;
    private int posicaoX, posicaoY;
    private boolean achouOuro = false;
    private String info;
    private char status;
    private Heroi heroi;

    public void movimentosArquivo(Caverna caverna, String movimentos, int nMovimento)
    {
        this.movimentos = movimentos;
<<<<<<< HEAD
        for(int i = 0; i < movimentos.length(); i++)
=======
        status = 'x';
        if(posicaoX == 0 && posicaoY == 0 && achouOuro){
            ganhou();
        }

        char movimentoAtual = movimentos.charAt(nMovimento);

        if(movimentoAtual == 'w' && posicaoY-1 >= 0)
>>>>>>> e1b443d3df78fc5bedfe3c0e10a8e647c85df000
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

<<<<<<< HEAD
        
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
=======
        // definições da flecha
        if(heroi.getFlechaEquipada()) // se a flecha estiver equipada, significa que ela não foi utilizada e foi perdida
        {
            heroi.setFlechaEquipada(false);
        }
>>>>>>> e1b443d3df78fc5bedfe3c0e10a8e647c85df000

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
<<<<<<< HEAD

            pontuacao -= 15;

            // write board FAZER
=======
>>>>>>> e1b443d3df78fc5bedfe3c0e10a8e647c85df000
        }

        caverna.setTabuleiro(posicaoX, posicaoY-1, caverna.getComponente(posicaoX, posicaoY));
        pontuacao -= 15;
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
<<<<<<< HEAD
                    pontuacao += 500;
                    heroi.setFlechaEquipada(false); 
=======
                    if(ganharBatalha()) // se o herói ganhar a batalha
                    {
                        pontuacao += 500;
                        caverna.setTabuleiro(xFim, yFim, caverna.getComponente(xInicio, yInicio));
                        heroi.setFlechaEquipada(false); 
                    } 
                    else // se perder a batalha
                    {
                        pontuacao -= 1000;
                        perdeu();
                    }
>>>>>>> e1b443d3df78fc5bedfe3c0e10a8e647c85df000
                } 
                else 
                {
                    pontuacao -= 1000;
                    perdeu();
                }
                break;

            case 'B':
                pontuacao -= 1000;
                perdeu();
                break;

            case 'O':
                achouOuro = true;
                break;

            case 'b':
                info = "Brisa";
                break;
            
            case 'f':
                info = "Fedor";
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

    public char getStatus() {
        return status;
    }

    public int getPontuacao()
    {
        return pontuacao;
    }

    public void perdeu()
    {
        status = 'n';
        System.out.println("Voce perdeu =( !!!");
    }
    
    public void ganhou()
    {
        status = 'w';
        System.out.println("Voce ganhou =D !!!");
    }

    public void sair()
    {
        status = 'n';
        System.out.println("Volte sempre !");
    }
}
