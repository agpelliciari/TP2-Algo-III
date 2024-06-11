package tp2.clases;

public class Correct extends Correction {
    public Correct() {
        super("correcta");
    }

    public int assignScore(int score){
        return score;
    }

    @Override
    public boolean isCorrect() {
        return true;
    }
}
