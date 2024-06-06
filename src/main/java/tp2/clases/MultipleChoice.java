package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;

public class MultipleChoice extends Type {

    @Override
    public void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers, int numberOfCorrectAnswers) {

        for (Player player : chosenAnswers.keySet()) {
            ArrayList<Answer> answers = chosenAnswers.get(player);

            boolean hasIncorrectAnswer = false;
            int numberOfCorrectAnswersOfPlayer = 0;

            for (Answer answer : answers) {
                if (answer.getCorrection() instanceof Incorrect) {
                    hasIncorrectAnswer = true;
                }
                else {
                    numberOfCorrectAnswersOfPlayer++;
                }
            }

            if (!hasIncorrectAnswer) {
                if (numberOfCorrectAnswersOfPlayer == numberOfCorrectAnswers) {
                    player.setScore(player.getScore() + 1);
                }
            }
        }
    }
}



