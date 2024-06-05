package tp2.clases;
import java.util.HashMap;

public class MultipleChoice extends Type {
    @Override
    public void assignScore(HashMap<Player, Answer> chosenAnswers, int numberOfCorrectAnswers) {

        int numberOfCorrectAnswersOfPlayer = 0;
        for (Player player : chosenAnswers.keySet()) {
            Answer answer = chosenAnswers.get(player);
            if (answer.getCorrection() instanceof Correct) {
                numberOfCorrectAnswersOfPlayer++;

            }
            if (numberOfCorrectAnswersOfPlayer == numberOfCorrectAnswers) {
                jugador.setPuntaje(jugador.getPuntaje() + 1);
            }
        }

    }
}



