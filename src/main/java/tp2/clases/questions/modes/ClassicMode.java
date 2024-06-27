package tp2.clases.questions.modes;

import tp2.clases.questions.choice.corrections.types.Correct;
import tp2.clases.questions.choice.corrections.types.Incorrect;
import tp2.clases.player.Player;

public class ClassicMode implements Mode {

    @Override
    public void assignCorrectScore(Player player, int correctPoints) {
        player.assignScore(new Correct(), 1);
    }

    @Override
    public void assignIncorrectScore(Player player, int wrongPoints) {
        player.assignScore(new Incorrect(), 0);
    }

    @Override
    public int calculateCorrectScore(Player player, int correctPoints) {
        return player.calculateScore(new Correct(), 1);
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
