package tp2.clases.model.player;

import tp2.clases.model.player.powers.Multiplier;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.choice.corrections.types.Correction;
import tp2.clases.exceptions.UsedPowerException;
import tp2.clases.model.player.powers.Exclusivity;
import tp2.clases.model.player.powers.Nullifier;
import tp2.clases.model.player.powers.Power;
import tp2.clases.model.questions.types.Question;

import java.util.ArrayList;

public class Player {

    private ArrayList<Multiplier> multipliers = new ArrayList<>();
    private final String name;
    private final Score score;
    private Exclusivity exclusivity;
    private Nullifier nullifier;
    private int numberOfCorrectAnswers;
    private ArrayList<String> answers = new ArrayList<>();

    public Player(String name, int score) {
        this.name = name;
        this.score = new Score(score);
        multipliers.add(new Multiplier(2));
        multipliers.add(new Multiplier(3));
        this.exclusivity = new Exclusivity();
        this.nullifier = new Nullifier();
        this.numberOfCorrectAnswers = 0;
    }

    public Player(String name, Score score) {
        this.name = name;
        this.score = score;
        multipliers.add(new Multiplier(2));
        multipliers.add(new Multiplier(3));
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

    public Nullifier getNullifier() {
        return nullifier;
    }

    public Exclusivity getExclusivity() {
        return exclusivity;
    }

    public ArrayList<Choice> setAnswers(Question question, String chosenChoice) {
        ArrayList<Choice> chosenAnswers = question.createAnswers(chosenChoice);
        answers.add(chosenChoice);

        return chosenAnswers;
    }

    public void assignExclusivity(boolean isAssigned) {
        if (isAssigned) {
            exclusivity.decreaseNumber();
            exclusivity.activate();
        }
    }

    public void assignScore(Correction correction, int modification) {
        Multiplier multiplier = getActiveMultiplier();
        int factor = 1;
        if (multiplier != null)
            factor = multiplier.getFactor();
        correction.assignScore(score, modification * factor);
    }

    public void assignScore(int modification) {
        score.addScore(modification);
    }

    public int calculateScore(Correction correction, int modification) {
        Multiplier multiplier = getActiveMultiplier();
        int factor = 1;
        if (multiplier != null)
            factor = multiplier.getFactor();
        return correction.calculateScore(modification * factor);
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

    public void useMultiplier(int factor) throws UsedPowerException {
        Multiplier multiplier = getMultiplier(factor);
        if (!multiplier.isActive() && !multiplier.isUsed()) {
            multiplier.activate();
        }
        else {
            throw new UsedPowerException();
        }
    }

    public Multiplier getMultiplier(int factor) {
        for (Multiplier multiplier : multipliers)
            if (multiplier.getFactor() == factor)
                return multiplier;
        return null;
    }

    public ArrayList<Multiplier> getMultipliers() {
        return multipliers;
    }

    public Multiplier getActiveMultiplier() {
        for (Multiplier multiplier : multipliers)
            if (multiplier.isActive())
                return multiplier;
        return null;
    }

    public void useNullifier() {
        nullifier.apply(score);
    }

    public boolean nullifierIsActive() {
        return nullifier.isActive();
    }

    public void aNullifierIsActivated() {
        nullifier.cancel(score);
    }

    public void disablePowers() {
        disableMultipliers();
        disableExclusivity();
        disableNullifier();
    }

    private void disableExclusivity() {
        exclusivity.deactivate();
    }

    private void disableMultipliers() {
        for (Multiplier multiplier: multipliers) {
            multiplier.deactivate();
        }
    }

    public void disableNullifier() {
        nullifier.disable(score);
    }

    public ArrayList<Power> getPowers() {
        ArrayList<Power> powers = new ArrayList<>();

        powers.add(getMultiplier(2));
        powers.add(getMultiplier(3));

        powers.add(exclusivity);
        powers.add(nullifier);

        return powers;
    }
}
