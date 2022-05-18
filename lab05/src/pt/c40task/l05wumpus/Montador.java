package pt.c40task.l05wumpus;

public class Montador
{

    public Montador(String[][] instrucoes, Caverna caverna, Heroi heroi)
    {  
        for(int l = 0; l < instrucoes.length; l++)
        {
            for(int c = 0; c < instrucoes[l].length; c++)
            {
                switch (instrucoes[l][c]) {
                    case "P":
                        caverna.addComponente(l, c, heroi);
                        break;
                    case "W":
                        caverna.addComponente(l, c, new Wumpus(l, c, caverna));
                        break;
                    case "B":
                        caverna.addComponente(l, c, new Buraco(l, c, caverna));
                        break;
                    case "O":
                        caverna.addComponente(l, c, new Ouro());
                        break;
                }
                
                // toda sala é iniciada com um espaço vazio que fica com a menor prioridade dentro dela
                caverna.addComponente(l, c, new Vazio());
            }
        }
    }
}
