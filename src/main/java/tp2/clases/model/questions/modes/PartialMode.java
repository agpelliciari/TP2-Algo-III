package tp2.clases.model.questions.modes;

import tp2.clases.model.questions.choice.corrections.types.Incorrect;
import tp2.clases.model.questions.choice.corrections.types.Correct;
import tp2.clases.model.player.Player;

public class PartialMode implements Mode {

    @Override
    public void assignCorrectScore(Player player, int correctPoints) {
        player.assignScore(new Correct(), correctPoints);
    }

    @Override
    public void assignIncorrectScore(Player player, int wrongPoints) {
        player.assignScore(new Incorrect(), 0);
    }

    @Override
    public int calculateCorrectScore(Player player, int correctPoints) {
        return player.calculateScore(new Correct(), correctPoints);
    }

    @Override
    public int calculateIncorrectScore(Player player, int wrongPoints) {
        return player.calculateScore(new Incorrect(), 0);
    }
    @Override
    public boolean isPenaltyMode() {
        return false;
    }
}
