package tp2.clases;

import java.util.Objects;

public abstract class Correction {

    public abstract boolean isCorrect();

    public abstract void assignScore(Score score, int modification);
}
