package tp2.clases;

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
}
