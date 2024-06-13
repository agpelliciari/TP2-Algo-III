package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;

import tp2.clases.exceptions.InvalidNumberOfChosenOptionsException;

abstract class Question {
    private final int id;
    private final ArrayList<Choice> choices;
    private final Content content;
    private final Mode mode;

    public Question(int id, Content content, Mode mode, ArrayList<Choice> choices) {
        this.id = id;
        this.choices = choices;
        this.content = content;
        this.mode = mode;
    }

    public Content getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public Mode getMode() {
        return mode;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public ArrayList<Choice> getCorrectAnswers() {
        ArrayList<Choice> correctAnswers = new ArrayList<>();
        for (Choice choice: choices) {
            if (choice.getCorrection().isCorrect()) {
                correctAnswers.add(choice);
            }
        }
        return correctAnswers;
    }

    public int getNumberOfCorrectAnswers(ArrayList<Choice> choices) {
        return (int) choices.stream().filter(Choice -> Choice.getCorrection().isCorrect()).count();
    }

    public int getNumberOfIncorrectAnswers(ArrayList<Choice> choices){
        return (int) choices.stream().filter(Choice -> !(Choice.getCorrection()).isCorrect()).count();
    }

    public boolean hasNoIncorrectAnswers(ArrayList<Choice> choices) {
        return choices.stream().allMatch(Choice -> (Choice.getCorrection()).isCorrect());
    }

    public ArrayList<Choice> choiceOption(ArrayList<Choice> chosenOptions, Player aPlayer){
        for (Choice chosenOption: chosenOptions){
            //verify(chosenOption);
            chosenOption.assignPlayer(aPlayer);
        }

        return chosenOptions;
    }

/*    private void verify(Choice chosenOption) {
        boolean isVerified = false;
        for(Choice option: options){
            if(option.equals(chosenOption)){
                isVerified = true;
            }
        }

        if(!isVerified){
            throw new InvalidChosenOptionException();
        }
    }

    public ArrayList<Choice> choiceOption(String chosenOptions){
        if ((chosenOptions.toCharArray()).length > options.size() - 1){
            throw new InvalidNumberOfChosenOptionsException();
        }

        ArrayList<Choice> chosenAnswers = new ArrayList<Choice>(); // Habria que sacarlo porq no se usa
        for(char chosenOption: chosenOptions.toCharArray()) {
            for (Choice option : options) {
                if (option.equals(chosenOption)) {
                    chosenAnswers.add(option);
                }
            }
        }
        return chosenAnswers;
    }
*/
    public ArrayList<Choice> choiceOption(String chosenOptions) throws InvalidNumberOfChosenOptionsException {
        String[] optionStrings = chosenOptions.split(",");
        if (optionStrings.length > choices.size() - 1) {
            throw new InvalidNumberOfChosenOptionsException();
        }
        ArrayList<Choice> chosenAnswers = new ArrayList<>();

        for (String optionStr : optionStrings) {
            try {
                int optionIndex = Integer.parseInt(optionStr.trim()) - 1;
                if (optionIndex >= 0 && optionIndex < choices.size()) {
                    chosenAnswers.add(choices.get(optionIndex));
                } else {
                    throw new InvalidNumberOfChosenOptionsException();
                }
            } catch (NumberFormatException e) {
                throw new InvalidNumberOfChosenOptionsException();
            }
        }
        return chosenAnswers;
    }

    public abstract void assignScore(HashMap<Player, ArrayList<Choice>> chosenAnswers);

    public ArrayList<Choice> createAnswers(String chosenOption) {
        return ChoicesFactory.createAnswers(chosenOption, this);
    }
}
