package tp2.clases.model.questions.types;

import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.modes.Mode;
import tp2.clases.model.questions.Content;
import tp2.clases.model.player.Player;

import java.util.ArrayList;

public class MultipleChoice extends Question {

    public MultipleChoice(int id, Content content, Mode mode, ArrayList<Choice> choices) {
        super(id, content, mode, choices);
    }

    @Override
    public void assignScore(Player player, ArrayList<Choice> chosenAnswers) {
        Mode mode = getMode();

        if (hasNoIncorrectAnswers(chosenAnswers)) {
            mode.assignCorrectScore(player, getNumberOfCorrectAnswers(chosenAnswers));
        } else {
            mode.assignIncorrectScore(player, getNumberOfIncorrectAnswers(chosenAnswers));
        }
    }

    public int calculateScore(Player player, ArrayList<Choice> chosenAnswers) {
        Mode mode = getMode();

        if (mode.isPenaltyMode()){
            return mode.calculateCorrectScore(player, getNumberOfCorrectAnswers(chosenAnswers)) + mode.calculateIncorrectScore(player, getNumberOfIncorrectAnswers(chosenAnswers));
        }
        if (hasNoIncorrectAnswers(chosenAnswers)) {
            return mode.calculateCorrectScore(player, getNumberOfCorrectAnswers(chosenAnswers));
        } else {
            return mode.calculateIncorrectScore(player, getNumberOfIncorrectAnswers(chosenAnswers));
        }
    }

}