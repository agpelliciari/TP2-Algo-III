package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassicMode implements Mode {

    @Override
    public void assignCorrectScore(Player player, int correctPoints) {player.assignScore(new Correct(), correctPoints);}

    @Override
    public void assignIncorrectScore(Player player, int wrongPoints) {player.assignScore(new Incorrect(), wrongPoints);}
}
