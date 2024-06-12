package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tp2.clases.exceptions.InvalidNumberOfChosenOptionsException;
import tp2.clases.exceptions.InvalidChosenOptionException;

abstract class Question {
    private final String theme;
    private final List<Answer> options;
    private final String content;
    private final Mode mode;


    public Question(String content, Mode mode, List<Answer> answers, String theme) {
        this.options = answers;
        this.theme = theme;
        this.content = content;
        this.mode = mode;
    }

    public int getNumberOfCorrectAnswers(List<Answer> answers) {
        return (int) answers.stream().filter(answer -> (answer.getCorrection()).isCorrect()).count();
    }

    public int getNumberOfIncorrectAnswers(List<Answer> answers){
        return (int) answers.stream().filter(answer -> !(answer.getCorrection()).isCorrect()).count();
    }

    public boolean hasNoIncorrectAnswers(ArrayList<Answer> answers) {
        return answers.stream().allMatch(answer -> (answer.getCorrection()).isCorrect());
    }

    public List<Answer> getOptions(){return options;}

    public Mode getMode(){return mode;}

    public ArrayList<Answer> choiceOption(ArrayList<Answer> chosenOptions, Player aPlayer){
        for (Answer chosenOption: chosenOptions){
            verify(chosenOption);
            chosenOption.assignPlayer(aPlayer);
        }

        return chosenOptions;
    }

    private void verify(Answer chosenOption) {
        boolean isVerified = false;
        for(Answer option: options){
            if(option.equals(chosenOption)){
                isVerified = true;
            }
        }

        if(!isVerified){
            throw new InvalidChosenOptionException();
        }
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

    public abstract void assignScore(HashMap<Player, ArrayList<Answer>> chosenAnswers);
}
