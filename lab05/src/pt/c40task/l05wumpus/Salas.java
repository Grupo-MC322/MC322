package pt.c40task.l05wumpus;

public class Salas
{
    private Componentes[] componentes = new Componentes[6];
    private String erro;

    public Componentes getComponente(char info)
    {
        switch (info)
        {
            case 'O', 'W', 'B':
                return componentes[0];
            case 'P':
                return componentes[1];
            case 'f':
                return componentes[2];
            case 'b':
                return componentes[3];
            case '#':
                return componentes[4];
            case '-':
                return componentes[5];
        }
        return null;
    }

    public String addComponente(Componentes componente)
    {       
        switch (componente.getInfo())
        {
            case 'O', 'W', 'B':
                if(componentes[0] == null)
                    componentes[0] = componente;
                else
                    erro = "Esse componente não pode ser inserido aqui.";
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
        return erro;
    }

    public void delComponente(Componentes componente)
    {
        switch (componente.getInfo())
        {
            case 'O', 'W':
                componentes[0] = null;
                break;
            case 'P':
                componentes[1] = null;
                break;
            case 'f':
                componentes[2] = null;
                break;
        }
    }
}