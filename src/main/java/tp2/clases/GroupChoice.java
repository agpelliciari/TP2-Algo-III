package tp2.clases;

import java.util.ArrayList;

public class GroupChoice extends Question {

    private final ArrayList<Group> groups = new ArrayList<>();

    public GroupChoice(int id, Content content, Mode mode, ArrayList<Choice> choices) {
        super(id, content, mode, choices);
    }

    public void addGroups(ArrayList<Group> groups) {
        this.groups.addAll(groups);
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public int calculateTotalScore(ArrayList<ArrayList<Choice>> groupsOfChosenAnswers) {
        int score = 0;

        for (ArrayList<Choice> chosenAnswers : groupsOfChosenAnswers) {
            boolean correctPlacement = false;

            for (Group group : groups) {
                if (group.containsSet(chosenAnswers)) {
                    correctPlacement = true;
                    break;
                }
            }

            if (correctPlacement) {
                score++;
            }
        }
        return score;
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