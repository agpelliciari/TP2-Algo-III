package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderedChoice extends Question {
    private int[] correctOrder;
    public OrderedChoice(int id, Content content, Mode mode, ArrayList<Choice> choices, int[] correctOrder) {
        super(id, content, mode, choices);
        this.correctOrder = correctOrder;
    }

    @Override
    public void assignScore(HashMap<Player, ArrayList<Choice>> chosenAnswers) {

        Mode mode = getMode();

        for (Player player : chosenAnswers.keySet()) {
            ArrayList<Choice> playerAnswers = chosenAnswers.get(player);
            if(checkAnswerOrder(playerAnswers)){
                mode.assignCorrectScore(player, 1);
            }
            else{
                mode.assignIncorrectScore(player, getNumberOfIncorrectAnswers(playerAnswers));
            }
        }
    }

    public boolean checkAnswerOrder(List<Choice> playerAnswers) {
        ArrayList<Choice> correctAnswers = getChoices();
        if (playerAnswers.size() != correctAnswers.size()) {
            return false;
        }
        for (int i = 0; i < playerAnswers.size(); i++) {
            if (playerAnswers.get(i).getId() != correctAnswers.get(i).getId()) {
                return false;
            }
        }
        return true;
    }

    public int[] getCorrectOrder() {
        return correctOrder;
    }
}
