package tp2.clases.model.player.score.score_states;

import tp2.clases.model.player.score.Score;

public class NormalState implements ScoreState {

    private Score score;

    @Override
    public int addScore(int scoreToAdd) {
        return scoreToAdd;
    }
}
