package pt.c40task.l05wumpus;

public class Salas
{
    private Componentes[] componentes = new Componentes[6];

    public Componentes getComponenteSuper()
    {
        for(int i = 0; i < 5; i++)
        {
            if(componentes[i] != null)
            {
                return componentes[i];
            }
        }
        return componentes[5];
    }

    public Componentes getComponente(char info)
    {
        switch (info)
        {
            case 'O', 'W', 'B':
                return componentes[0];
                break;
            case 'P':
                return componentes[1];
                break;
            case 'f':
                return componentes[2];
                break;
            case 'b':
                return componentes[3];
                break;
            case '#':
                return componentes[4];
                break;
            case '-':
                return componentes[5];
                break;
            default:
                return null;
                break;
        }
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
            case '#':
                componentes[4] = componente;
                break;
            case '-':
                componentes[5] = componente;
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