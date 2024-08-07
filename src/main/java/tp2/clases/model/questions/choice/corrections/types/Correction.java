package tp2.clases.model.questions.choice.corrections.types;

import tp2.clases.model.player.score.Score;

public abstract class Correction {

    public abstract boolean isCorrect();

    public abstract void assignScore(Score score, int modification);

    public abstract int calculateScore(int modification);
}
