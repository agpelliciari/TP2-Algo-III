package tp2.clases;

import java.util.ArrayList;

public class Player {

    private ArrayList<Multiplicator> multiplicators = new ArrayList<>();
    private final String name;
    private final Score score;
    private Exclusivity exclusivity;
    private int numberOfCorrectAnswers;
    private ArrayList<String> answers = new ArrayList<>();

    public Player(String name, int initialScore) {
        this.name = name;
        this.score = new Score(initialScore);
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

    public ArrayList<Choice> setAnswers(Question question, String chosenChoice) {
        ArrayList<Choice> chosenAnswers = question.createAnswers(chosenChoice);

        answers.add(chosenChoice);

        return chosenAnswers;
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
            correction.assignScore(score, modification * factor);
            multiplicator.deactivate();
        }
        else{
            correction.assignScore(score, modification);
        }
    }

    public void addToScore(int number) {
        score.addScore(number);
    }

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
        if (!multiplicator.isActive() && !multiplicator.isUsed()) {
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
