package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrueOrFalse extends Question {

    public TrueOrFalse(int id, Content content, Mode mode, ArrayList<Choice> choices) {
        super(id, content, mode, choices);
    }

    @Override
    public void assignScore(HashMap<Player, ArrayList<Choice>> chosenAnswers) {
        Mode mode = getMode();

        for (Player player : chosenAnswers.keySet()) {
            ArrayList<Choice> choices = chosenAnswers.get(player);

            if ((hasNoIncorrectAnswers(choices)))
                mode.assignCorrectScore(player, getNumberOfCorrectAnswers(choices));
            else
                mode.assignIncorrectScore(player, (getNumberOfIncorrectAnswers(choices)));
        }
    }
}
