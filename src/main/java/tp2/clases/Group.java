package tp2.clases;

import java.util.ArrayList;

public class Group {
    private final char letter;
    private final String text;
    private final ArrayList<Choice> choices = new ArrayList<>();

    public Group(char letter, String text, int[] choicesId) {
        this.letter = letter;
        this.text = text;
        for (int choiceId : choicesId)
            this.choices.add(new Choice("", "Correcta", choiceId));
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
}
