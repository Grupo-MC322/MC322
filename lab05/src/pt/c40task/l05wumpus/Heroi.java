package pt.c40task.l05wumpus;
import java.util.Random;

public class Heroi extends Componentes
{
    private char info = 'P';
    private boolean flechaUsada = false;
    private boolean flechaEquipada = false;
    private Controle controle;

    public boolean getFlechaUsada()
    {
        return flechaUsada;
    }

    public void setFlechaUsada(boolean flechaUsada)
    {
        this.flechaUsada = flechaUsada;
    }

    public boolean getFlechaEquipada()
    {
        return flechaEquipada;
    }

    public void setFlechaEquipada(boolean flechaEquipada)
    {
        this.flechaEquipada = flechaEquipada;
    }

    public char getInfo()
    {
        return info;
    }

    public void movimentar(int linhaInicio, int colunaInicio, int linhaFim, int colunaFim, Caverna caverna)
    {
        if(caverna.getComponente(linhaFim, colunaFim, 'W') != null)
        {
            if(flechaEquipada && ganharBatalha())
            {
                controle.atualizaPontuacao(500);
                caverna.delComponente(linhaFim, colunaFim, caverna.getComponente(linhaFim, colunaFim, 'W'));

                if(linhaFim-1 >= 0)
                    caverna.delComponente(linhaFim, colunaFim, caverna.getComponente(linhaFim-1, colunaFim, 'f'));
                if(linhaFim+1 < 4)
                    caverna.delComponente(linhaFim, colunaFim, caverna.getComponente(linhaFim+1, colunaFim, 'f'));
                if(colunaFim-1 >= 0)
                    caverna.delComponente(linhaFim, colunaFim, caverna.getComponente(linhaFim, colunaFim-1, 'f'));
                if(colunaFim-1 < 4)
                    caverna.delComponente(linhaFim, colunaFim, caverna.getComponente(linhaFim, colunaFim+1, 'f'));

                flechaEquipada = false; 
            } 
            else 
            {
                controle.atualizaPontuacao(-1000);
                controle.perdeu();
            }
        } 
        else if(caverna.getComponente(linhaFim, colunaFim, 'B') != null)
        {
            controle.atualizaPontuacao(-1000);
            controle.perdeu();
        }
    
        if(caverna.getComponente(linhaFim, colunaFim, 'b') != null)
        {
            controle.setAlerta("Brisa");
        }            
        if (caverna.getComponente(linhaFim, colunaFim, 'f') != null)
        {
            controle.setAlerta("Fedor");
        }

        if(caverna.getComponente(linhaFim, colunaFim, '-') != null)
            caverna.delComponente(linhaFim, colunaFim, caverna.getComponente(linhaFim, colunaFim, '-'));
        caverna.addComponente(linhaFim, colunaFim, caverna.getComponente(linhaInicio, colunaInicio, 'P'));
        caverna.delComponente(linhaInicio, colunaInicio, caverna.getComponente(linhaInicio, colunaInicio, 'P'));

        controle.atualizaPontuacao(-15);
    }

    public boolean ganharBatalha()
    {
        Random random = new Random();
        boolean resultado = random.nextBoolean();
        return resultado;
    }

    public void conectaControle(Controle controle)
    {
        this.controle = controle;
    }
}