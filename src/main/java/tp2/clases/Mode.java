package tp2.clases;

public interface Mode {

    public abstract void assignCorrectScore(Player player, int correctPoints);

    public abstract void assignIncorrectScore(Player player, int wrongPoints);
}
