package tp2.clases;

public class Correct extends Correction {
    public Correct() {
        super("correcta");
    }


    @Override
    public void assignScore(Score score, int modification) {
        score.addScore(modification);
    }

    @Override
    public boolean isCorrect() {
        return true;
    }
}
