package tp2.clases;

public class Score {

    private int totalScore;
    private ScoreState state;

    public Score(int initialScore){
        totalScore = initialScore;
        state = new NormalState();
    }

    public int getScore(){
        return totalScore;
    }

    public void addScore(int scoreToAdd){
        totalScore += state.addScore(scoreToAdd);
    }

    public void subtractScore(int scoreToSubtract){
        totalScore -= scoreToSubtract;
    }

    public void cancelScore() {
        state = new CanceledState();
    }
}
