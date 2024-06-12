package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultipleChoice extends Question {

    public MultipleChoice(Content content, Mode mode, List<Answer> answers) {
        super(content, mode, answers);
    }

    @Override
    public void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers) {
        Mode mode = getMode();

        for (Player player : chosenAnswers.keySet()) {
            ArrayList<Answer> answers = chosenAnswers.get(player);

            if ((hasNoIncorrectAnswers(answers))) {
                mode.assignCorrectScore(player, getNumberOfCorrectAnswers(answers));
            }
            else {
                mode.assignIncorrectScore(player,getNumberOfCorrectAnswers(answers) - getNumberOfIncorrectAnswers(answers));
            }
        }
    }
}



