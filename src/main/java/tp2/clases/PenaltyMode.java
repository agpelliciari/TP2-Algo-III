package tp2.clases;

public class PenaltyMode implements Mode {
    @Override
    public void assignCorrectScore(Player player, int correctPints) {player.assignScore(new Correct(), correctPints);}

    @Override
    public void assignIncorrectScore(Player player, int wrongPoints) {player.assignScore(new Incorrect(), wrongPoints);}
}
