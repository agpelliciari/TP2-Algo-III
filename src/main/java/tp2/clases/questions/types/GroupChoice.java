package tp2.clases.questions.types;

import tp2.clases.*;
import tp2.clases.player.Player;
import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.Content;
import tp2.clases.questions.modes.Mode;

import java.util.ArrayList;

public class GroupChoice extends Question {
    public final ArrayList<Group> groups = new ArrayList<>();

    public GroupChoice(int id, Content content, Mode mode, ArrayList<Choice> choices) {
        super(id, content, mode, choices);
    }

    public void addGroups(ArrayList<Group> groups) {
        this.groups.addAll(groups);
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    @Override
    public void assignScore(Player player, ArrayList<Choice> chosenAnswers) {}

    @Override
    public int calculateScore(Player player, ArrayList<Choice> chosenAnswers) {
        return 0;
    }
}
