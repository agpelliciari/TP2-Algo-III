package tp2.clases;

public class ClassicMode implements Mode {

    @Override
    public void assignCorrectScore(Player player) {player.setScore(player.getScore()+1);}

    @Override
    public void assignIncorrectScore(Player player) {player.setScore(player.getScore());}
}
