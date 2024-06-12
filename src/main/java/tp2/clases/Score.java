package tp2.clases;

public class Score {
    private int totalScore;

    public Score(int initialScore){
        totalScore = initialScore;
    }

    public int getScore(){
        return totalScore;
    }

    public void addScore(int scoreToAdd){
        totalScore += scoreToAdd;
    }

    public void subtractScore(int scoreToSubtract){
        totalScore -= scoreToSubtract;
    }

/*    public void assignScore(Correction correction, int newScore){
        score += correction.assignScore(newScore);
    }

    public void setScore(int newScore) {
        score = newScore;
    }

 */
}
