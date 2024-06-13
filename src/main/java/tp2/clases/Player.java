package tp2.clases;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private Score score;

    public Player(String name, int initialScore) {
        this.name = name;
        this.score = new Score(initialScore);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score.getScore();
    }

    public ArrayList<Choice> Choice(Question question) {

        Scanner scanner = new Scanner(System.in);
        String chosenOption = scanner.nextLine();

        return question.choiceOption(chosenOption);
    }

    public ArrayList<Choice> Choice(Question question, String chosenOption) {
        return question.choiceOption(question.createAnswers(chosenOption), this);
    }

    public void assignScore(Correction correction, int modification) {
        correction.assignScore(score, modification);
    }

    /*public void assignScore(Incorrect correction, int wrongPoints) {
        score.subtractScore(wrongPoints);
    }*/

    public boolean equals(Player aPlayer) {
        return name == aPlayer.getName();
    }
}
