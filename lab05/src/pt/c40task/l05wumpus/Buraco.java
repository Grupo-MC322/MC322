package pt.c40task.l05wumpus;

public class Buraco extends Componentes 
{
    protected char info = 'B';

    public Buraco(int posicaoX, int posicaoY, Caverna caverna)
    {
        // posicionamento das brisas em volta do buraco
        if(posicaoX-1 >= 0)
            caverna.addComponente(posicaoX-1, posicaoY, new Brisa());
        if(posicaoY-1 >= 0)
            caverna.addComponente(posicaoX, posicaoY-1, new Brisa());
        if(posicaoX+1 < 4)
            caverna.addComponente(posicaoX+1, posicaoY, new Brisa());
        if(posicaoY+1 < 4)
            caverna.addComponente(posicaoX, posicaoY+1, new Brisa());

    }
}