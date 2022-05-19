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
      
      Heroi heroiJogo = new Heroi();

      Caverna cavernaJogo = new Caverna();
      new Montador(cave, cavernaJogo, heroiJogo);
      Controle controleJogo = new Controle();
      heroiJogo.conectaControle(controleJogo);
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

      System.out.println("=== Última Caverna");
      tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());

      
      tk.stop();
   }

}

/*     -  Listinha de tarefas  -    */

// fazer modos automático e com teclado.
// cada user tem nome? tem que alterar o toolkit
// quem aciona a leitura do arquivo é o montador
// status como L, P, L ou n, x, w
// como deve sair
