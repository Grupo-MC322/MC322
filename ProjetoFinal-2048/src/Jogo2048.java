import PastaBlocos.*;
import java.util.Random;

public class Jogo2048
{
    public static void main(String[] args)
    {
        // perguntar tamanhoX e tamanhoY
        int tamanhoX = 4, tamanhoY = 4;

        Tabuleiro tabuleiro = new Tabuleiro(tamanhoX, tamanhoY);
        Controle controle = new Controle();
        spawnBloco(tabuleiro, controle);
        
        // movimentar
        // apresentar tabuleiro com for na direção oposta ao sentido do movimento

    }

    public static void spawnBloco(Tabuleiro tabuleiro, Controle controle)
    {
        IBlocos blocoGerado;
        Random random = new Random();
        int coordX = random.nextInt(tabuleiro.getTamanhoX());
		int coordY = random.nextInt(tabuleiro.getTamanhoY());

		if (tabuleiro.getId(coordX, coordY) == 0)
		{
			int index = random.nextInt(100);
            if(index < 20)
            {
                blocoGerado = new BlocoGenerico(1);
            }
            else if (index < 60)
            {
                blocoGerado = new BlocoGenerico(2);
            }
            else if (index < 80)
            {
                blocoGerado = new BlocoGenerico(4);
            }
            else if (index < 85 && controle.getBombaAtiva() == false)
            {
                controle.setBombaAtiva();
                blocoGerado = new Bomba();
            }
            else if (index < 90 && controle.getPretoAtivo() == false)
            {
                controle.setPretoAtivo();
                blocoGerado = new Preto();
            }
            else if (index < 95)
            {
                blocoGerado = new Deleta();
            }
            else if (index < 100)
            {
                blocoGerado = new Dobro();
            }
            
            tabuleiro.setBloco(coordX, coordY, blocoGerado);
		}
		else
		{
			spawnBloco();
		}
    }
}


// set e get juntado