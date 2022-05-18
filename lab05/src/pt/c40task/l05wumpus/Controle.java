package pt.c40task.l05wumpus;
import java.util.Random;

public class Controle 
{
    private static int pontuacao = 0;
    private int posicaoX, posicaoY;
    private boolean achouOuro = false;
    private String alerta;
    private char status;
    private Heroi heroi;

    public void movimentosArquivo(Caverna caverna, String movimentos, int nMovimento)
    {
        status = 'x';
        if(posicaoX == 0 && posicaoY == 0 && achouOuro){
            ganhou();
        }

        char movimentoAtual = movimentos.charAt(nMovimento);

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
        else if (movimentoAtual == 'c' && caverna.getComponente(posicaoX, posicaoY, 'O') != null)
        {
            achouOuro = true;
            caverna.delComponente(posicaoX, posicaoY, caverna.getComponente(posicaoX, posicaoY, 'O'));
        }
        else if (movimentoAtual == 'q')
        {
            sair();
        }
        else if(movimentoAtual == 'k')
        {
            if(heroi.getFlechaUsada())
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
        else
        {
            System.out.println("Caractere de entrada inválido.");
            return;
        }

        // definições da flecha
        if(heroi.getFlechaEquipada()) // se a flecha ainda estiver equipada, o monstro ainda não foi morto e perdeu-se a flecha
        {
            heroi.setFlechaEquipada(false);
        }
    }

    public void conectaHeroi(Heroi heroi)
    {
        this.heroi = heroi;
    }

    public void movimentar(int xInicio, int yInicio, int xFim, int yFim, Caverna caverna)
    {
        if(caverna.getComponente(xFim, yFim, 'W') != null)
        {
            if(heroi.getFlechaEquipada() && ganharBatalha())
            {
                pontuacao += 500;
                caverna.delComponente(xFim, yFim, caverna.getComponente(xFim, yFim, 'W'));

                if(xFim-1 >= 0)
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim-1, yFim, 'f'));
                if(xFim+1 < 4)
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim+1, yFim, 'f'));
                if(yFim-1 >= 0)
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim, yFim-1, 'f'));
                if(yFim-1 < 4)
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim, yFim+1, 'f'));

                heroi.setFlechaEquipada(false); 
            } 
            else 
            {
                pontuacao -= 1000;
                perdeu();
            }
        }
        if(caverna.getComponente(xFim, yFim, 'B') != null)
        {
            pontuacao -= 1000;
            perdeu();
        }
        if(caverna.getComponente(xFim, yFim, 'b') != null)
        {
            alerta = "Brisa";
        }            
        if (caverna.getComponente(xFim, yFim, 'f') != null)
        {
            alerta = "Fedor";
        }
        if (caverna.getComponente(xFim, yFim, '-') != null)
        {
            caverna.addComponente(xFim, yFim, new Visitado());
        }

        caverna.addComponente(xFim, yFim, caverna.getComponente(xInicio, yInicio, 'P'));
        caverna.delComponente(xInicio, yInicio, caverna.getComponente(xInicio, yInicio, 'P'));
        pontuacao -= 15;
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

    public String getAlerta()
    {
        return alerta;
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
