package tp2.clases;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Player {
  
    private List<Multiplicator> multiplicators;
    private final String name;
    private final Score score;
    private Exclusivity exclusivity;
    private int numberOfCorrectAnswers;

    public Player(String name, int initialScore) {
        this.name = name;
        this.score = new Score(initialScore);
        this.multiplicators = new ArrayList<>();
        multiplicators.add(new Multiplicator(2));
        multiplicators.add(new Multiplicator(3));
        this.exclusivity = new Exclusivity(false);
        this.numberOfCorrectAnswers = 0;
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
        if (bool)
            exclusivity.decreaseNumber();
        exclusivity.setBool(bool);
    }

    public void assignScore(Correction correction, int modification) {

        Multiplicator multiplicator = getActiveMultiplicator();
        if(multiplicator != null){
            int factor = multiplicator.getFactor();
            correction.assignScore(score, modification*factor);
            multiplicator.deactivate();
        }
        else{
            correction.assignScore(score, modification);
        }


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

    public void useMultiplicator(int factor) {
        Multiplicator multiplicator = getMultiplicator(factor);
        if (!multiplicator.isActive()) {
            multiplicator.activate();
        }
    }

    public Multiplicator getMultiplicator(int factor) {
        for (Multiplicator multiplicator : multiplicators) {
            if (multiplicator.getFactor() == factor){
                return multiplicator;
            }
        }
        return null;
    }

    public Multiplicator getActiveMultiplicator() {
        for (Multiplicator multiplicator : multiplicators) {
            if (multiplicator.isActive()) {
                return multiplicator;
            }
        }
        return null;
    }
}
