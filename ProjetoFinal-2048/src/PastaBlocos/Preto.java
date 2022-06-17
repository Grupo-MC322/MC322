package PastaBlocos;
public class Preto implements Blocos
{
    private int numero = -2;
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

    public Blocos dobra()
    {
        return new Preto();
    }

}