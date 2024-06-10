package tp2.clases;

public class PenaltyMode implements Mode {
    @Override
    public void assignCorrectScore(Player player, int numberOfCorrectAnswers) {player.setScore(player.getScore() + numberOfCorrectAnswers);}

    @Override
    public void assignIncorrectScore(Player player, int numberOfCorrectAnswers) {player.setScore(player.getScore() + numberOfCorrectAnswers);}
}
