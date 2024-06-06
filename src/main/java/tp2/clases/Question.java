package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question {
    private String theme;
    private List<Answer> options;
    private String content;
    private Type type;
    private int numberOfCorrectAnswers;


    public Question(String content, Type type, List<Answer> answers, String theme) {
        this.options = answers;
        this.theme = theme;
        this.content = content;
        this.type = type;

    }

    public int getNumberOfCorrectAnswers() {
        for (Answer answer : options) {
            if (answer.getCorrection() instanceof Correct){
                numberOfCorrectAnswers = numberOfCorrectAnswers + 1;
            }
        }
        return numberOfCorrectAnswers;
    }

    public void assignScore(HashMap<Player, ArrayList<Answer>> playersAnswers) {
        type.assignScore(playersAnswers, numberOfCorrectAnswers);
    }

    public void choiceOption(){

    }




}
