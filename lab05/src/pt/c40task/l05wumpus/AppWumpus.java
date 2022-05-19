package pt.c40task.l05wumpus;

import java.util.Scanner;
import java.io.File;

public class AppWumpus {

   public static void main(String[] args) {
      AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
   }
   
   public static void imprimeJogo(Caverna cavernaJogo, String nome, Controle controleJogo)
   {
      char[][] board = cavernaJogo.apresentar();

      for(int i = 0; i < 4; i++)
      {
         for(int j = 0; j < 4; j++)
         {
            System.out.printf("%s", board[i][j]);
         }
         System.out.println();
      }
      System.out.printf("Player: %s \n", nome);
      System.out.println("Score: " + controleJogo.getPontuacao());
   }

   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos)
   {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);

      String nome = "Alcebiades";
      Heroi heroiJogo = new Heroi();
      Caverna cavernaJogo = new Caverna();
      new Montador(tk, cavernaJogo, heroiJogo);
      Controle controleJogo = new Controle();
      
      heroiJogo.conectaControle(controleJogo);
      controleJogo.conectaHeroi(heroiJogo);

      String arquivoMovimentos2 = System.getProperty("user.dir")+"/src/pt/c40task/l05wumpus/"+"movements.csv";
      File file = new File(arquivoMovimentos2);
      if(arquivoMovimentos == null && file.length() == 0L)
      // entrando no modo manual
      {
         Scanner teclado = new Scanner(System.in);
         System.out.println();
         System.out.print("Digite seu nome: ");
         nome = teclado.nextLine();
         System.out.println();
         
         imprimeJogo(cavernaJogo, nome, controleJogo);

         while (controleJogo.getStatus() == 'P' )
         {
            System.out.print("Digite seu movimento: ");
            String movimentoAtual = teclado.nextLine();
            System.out.println();
            controleJogo.movimentosArquivo(cavernaJogo, movimentoAtual, 0);
            System.out.println();
            tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());
            imprimeJogo(cavernaJogo, nome, controleJogo);

            if (controleJogo.getAlerta() != null)
            {
               System.out.println("Alerta: " + controleJogo.getAlerta());
            }
            System.out.println();
         }

         teclado.close();
      }
      else
      {         
         String movements = tk.retrieveMovements();
         System.out.println("=== Movimentos");
         System.out.println(movements);

         for(int i = 0; i < movements.length(); i++)
         {
            controleJogo.movimentosArquivo(cavernaJogo, movements, i);
            if(controleJogo.getStatus() != 'P') // se o jogo acabar
            {
               tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());
               break;
            }
            else
            {
               tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());
            }
         }
      }

      System.out.println("=== Última Caverna");
      imprimeJogo(cavernaJogo, nome, controleJogo);
      switch (controleJogo.getStatus()) {
         case 'W':
            System.out.println("Voce ganhou =D !!!");
            break;
         case 'L':
            System.out.println("Voce perdeu =( !!!");
            break;
         case 'Q':
            System.out.println("Volte sempre !");
            break;
      }
      tk.writeBoard(cavernaJogo.apresentar(), controleJogo.getPontuacao(), controleJogo.getStatus());

      
      tk.stop();
   }

}
