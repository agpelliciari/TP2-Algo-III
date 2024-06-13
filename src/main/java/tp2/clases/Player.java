package tp2.clases;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private final String name;
    private final Score score;
    private Exclusivity exclusivity;
    private int numberOfCorrectAnswers;

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

    public Exclusivity getExclusivity() {
        return exclusivity;
    }

    public ArrayList<Choice> setAnswers(Question question) {
        Scanner scanner = new Scanner(System.in);
        String chosenChoice = scanner.nextLine();

        return question.assignChosenChoicesToPlayer(chosenChoice);
    }

    public ArrayList<Choice> setAnswers(Question question, String chosenChoice) {
        return question.assignChosenChoicesToPlayer(question.createAnswers(chosenChoice), this);
    }

    public void assignExclusivity(boolean bool) {
        exclusivity = new Exclusivity(bool);
    }

    public void assignScore(Correction correction, int modification) {
        correction.assignScore(score, modification);
    }

    /*public void assignScore(Incorrect correction, int wrongPoints) {
        score.subtractScore(wrongPoints);
    }*/

    public boolean equals(Player aPlayer) {
        return name.equals(aPlayer.getName());
    }

    public int getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }

    public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }
}
