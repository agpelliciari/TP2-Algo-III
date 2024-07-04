package tp2.clases.model.questions.types;

import tp2.clases.model.player.Player;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.modes.Mode;

import java.util.ArrayList;

public class GroupChoice extends Question {

    public static class Group {
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

        public char getLetter() {
            return letter;
        }
    }

    private final ArrayList<Group> groups = new ArrayList<>();

    public GroupChoice(int id, Content content, Mode mode, ArrayList<Choice> choices) {
        super(id, content, mode, choices);
    }

    public static Group createGroup(char letter, String text, int[] intChoices) {
        return new Group(letter, text, intChoices);
    }

    public void addGroups(ArrayList<Group> groups) {
        this.groups.addAll(groups);
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public int calculateTotalScore(ArrayList<ArrayList<Choice>> groupsOfChosenAnswers) {
        for (int i = 0; i < groupsOfChosenAnswers.size(); i++) {
            if (!groups.get(i).containsSet(groupsOfChosenAnswers.get(i))) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public void assignScore(Player player, ArrayList<Choice> chosenAnswers) {
        Mode mode = getMode();

        if (hasNoIncorrectAnswers(chosenAnswers)) {
            mode.assignCorrectScore(player, getNumberOfCorrectAnswers(chosenAnswers));
        } else {
            mode.assignIncorrectScore(player, getNumberOfIncorrectAnswers(chosenAnswers));
        }
    }

    @Override
    public int calculateScore(Player player, ArrayList<Choice> chosenAnswers) {
        Mode mode = getMode();

        if (hasNoIncorrectAnswers(chosenAnswers)) {
            return mode.calculateCorrectScore(player, getNumberOfCorrectAnswers(chosenAnswers));
        } else {
            return mode.calculateIncorrectScore(player, getNumberOfIncorrectAnswers(chosenAnswers));
        }
    }
}