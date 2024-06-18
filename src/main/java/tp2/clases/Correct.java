package tp2.clases;

public class Correct extends Correction {
    @Override
    public void assignScore(Score score, int modification) {
        score.addScore(modification);
    }

    @Override
    public boolean isCorrect() {
        return true;
    }
}
