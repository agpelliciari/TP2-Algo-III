package tp2.clases;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class QuestionFactory {
    private HashMap<String, Mode> modes;

    public QuestionFactory() {
        modes = new HashMap<String, Mode>();
        modes.put("Simple", new ClassicMode());
        modes.put("Penalidad", new PenaltyMode());
        modes.put("Puntaje Parcial", new PartialMode());
    }

    private Mode defineMode(String type) {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(type.split(" ")));
        String searchedMode = list.get(list.size()-1);
        if (list.size() < 2) {
            return modes.get("Simple");
        }
        return modes.get(searchedMode);
    }

    public TrueOrFalse createTrueFalse(int id, String theme, String prompt, String type, ArrayList<Choice> choices, String answerText) {
        Content content = new Content(theme, prompt, answerText);
        return new TrueOrFalse(id, content, defineMode(type), choices);
    }

    public MultipleChoice createMultipleChoice(int id, String theme, String prompt, String type, ArrayList<Choice> choices, String answerText) {
        Content content = new Content(theme, prompt, answerText);
        return new MultipleChoice(id, content, defineMode(type), choices);
    }

    public OrderedChoice createOrderedChoice(int id, String theme, String prompt, String type, ArrayList<Choice> choices, String answerText, int[] correctOrder) {
        Content content = new Content(theme, prompt, answerText);
        return new OrderedChoice(id, content, defineMode(type), choices, correctOrder);
    }

    public GroupChoice createGroupChoice(int id, String theme, String prompt, String type, ArrayList<Choice> choices, String answerText) {
        Content content = new Content(theme, prompt, answerText);
        return new GroupChoice(id, content, defineMode(type), choices);
    }
}
