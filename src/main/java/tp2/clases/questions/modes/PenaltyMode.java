package tp2.clases.questions.modes;

import tp2.clases.questions.choice.corrections.types.Correct;
import tp2.clases.questions.choice.corrections.types.Incorrect;
import tp2.clases.player.Player;

public class PenaltyMode implements Mode {

    @Override
    public void assignCorrectScore(Player player, int correctPints) {
        player.assignScore(new Correct(), correctPints);
    }

    @Override
    public void assignIncorrectScore(Player player, int wrongPoints) {
        player.assignScore(new Incorrect(), wrongPoints);
    }

    @Override
    public int calculateCorrectScore(Player player, int correctPints) {
        return player.calculateScore(new Correct(), correctPints);
    }

    @Override
    public int calculateIncorrectScore(Player player, int wrongPoints) {
        return player.calculateScore(new Incorrect(), wrongPoints);
    }

    @Override
    public boolean isPenaltyMode(){
        return true;
    }
}
