package tp2.clases;

public class Correct extends Correction {

    public Correct() {}

    @Override
    public void assignScore(Score score, int modification) {
        score.addScore(modification);
    }

    @Override
    public int calculateScore(Score score, int modification) {
        return score.getScore() + modification;
    }

    @Override
    public boolean isCorrect() {
        return true;
    }
}
