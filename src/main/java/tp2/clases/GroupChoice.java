package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupChoice extends Question{


    public GroupChoice(Content content, Mode mode, List<Answer> answers) {
        super(content, mode, answers);
    }
    @Override
    public void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers) {

        Mode mode = getMode();

        for (Player player : chosenAnswers.keySet()) {
            List<Answer> playerAnswers = chosenAnswers.get(player);
            if(checkAnswersGroups(playerAnswers)){
                mode.assignCorrectScore(player, getNumberOfCorrectAnswers(playerAnswers));
            }
            else{
                mode.assignIncorrectScore(player, getNumberOfIncorrectAnswers(playerAnswers));
            }
        }
    }
    public boolean checkAnswersGroups(List<Answer> playerAnswers) {

        List<Answer> answers = getCorrectAnswers();
        List<Character> filteredAnswers = filterAnswersIds(answers);

        if (playerAnswers.size() != answers.size()) {
            return false;
        }
        for (Answer playerAnswer : playerAnswers) {
            if (!filteredAnswers.contains(playerAnswer.getId())) {
                return false;
            }
        }
        return true;


    }

    public static List<Character> filterAnswersIds(List<Answer> answers) {
        List<Character> ids = new ArrayList<>();
        for (Answer answer : answers) {
                ids.add(answer.getId());
        }
        return ids;
    }


}