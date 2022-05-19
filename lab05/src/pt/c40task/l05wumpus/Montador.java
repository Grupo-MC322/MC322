package pt.c40task.l05wumpus;

public class Montador
{
    private int linha, coluna;
    private int contaBuraco = 0, contaWumpus = 0, contaOuro = 0, contaHeroi = 0;

    public Montador(Toolkit tk, Caverna caverna, Heroi heroi)
    {
        String instrucoes[][] = tk.retrieveCave();
        System.out.println("=== Caverna");
        for(int l = 0; l < instrucoes.length; l++)
        {
            for(int c = 0; c < instrucoes[l].length; c++)
                System.out.print(instrucoes[l][c] + ((c < instrucoes[l].length-1) ? ", " : ""));
            System.out.println();
        }
        
        for(int i = 0; i < instrucoes.length; i++)
        {
            linha = Integer.parseInt(instrucoes[i][0])-1;
            coluna = Integer.parseInt(instrucoes[i][1])-1;
            if(linha < 0 || linha > 3 || coluna < 0 || coluna > 3)
            {
                System.out.println("Foi inserida uma posição fora do tabuleiro.");
                System.exit(0);
            }

            switch (instrucoes[i][2])
            {
                case "P":
                    caverna.addComponente(linha, coluna, heroi);
                    contaHeroi++;
                    break;
                case "W":
                    caverna.addComponente(linha, coluna, new Wumpus(linha, coluna, caverna));
                    contaWumpus++;
                    break;
                case "B":
                    caverna.addComponente(linha, coluna, new Buraco(linha, coluna, caverna));
                    contaBuraco++;
                    break;
                case "O":
                    caverna.addComponente(linha, coluna, new Ouro());
                    contaOuro++;
                    break;
            }
            
            // toda sala é iniciada com um espaço vazio que fica com a menor prioridade dentro dela
            caverna.addComponente(linha, coluna, new Vazio());
            caverna.addComponente(linha, coluna, new Visitado());
        }
        caverna.delComponente(0, 0, caverna.getComponente(0, 0, '-'));

        // o montador encerra o programa se identificar algum erro de montagem
        if(contaBuraco < 2 || contaBuraco > 3 || contaWumpus != 1 || contaOuro != 1 || contaHeroi != 1)
        {
            System.out.println("Erro na montagem do jogo, encerrando programa...");
            System.exit(0);
        }
    }
}