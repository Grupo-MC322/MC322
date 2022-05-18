package pt.c40task.l05wumpus;

public class Salas
{
    private Componentes[] componentes = new Componentes[5];

    public Componentes getComponente()
    {
        for(int i = 0; i < 4; i++)
        {
            if(componentes[i] != null)
            {
                return componentes[i];
            }
        }
        return componentes[4];
    }

    public Componentes getHeroi()
    {
        return componentes[1];
    }

    public Componentes getFedor()
    {
        return componentes[2];
    }

    public Componentes getVazio()
    {
        return componentes[4];
    }

    public void addComponente(Componentes componente)
    {
        switch (componente.getInfo())
        {
            case 'O', 'W', 'B':
                componentes[0] = componente;
                break;
            case 'P':
                componentes[1] = componente;
                break;
            case 'f':
                componentes[2] = componente;
                break;
            case 'b':
                componentes[3] = componente;
                break;
            case '-':
                componentes[4] = componente;
                break;
        }
    }

    public void delComponente(Componentes componente)
    {
        switch (componente.getInfo())
        {
            case 'O', 'W':
                componentes[0] = componente;
                break;
            case 'P':
                componentes[1] = componente;
                break;
            case 'f':
                componentes[2] = componente;
                break;
        }
    }
}