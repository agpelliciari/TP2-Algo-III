package tp2.clases;

import tp2.clases.Question;
import tp2.clases.Answer;
import tp2.clases.Correction;
import tp2.clases.Score;

class Player {
    private String name;
    private Score score;

    public Player(String name, int initialScore){
        this.name = name;
        this.score = new Score(initialScore);
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score.getScore();
    }

    public Answer answer(Question question){
        return question.choiceOption();
    }

    public void assignScore(Correction correction, int newScore){
        score.assignScore(correction, newScore);
    }

    public boolean equals(Player aPlayer){
        return name == aPlayer.getName();
    }
}
