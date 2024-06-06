package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tp2.clases.exceptions.InvalidNumberOfChosenOptionsException;

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
        type.assignScore(playersAnswers, getNumberOfCorrectAnswers());
    }

    public ArrayList<Answer> choiceOption(String chosenOptions){
        if ((chosenOptions.toCharArray()).length > options.size() - 1){
            throw new InvalidNumberOfChosenOptionsException();
        }

        ArrayList<Answer> chosenAnswers = new ArrayList<Answer>();
        for(char chosenOption: chosenOptions.toCharArray()) {
            for (Answer option : options) {
                if (option.equals(chosenOption)) {
                    chosenAnswers.add(option);
                }
            }
        }
        return chosenAnswers;
    }




}
