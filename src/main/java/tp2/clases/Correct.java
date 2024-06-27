package tp2.clases;

public class Correct extends Correction {

    public Correct() {}

    @Override
    public void assignScore(Score score, int modification) {
        score.addScore(modification);
    }

    @Override
    public int calculateScore(int modification) {
        return modification;
    }

    @Override
    public boolean isCorrect() {
        return true;
    }
}
