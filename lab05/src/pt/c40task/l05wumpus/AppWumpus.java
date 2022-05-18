package pt.c40task.l05wumpus;

public class AppWumpus {

   public static void main(String[] args) {
      AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      
      String cave[][] = tk.retrieveCave();
      System.out.println("=== Caverna");
      for (int l = 0; l < cave.length; l++) {
         for (int c = 0; c < cave[l].length; c++)
            System.out.print(cave[l][c] + ((c < cave[l].length-1) ? ", " : ""));
         System.out.println();
      }
      
      String movements = tk.retrieveMovements();
      System.out.println("=== Movimentos");
      System.out.println(movements);

      System.out.println("=== Caverna Intermediaria");
      
      Heroi heroiJogo = new Heroi();

      Caverna cavernaJogo = new Caverna();
      new Montador(cave, cavernaJogo, heroiJogo);
      Controle controleJogo = new Controle();
      controleJogo.conectaHeroi(heroiJogo);

      for(int i = 0; i < movements.length(); i++)
      {
         controleJogo.movimentosArquivo(cavernaJogo, movements, i);
         if(controleJogo.getStatus() == 'n') // se o jogador perder
         {
            tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());
            break;
         }
         else
         {
            tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());
         }
      }

      char partialCave[][] = {
         {'#', '#', 'b', '-'},
         {'#', 'b', '-', '-'},
         {'b', '-', '-', '-'},
         {'p', '-', '-', '-'}
      };
      int score = -120;
      char status = 'x'; // 'w' para venceu; 'n' para perdeu; 'x' intermediárias
      tk.writeBoard(partialCave, score, status);

      System.out.println("=== Última Caverna");
      char finalCave[][] = {
         {'#', '#', 'b', '-'},
         {'#', 'b', '#', 'f'},
         {'b', '-', '-', 'w'},
         {'#', '-', '-', '-'}
      };
      score = -1210;
      status = 'n'; // 'w' para venceu; 'n' para perdeu; 'x' intermediárias
      tk.writeBoard(finalCave, score, status);
      
      tk.stop();
   }

}

/*     -  Listinha de tarefas  -    */

// sair da caverna, e se chegar na sala (1, 1) para sair, verificar se ele está com o ouro (necessário)
// resultados da partida e saídas
// fazer modos automático e com teclado.
// a caverna vai sendo revelada à medida que o jogador se movimenta
// implementar quit() forçado/bonitinho
// cada user tem nome? tem que alterar o toolkit
// verificar condições da caverna, 2 a 3 buracos...
// os componentes têm referência para a caverna
// quem aciona a leitura do arquivo é o montador
// quando dá erros, sair do jogo
// heroi indica para a caverna quais os movimentos a serem feitos
// status como L, P, L ou n, x, w
