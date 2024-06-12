package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderedChoice extends Question{

    public OrderedChoice(Content content, Mode mode, List<Answer> answers) {
        super(content, mode, answers);
    }

    @Override
    public void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers) {

        Mode mode = getMode();

        for (Player player : chosenAnswers.keySet()) {
            List<Answer> playerAnswers = chosenAnswers.get(player);
            if(checkAnswerOrder(playerAnswers)){
                mode.assignCorrectScore(player, 1);
            }
            else{
                mode.assignIncorrectScore(player, getNumberOfIncorrectAnswers(playerAnswers));
            }
        }

    }

    public boolean checkAnswerOrder(List<Answer> playerAnswers) {
        List<Answer> correctAnswers = getOptions();
        if (playerAnswers.size() != correctAnswers.size()) {
            return false;
        }
        for (int i = 0; i < playerAnswers.size(); i++) {
            if (!playerAnswers.get(i).equals(correctAnswers.get(i))) {
                return false;
            }
        }
        return true;
    }




}
