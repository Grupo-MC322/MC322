package PastaBlocos;
public class Preto implements Blocos
{
    private int numero = 0;
    private int vida = 3;

    public int getNumero()
    {
        return numero;
    }
    public void diminuiVida()
    {
        vida--;
    }
    public int getvida()
    {
        return vida;
    }

}