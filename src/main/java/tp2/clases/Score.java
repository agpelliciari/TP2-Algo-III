package tp2.clases;

public class Score {
    private int score;

    public Score(int initialScore){
        score = initialScore;
    }

    public int getScore(){
        return score;
    }

    public void assignScore(Correction correction, int newScore){
        score += correction.assignScore(newScore);
    }

    public void setScore(int newScore) {
        score = newScore;
    }
}
