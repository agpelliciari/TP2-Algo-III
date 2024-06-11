package tp2.clases;

import java.util.ArrayList;

public class Group {
    private final char letter;
    private final String text;
    private final ArrayList<Option> options = new ArrayList<>();

    public Group(char letter, String text, int[] optionNumbers) {
        this.letter = letter;
        this.text = text;
        for (int optionNumber : optionNumbers)
            this.options.add(new Option(optionNumber, "", new Correct()));
    }

    public char getLetter() {
        return letter;
    }

    public String getText() {
        return text;
    }
}
