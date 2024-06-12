package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Mode {
    public abstract void assignCorrectScore(Player player, int correctPoints);
    public abstract void assignIncorrectScore(Player player, int wrongPoints);
}
