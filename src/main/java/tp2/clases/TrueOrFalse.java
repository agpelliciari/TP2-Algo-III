package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;

public class TrueOrFalse extends Type{
    
    @Override
    public void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers, int numberOfCorrectAnswers) {

        for (Player jugador : chosenAnswers.keySet()) {
            ArrayList<Answer> answers = chosenAnswers.get(jugador);

            for (Answer answer : answers) {

                if (answer.getCorrection() instanceof Correct) {
                    jugador.assignScore(answer.getCorrection(),1);
                }   
            }        
        }
    }
}
