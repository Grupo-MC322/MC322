import PastaBlocos.*;
import java.util.Random;

public class Jogo2048
{
    public static void main(String[] args)
    {
        // perguntar tamanhoX e tamanhoY
        int tamanhoX = 4, tamanhoY = 4;

        Tabuleiro tabuleiro = new Tabuleiro(tamanhoX, tamanhoY);
        brotaBloco();
        
        // movimentar
        // apresentar tabuleiro com for na direção oposta ao sentido do movimento

    }

    public static void brotaBloco()
    {
        Random random = new Random();
        int coordX = random.nextInt(tabuleiro.getTamanhoX());
		int coordY = random.nextInt(tabuleiro.getTamanhoY());

		if (tabuleiro.getId(coordX, coordY) == 0)
		{
			int index = random.nextInt(100);
            Blocos blocoGerado;
            if(index < 20)
            {
                blocoGerado = new Bloco1();
            }
            else if (index < 50)
            {
                blocoGerado = new Bloco2();
            }
            else if (index < 60)
            {
                blocoGerado = new Bloco4();
            }
            else if (index < 65)
            {
                blocoGerado = new Bloco8();
            }
            else if (index < 70)
            {
                blocoGerado = new Bloco16();
            }
            else if (index < 75)
            {
                blocoGerado = new Bomba();
            }
            else if (index < 80)
            {
                blocoGerado = new Deleta();
            }
            else if (index < 90)
            {
                blocoGerado = new Dobro();
            }
            else if (index < 100)
            {
                blocoGerado = new Preto();
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