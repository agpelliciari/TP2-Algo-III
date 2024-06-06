package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;

public class MultipleChoice extends Type {

    @Override
    public void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers, int numberOfCorrectAnswers) {
        int numberOfCorrectAnswersOfPlayer = 0;

        for (Player player : chosenAnswers.keySet()) {
            ArrayList<Answer> answers = chosenAnswers.get(player);

            for (Answer answer : answers) {
                if (answer.getCorrection() instanceof Correct) {
                    numberOfCorrectAnswersOfPlayer++;
                }
                if (numberOfCorrectAnswersOfPlayer == numberOfCorrectAnswers) {
                    player.setScore(player.getScore() + 1);
                } 
            }   
        }
    }
}



