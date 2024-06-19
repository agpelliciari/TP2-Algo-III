package tp2.clases;

import java.util.ArrayList;

public class Group {

    private final char letter;
    private final String text;
    private final ArrayList<Choice> choices = new ArrayList<>();

    public Group(char letter, String text, int[] choicesId) {
        this.letter = letter;
        this.text = text;
        for (int choiceId : choicesId) {
            this.choices.add(new Choice("", "Correct", choiceId));
        }
    }

    public char getLetter() {
        return letter;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public boolean containsSet(ArrayList<Choice> chosenAnswers) {
        for (Choice chosenAnswer : chosenAnswers) {
            boolean found = false;
            for (Choice choice : choices) {
                if (choice.getId() == chosenAnswer.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }


    public void assignScore(Player player, ArrayList<Choice> chosenAnswers) {
        if (containsSet(chosenAnswers)) {
            player.addToScore(1);
        }
    }

}