package tp2.clases;

public class Incorrect extends Correction {
    public Incorrect() {
        super("incorrecta");
    }

    public int assignScore(int score){
        return score * -1;
    }

    @Override
    public boolean isCorrect() {
        return false;
    }
}
