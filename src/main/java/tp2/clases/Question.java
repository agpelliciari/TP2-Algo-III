package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tp2.clases.Player;

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

    public ArrayList<Answer> choiceOption(String chosenOptions){

        ArrayList<Answer> chosenAnswers = new ArrayList<Answer>();
        for(char chosenOption: chosenOptions.toCharArray()){
            chosenAnswers.add((Answer) options.stream().map(option -> option.equals(chosenOption)));
        }

        return chosenAnswers;
    }




}
