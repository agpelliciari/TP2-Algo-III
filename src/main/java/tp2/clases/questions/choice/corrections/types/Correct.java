package tp2.clases.questions.choice.corrections.types;

import tp2.clases.player.score.Score;

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
