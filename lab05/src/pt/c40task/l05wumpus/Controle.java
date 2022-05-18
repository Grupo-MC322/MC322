package pt.c40task.l05wumpus;
import java.util.Random;

public class Controle 
{
    private static int pontuacao = 0;
    private int posicaoX, posicaoY;
    private boolean achouOuro = false;
    private String info;
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
        else if (movimentoAtual == 'c' && caverna.getComponente(posicaoX, posicaoY).getInfo() == 'O')
        {
            achouOuro = true;
            caverna.delComponente(posicaoX, posicaoY, caverna.getComponente(posicaoX, posicaoY));
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

        // definições da flecha
        if(heroi.getFlechaEquipada()) // se a flecha ainda estiver equipada, o monstro ainda não foi morto e perdeu-se a flecha
        {
            heroi.setFlechaEquipada(false);
        }

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
                if(heroi.getFlechaEquipada() && ganharBatalha())
                {
                    pontuacao += 500;
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim, yFim));

                    if(xFim-1 >= 0)
                        caverna.delComponente(xFim, yFim, caverna.getFedor(xFim-1, yFim));
                    if(xFim+1 < 4)
                        caverna.delComponente(xFim, yFim, caverna.getFedor(xFim+1, yFim));
                    if(yFim-1 >= 0)
                        caverna.delComponente(xFim, yFim, caverna.getFedor(xFim, yFim-1));
                    if(yFim-1 < 4)
                        caverna.delComponente(xFim, yFim, caverna.getFedor(xFim, yFim+1));

                    heroi.setFlechaEquipada(false); 
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

            case 'b':
                info = "Brisa";
                break;
            
            case 'f':
                info = "Fedor";
                break;
            case '-':
                // tem que mudar pra #!!
                break;
        }

        caverna.addComponente(xFim, yFim, caverna.getHeroi(xInicio, yInicio));
        caverna.delComponente(xInicio, yInicio, caverna.getHeroi(xInicio, yInicio));
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
