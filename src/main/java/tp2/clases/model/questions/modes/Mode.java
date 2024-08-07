package tp2.clases.model.questions.modes;

import tp2.clases.model.player.Player;

public interface Mode {

    public abstract void assignCorrectScore(Player player, int correctPoints);

    public abstract void assignIncorrectScore(Player player, int wrongPoints);

    public abstract int calculateCorrectScore(Player player, int correctPoints);

    public abstract int calculateIncorrectScore(Player player, int wrongPoints);

    public abstract boolean isPenaltyMode();
}
