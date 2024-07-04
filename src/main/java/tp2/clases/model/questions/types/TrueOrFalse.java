package tp2.clases.model.questions.types;

import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.player.Player;
import tp2.clases.model.questions.modes.Mode;

import java.util.ArrayList;

public class TrueOrFalse extends Question {

    public TrueOrFalse(int id, Content content, Mode mode, ArrayList<Choice> choices) {
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


    @Override
    public int calculateScore(Player player, ArrayList<Choice> chosenAnswers) {
        Mode mode = getMode();

        if (hasNoIncorrectAnswers(chosenAnswers)) {
            return mode.calculateCorrectScore(player, getNumberOfCorrectAnswers(chosenAnswers));
        } else {
            return mode.calculateIncorrectScore(player, getNumberOfIncorrectAnswers(chosenAnswers));
        }
    }
}