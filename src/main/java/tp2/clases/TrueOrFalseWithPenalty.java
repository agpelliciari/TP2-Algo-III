package tp2.clases;

import java.util.HashMap;

public class TrueOrFalseWithPenalty extends Type{
    @Override
    public void assignScore(HashMap<Player, Answer> chosenAnswers, int numberOfCorrectAnswers) {

        for (Player player : chosenAnswers.keySet()) {
            Answer answer = chosenAnswers.get(player);

            player.assignScore(answer.getCorrection(), 1);
        }
    }

}
