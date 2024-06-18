package tp2.clases;

public class NormalState implements ScoreState {
    private Score score;

    @Override
    public int addScore(int scoreToAdd) {
        return scoreToAdd;
    }
}
