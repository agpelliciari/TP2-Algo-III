package tp2.clases;
import java.util.HashMap;


public abstract class Type {
    private String content;
    public abstract void assignScore(HashMap<Player, Answer> chosenAnswers, int numberOfCorrectAnswers);


}
