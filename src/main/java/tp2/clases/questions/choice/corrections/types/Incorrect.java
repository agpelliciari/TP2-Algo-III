package tp2.clases.questions.choice.corrections.types;

import tp2.clases.player.score.Score;

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
