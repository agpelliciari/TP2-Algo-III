package tp2.clases;

public class Incorrect extends Correction {
    public Incorrect() {
        super("incorrecta");
    }

    @Override
    public void assignScore(Score score, int modification) {
        score.subtractScore(modification);
    }

    @Override
    public boolean isCorrect() {
        return false;
    }
}
