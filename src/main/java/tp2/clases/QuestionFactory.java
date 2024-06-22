package tp2.clases;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionFactory {
    private ArrayList<ModeEntry> modes;

    public QuestionFactory() {
        modes = new ArrayList<>();
        modes.add(new ModeEntry("Simple", new ClassicMode()));
        modes.add(new ModeEntry("Penalidad", new PenaltyMode()));
        modes.add(new ModeEntry("Puntaje Parcial", new PartialMode()));
    }

    private Mode defineMode(String type) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(type.split(" ")));
        String searchedMode = list.get(list.size() - 1);
        if (list.size() < 2) {
            return findModeByName("Simple");
        }
        return findModeByName(searchedMode);
    }

    private Mode findModeByName(String name) {
        for (ModeEntry modeEntry : modes) {
            if (modeEntry.getName().equals(name)) {
                return modeEntry.getMode();
            }
        }
        return new ClassicMode();
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

    private class ModeEntry {
        private String name;
        private Mode mode;

        public ModeEntry(String name, Mode mode) {
            this.name = name;
            this.mode = mode;
        }

        public String getName() {
            return name;
        }

        public Mode getMode() {
            return mode;
        }
    }
}