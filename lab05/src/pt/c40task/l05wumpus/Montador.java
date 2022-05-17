package pt.c40task.l05wumpus;

public class Montador
{
    public Montador(String[][] instrucoes)
    {
        Caverna caverna = new Caverna();
        
        for(int l = 0; l < instrucoes.length; l++)
        {
            for(int c = 0; c < instrucoes[l].length; c++)
            {
                switch (instrucoes[l][c]) {
                    case "P":
                        Componentes heroi = new Heroi();
                        break;
                    case "W":
                        Componentes wumpus = new Wumpus();
                        break;
                }
            }
        }

        //if(string == "buraco"){
            //     Buraco buraco = new Buraco();
            //     caverna1.setTabuleiro(x, y, buraco);
            //     buraco.colocarBrisas(caverna1);
            // }
    }
}
