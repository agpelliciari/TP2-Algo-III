package tp2.clases;

import tp2.clases.player.Player;
import tp2.clases.questions.choice.Choice;

import java.util.ArrayList;

public class Group {

    private final char letter;
    private final String text;
    private final ArrayList<Choice> choices = new ArrayList<>();

    public Group(char letter, String text, int[] intChoices) {
        this.letter = letter;
        this.text = text;
        for (int intChoice : intChoices) {
            choices.add(new Choice("", "correcta", intChoice));
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
        if (choices.size() != chosenAnswers.size()) {
            return false;
        }

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
}