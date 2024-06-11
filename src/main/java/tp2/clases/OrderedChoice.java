package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderedChoice extends Question{

    public OrderedChoice(String content, Mode mode, List<Answer> answers, String theme) {
        super(content, mode, answers, theme);
    }

    @Override
    public void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers) {
        
    }
}
