package tp2.clases;

public class Incorrect extends Correction {
    public Incorrect() {}

    @Override
    public void assignScore(Score score, int modification) {
        score.subtractScore(modification);
    }

    @Override
    public int calculateScore(int modification) {
        return -modification;
    }

    @Override
    public boolean isCorrect() {
        return false;
    }
}
