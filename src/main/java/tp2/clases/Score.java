package tp2.clases;

import tp2.clases.Correct;
import tp2.clases.Incorrect;

public class Score {
    private int score;

    public Score(int initialScore){
        score = initialScore;
    }

    public int getScore(){
        return score;
    }

    public void assignScore(Correct correction, int newScore){
        score += newScore;
    }

    public void assignScore(Incorrect correction, int newScore){
        score -= newScore;
    }
}
