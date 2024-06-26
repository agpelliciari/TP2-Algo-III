package tp2.clases;

public abstract class Correction {

    public abstract boolean isCorrect();

    public abstract void assignScore(Score score, int modification);

    public abstract int calculateScore(Score score, int modification);
}
