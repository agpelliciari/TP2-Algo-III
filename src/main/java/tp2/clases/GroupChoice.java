package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupChoice extends Question{
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
    public void assignScore(HashMap<Player, ArrayList<Choice>> chosenAnswers) {

        Mode mode = getMode();

        for (Player player : chosenAnswers.keySet()) {
            ArrayList<Choice> playerAnswers = chosenAnswers.get(player);
            if(checkAnswersGroups(playerAnswers)){
                mode.assignCorrectScore(player, getNumberOfCorrectAnswers(playerAnswers));
            }
            else{
                mode.assignIncorrectScore(player, getNumberOfIncorrectAnswers(playerAnswers));
            }
        }
    }
    public boolean checkAnswersGroups(List<Choice> playerAnswers) {

        List<Choice> choices = getCorrectAnswers();
        List<Integer> filteredAnswers = filterAnswersIds(choices);

        if (playerAnswers.size() != choices.size()) {
            return false;
        }
        for (Choice playerAnswer : playerAnswers) {
            if (!filteredAnswers.contains(playerAnswer.getId())) {
                return false;
            }
        }
        return true;


    }

    public static List<Integer> filterAnswersIds(List<Choice> choices) {
        List<Integer> ids = new ArrayList<>();
        for (Choice choice : choices) {
                ids.add(choice.getId());
        }
        return ids;
    }


}