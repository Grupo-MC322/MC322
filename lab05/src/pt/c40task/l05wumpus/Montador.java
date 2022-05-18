package pt.c40task.l05wumpus;

public class Montador
{
    private int posicaoX, posicaoY;

    public Montador(String[][] instrucoes, Caverna caverna, Heroi heroi)
    { 
        
        for(int i = 0; i < instrucoes.length; i++)
        {
            posicaoX = Integer.parseInt(instrucoes[i][0]);
            posicaoY = Integer.parseInt(instrucoes[i][1]);

            switch (instrucoes[i][2])
            {
                case "P":
                    caverna.addComponente(posicaoX, posicaoY, heroi);
                    break;
                case "W":
                    caverna.addComponente(posicaoX, posicaoY, new Wumpus(posicaoX, posicaoY, caverna));
                    break;
                case "B":
                    caverna.addComponente(posicaoX, posicaoY, new Buraco(posicaoX, posicaoY, caverna));
                    break;
                case "O":
                    caverna.addComponente(posicaoX, posicaoY, new Ouro());
                    break;
            }
            
            // toda sala é iniciada com um espaço vazio que fica com a menor prioridade dentro dela
            caverna.addComponente(posicaoX, posicaoY, new Vazio());
        }
    }
}
