package pt.c40task.l05wumpus;

public class Wumpus extends Componentes
{
    protected char info = 'W';

    public Wumpus(int posicaoX, int posicaoY, Caverna caverna)
    {
        // posicionamento dos fedores em volta do wumpus
        if(posicaoX-1 >= 0)
            caverna.addComponente(posicaoX-1, posicaoY, new Fedor());
        if(posicaoY-1 >= 0)
            caverna.addComponente(posicaoX, posicaoY-1, new Fedor());
        if(posicaoX+1 < 4)
            caverna.addComponente(posicaoX+1, posicaoY, new Fedor());
        if(posicaoY+1 < 4)
            caverna.addComponente(posicaoX, posicaoY+1, new Fedor());

    }
}
