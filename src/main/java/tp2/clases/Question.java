package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;
import tp2.clases.exceptions.InvalidNumberOfChosenChoicesException;

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

    public ArrayList<Choice> assignChosenChoicesToPlayer(ArrayList<Choice> assignChosenChoicesToPlayer, Player aPlayer){
        for (Choice chosenChoice: assignChosenChoicesToPlayer){
            chosenChoice.assignPlayer(aPlayer);
        }
        return assignChosenChoicesToPlayer;
    }

/*    private void verify(Choice chosenChoice) {
        boolean isVerified = false;
        for(Choice choice: choices){
            if(choice.equals(chosenChoice)){
                isVerified = true;
            }
        }

        if(!isVerified){
            throw new InvalidChosenChoiceException();
        }
    }

    public ArrayList<Choice> assignChosenChoicesToPlayer(String assignChosenChoicesToPlayer){
        if ((assignChosenChoicesToPlayer.toCharArray()).length > choices.size() - 1){
            throw new InvalidNumberOfChosenChoicesException();
        }

        ArrayList<Choice> chosenAnswers = new ArrayList<Choice>(); // Habria que sacarlo porq no se usa
        for(char chosenChoice: assignChosenChoicesToPlayer.toCharArray()) {
            for (Choice choice : choices) {
                if (choice.equals(chosenChoice)) {
                    chosenAnswers.add(choice);
                }
            }
        }
        return chosenAnswers;
    }
*/
    public ArrayList<Choice> assignChosenChoicesToPlayer(String assignChosenChoicesToPlayer) {
        String[] choiceStrings = assignChosenChoicesToPlayer.split(",");
        if (choiceStrings.length > choices.size() - 1) {
            throw new InvalidNumberOfChosenChoicesException();
        }
        ArrayList<Choice> chosenAnswers = new ArrayList<>();

        for (String choiceStr : choiceStrings) {
            try {
                int choiceIndex = Integer.parseInt(choiceStr.trim()) - 1;
                if (choiceIndex >= 0 && choiceIndex < choices.size()) {
                    chosenAnswers.add(choices.get(choiceIndex));
                } else {
                    throw new InvalidNumberOfChosenChoicesException();
                }
            } catch (NumberFormatException e) {
                throw new InvalidNumberOfChosenChoicesException();
            }
        }
        return chosenAnswers;
    }

    public abstract void assignScore(HashMap<Player, ArrayList<Choice>> chosenAnswers);

    public ArrayList<Choice> createAnswers(String chosenChoice) {
        return ChoicesFactory.createAnswers(chosenChoice, this);
    }
}
