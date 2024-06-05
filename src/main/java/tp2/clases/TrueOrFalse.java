package tp2.clases;

import java.util.HashMap;

public class TrueOrFalse extends Type{
    @Override
    public void assignScore(HashMap<Player, Answer> chosenAnswers, int numberOfCorrectAnswers) {

        for (Player jugador : chosenAnswers.keySet()) {
            Answer answer = chosenAnswers.get(jugador);
            if (answer.getCorrection() instanceof Correct) {
                jugador.assignScore(answer.getCorrection(),1);
            }
        }
    }
}
