package pt.c40task.l05wumpus;

public class Controle 
{
    private static int pontuacao = 0;
    private int linha, coluna;
    private boolean achouOuro = false;
    private String alerta;
    private char status = 'x';
    private Heroi heroi;

    public void movimentosArquivo(Caverna caverna, String movimentos, int contaMovimento)
    {
        char movimentoAtual = movimentos.charAt(contaMovimento);

        if(movimentoAtual == 'w' && linha-1 >= 0)
        {
            heroi.movimentar(linha, coluna, linha-1, coluna, caverna);
            linha--;
        } 
        else if(movimentoAtual == 's' && linha+1 < 4)
        {
            heroi.movimentar(linha, coluna, linha+1, coluna, caverna);
            linha++;
        }
        else if(movimentoAtual == 'a' && coluna-1 >= 0)
        {
            heroi.movimentar(linha, coluna, linha, coluna-1, caverna);
            coluna--;
        }
        else if(movimentoAtual == 'd' && coluna+1 < 4)
        {
            heroi.movimentar(linha, coluna, linha, coluna+1, caverna);
            coluna++;
        }
        else if (movimentoAtual == 'c' && caverna.getComponente(linha, coluna, 'O') != null)
        {
            achouOuro = true;
            caverna.delComponente(linha, coluna, caverna.getComponente(linha, coluna, 'O'));
        }
        else if (movimentoAtual == 'q')
        {
            sair();
            return;
        }
        else if(movimentoAtual == 'k')
        {
            if(heroi.getFlechaUsada())
            {
                System.out.println("A flecha já foi utilizada.");
            }
            else // equipa a flecha e muda para como sendo utilizada
            {
                heroi.setFlechaUsada(true);
                heroi.setFlechaEquipada(true);
                pontuacao -= 100;
            }
        }
        else
        {
            System.out.println("Caractere de entrada inválido.");
            return;
        }

        if(linha == 0 && coluna == 0 && achouOuro){
            pontuacao += 1000;
            ganhou();
            return;
        }
    }

    public void conectaHeroi(Heroi heroi)
    {
        this.heroi = heroi;
    }

    public char getStatus() {
        return status;
    }

    public void atualizaPontuacao(int difPontuacao)
    {
        pontuacao += difPontuacao;
    }

    public int getPontuacao()
    {
        return pontuacao;
    }

    public String getAlerta()
    {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
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