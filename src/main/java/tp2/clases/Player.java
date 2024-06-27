package tp2.clases;

import tp2.clases.exceptions.UsedPowerException;

import java.util.ArrayList;

public class Player {

    private ArrayList<Multiplicator> multiplicators = new ArrayList<>();
    private final String name;
    private final Score score;
    private Exclusivity exclusivity;
    private Nullifier nullifier;
    private int numberOfCorrectAnswers;
    private ArrayList<String> answers = new ArrayList<>();
    private Boolean answeredCorrectly = false;

    public Player(String name, int score) {
        this.name = name;
        this.score = new Score(score);
        multiplicators.add(new Multiplicator(2));
        multiplicators.add(new Multiplicator(3));
        this.exclusivity = new Exclusivity();
        this.nullifier = new Nullifier();
        this.numberOfCorrectAnswers = 0;
    }

    public Player(String name, Score score) {
        this.name = name;
        this.score = score;
        multiplicators.add(new Multiplicator(2));
        multiplicators.add(new Multiplicator(3));
        this.exclusivity = new Exclusivity();
        this.nullifier = new Nullifier();
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
        exclusivity.activate();
    }

    public void assignScore(Correction correction, int modification) {

        Multiplicator multiplicator = getActiveMultiplicator();
        int factor = 1;
        if(multiplicator != null) {
            factor = multiplicator.getFactor();
            multiplicator.deactivate();
        }
        correction.assignScore(score, modification * factor);
    }

    public void assignScore(int modification) {
        score.addScore(modification);
    }

    public int calculateScore(Correction correction, int modification) {
        Multiplicator multiplicator = getActiveMultiplicator();
        int factor = 1;
        if(multiplicator != null) {
            factor = multiplicator.getFactor();
            multiplicator.deactivate();
        }
        return correction.calculateScore(score, modification * factor);
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
        else {
            throw new UsedPowerException();
        }
    }

    public Multiplicator getMultiplicator(int factor) {
        for (Multiplicator multiplicator : multiplicators) {
            if (multiplicator.getFactor() == factor) {
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

    public void useNullifier() {
        nullifier.apply(score);
    }

    public boolean nullifierIsActive() {
        return nullifier.isActive();
    }

    public void aNullifierisActivated() {
        nullifier.cancel(score);
    }

    public void disableNullifier() {
        nullifier.disable(score);
    }

    public boolean answeredCorrectly() {
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly() {
        answeredCorrectly = true;
    }

    public ArrayList<Power> getPowers() {
        ArrayList<Power> powers = new ArrayList<>();
        powers.add(getMultiplicator(2));
        powers.add(getMultiplicator(3));
        powers.add(exclusivity);
        powers.add(nullifier);

        return powers;
    }
}
