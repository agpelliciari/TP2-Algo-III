package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class Type {
    private String content;
    public abstract void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers, int numberOfCorrectAnswers);
}
