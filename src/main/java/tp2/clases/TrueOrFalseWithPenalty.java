package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;

public class TrueOrFalseWithPenalty extends Type{
    @Override
    public void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers, int numberOfCorrectAnswers) {

        for (Player player : chosenAnswers.keySet()) {
            ArrayList<Answer> answers = chosenAnswers.get(player);

            for (Answer answer : answers) {
                player.assignScore(answer.getCorrection(), 1);
            }    
        }
    }
}
