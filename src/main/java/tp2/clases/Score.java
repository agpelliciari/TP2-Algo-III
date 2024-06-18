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
        //totalScore += scoreToAdd;
        totalScore += state.addScore(scoreToAdd);
    }

    public void subtractScore(int scoreToSubtract){
        totalScore -= scoreToSubtract;
    }

    public void cancelScore() {
        state = new CanceledState();
    }

/*    public void assignScore(Correction correction, int newScore){
        score += correction.assignScore(newScore);
    }

    public void setScore(int newScore) {
        score = newScore;
    }

 */
}
