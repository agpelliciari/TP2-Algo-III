package tp2.clases;

import java.util.ArrayList;
import tp2.clases.exceptions.InvalidNumberOfChosenChoicesException;

public abstract class Question {

    private final int id;
    private final ArrayList<Choice> choices;
    private final Content content;
    private final Mode mode;

    public Question(int id, Content content, Mode mode, ArrayList<Choice> choices) {
        this.id = id;
        this.choices = choices;
        this.content = content;
        this.mode = mode;
    }

    public Content getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public Mode getMode() {
        return mode;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public ArrayList<Choice> getCorrectAnswers() {
        ArrayList<Choice> correctAnswers = new ArrayList<>();
        for (Choice choice: choices) {
            if (choice.getCorrection().isCorrect()) {
                correctAnswers.add(choice);
            }
        }
        return correctAnswers;
    }

    public ArrayList<Choice> createAnswers(String chosenChoice) {
        return ChoicesFactory.createAnswers(chosenChoice, this);
    }

    public int getNumberOfCorrectAnswers(ArrayList<Choice> choices) {
        return (int) choices.stream().filter(choice -> choice.getCorrection().isCorrect()).count();
    }

    public int getNumberOfIncorrectAnswers(ArrayList<Choice> choices) {
        return (int) choices.stream().filter(choice -> !choice.getCorrection().isCorrect()).count();
    }

    public boolean hasNoIncorrectAnswers(ArrayList<Choice> choices) {
        return choices.stream().allMatch(choice -> choice.getCorrection().isCorrect());
    }

    public ArrayList<Choice> assignChosenChoicesToPlayer(String chosenChoiceString) {
        String[] choiceStrings = chosenChoiceString.split(",");
        if (choiceStrings.length > choices.size() - 1) {
            throw new InvalidNumberOfChosenChoicesException();
        }
        ArrayList<Choice> chosenAnswers = new ArrayList<>();

        for (String choiceStr : choiceStrings) {
            try {
                int choiceIndex = Integer.parseInt(choiceStr.trim()) - 1;
                if (choiceIndex >= 0 && choiceIndex < choices.size()) {
                    chosenAnswers.add(choices.get(choiceIndex));
                } else {
                    throw new InvalidNumberOfChosenChoicesException();
                }
            } catch (NumberFormatException e) {
                throw new InvalidNumberOfChosenChoicesException();
            }
        }
        return chosenAnswers;
    }

    public abstract void assignScore(Player player, ArrayList<Choice> chosenAnswers);

    public abstract int calculateScore(Player player, ArrayList<Choice> chosenAnswers);
}
