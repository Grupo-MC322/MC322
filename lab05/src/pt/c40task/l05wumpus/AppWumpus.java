package pt.c40task.l05wumpus;

import java.util.Scanner;

public class AppWumpus {

   public static void main(String[] args) {
      AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos)
   {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);

      Heroi heroiJogo = new Heroi();
      Caverna cavernaJogo = new Caverna();
      new Montador(tk, cavernaJogo, heroiJogo);
      Controle controleJogo = new Controle();
      
      heroiJogo.conectaControle(controleJogo);
      controleJogo.conectaHeroi(heroiJogo);

      if(arquivoMovimentos == null)
      {
         
      }

      Scanner teclado = new Scanner(System.in);
      System.out.println();
      String nome = teclado.nextLine();
      teclado.close();
      
      String movements = tk.retrieveMovements();
      System.out.println("=== Movimentos");
      System.out.println(movements);
      
      if(movements.length() == 0)
      {

      }
      for(int i = 0; i < movements.length(); i++)
      {
         controleJogo.movimentosArquivo(cavernaJogo, movements, i);
         if(controleJogo.getStatus() == 'L') // se o jogador perder
         {
            tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());
            break;
         }
         else
         {
            tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());
         }
      }

      char[][] board = cavernaJogo.apresentar();
      System.out.println("=== Última Caverna");
      for(int i = 0; i < 4; i++)
      {
         for(int j = 0; j < 4; j++)
         {
            System.out.println(board[i][j]);
         }
         System.out.println();
      }
      System.out.println("Player: " + nome);
      System.out.println("Score: " + controleJogo.getPontuacao());
      switch (controleJogo.getStatus()) {
         case 'W':
            System.out.println("Voce ganhou =D !!!");
            break;
         case 'L':
            System.out.println("Voce perdeu =( !!!");
            break;
      }
      tk.writeBoard(board, controleJogo.getPontuacao(), controleJogo.getStatus());

      
      tk.stop();
   }

}

/*     -  Listinha de tarefas  -    */

// fazer modos automático e com teclado.
// quem aciona a leitura do arquivo é o montador
// status como W, P, L ou n, x, w
// como deve sair
// comentar código
