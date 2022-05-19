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

    public void movimentar(int xInicio, int yInicio, int xFim, int yFim, Caverna caverna)
    {
        if(caverna.getComponente(xFim, yFim, 'W') != null)
        {
            if(flechaEquipada && ganharBatalha())
            {
                controle.atualizaPontuacao(500);
                caverna.delComponente(xFim, yFim, caverna.getComponente(xFim, yFim, 'W'));

                if(xFim-1 >= 0)
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim-1, yFim, 'f'));
                if(xFim+1 < 4)
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim+1, yFim, 'f'));
                if(yFim-1 >= 0)
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim, yFim-1, 'f'));
                if(yFim-1 < 4)
                    caverna.delComponente(xFim, yFim, caverna.getComponente(xFim, yFim+1, 'f'));

                flechaEquipada = false; 
            } 
            else 
            {
                controle.atualizaPontuacao(-1000);
                controle.perdeu();
            }
        } 
        else if(caverna.getComponente(xFim, yFim, 'B') != null)
        {
            controle.atualizaPontuacao(-1000);
            controle.perdeu();
        }
    
        if(caverna.getComponente(xFim, yFim, 'b') != null)
        {
            controle.setAlerta("Brisa");
        }            
        if (caverna.getComponente(xFim, yFim, 'f') != null)
        {
            controle.setAlerta("Fedor");
        }

        if(caverna.getComponente(xFim, yFim, '-') != null)
            caverna.delComponente(xFim, yFim, caverna.getComponente(xFim, yFim, '-'));
        caverna.addComponente(xFim, yFim, caverna.getComponente(xInicio, yInicio, 'P'));
        caverna.delComponente(xInicio, yInicio, caverna.getComponente(xInicio, yInicio, 'P'));

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