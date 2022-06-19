package PastaBlocos;
public interface IBlocos
{
    public Object getId();
    public IBlocos junta();
    public void setJuntado(boolean info);
    public boolean getJuntado();

}
