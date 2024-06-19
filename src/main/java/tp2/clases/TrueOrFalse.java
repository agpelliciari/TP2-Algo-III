package tp2.clases;

import java.util.ArrayList;

public class TrueOrFalse extends Question {

    public TrueOrFalse(int id, Content content, Mode mode, ArrayList<Choice> choices) {
        super(id, content, mode, choices);
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
}