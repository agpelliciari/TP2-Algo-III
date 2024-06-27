package tp2.clases.questions.types;

import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.Content;
import tp2.clases.player.Player;
import tp2.clases.questions.modes.Mode;

import java.util.ArrayList;

public class OrderedChoice extends Question {
    private int[] correctOrder;

    public OrderedChoice(int id, Content content, Mode mode, ArrayList<Choice> choices, int[] correctOrder) {
        super(id, content, mode, choices);
        this.correctOrder = correctOrder;
    }

    @Override
    public void assignScore(Player player, ArrayList<Choice> chosenAnswers) {
        Mode mode = getMode();

        if (checkAnswerOrder(chosenAnswers)) {
            mode.assignCorrectScore(player, 1);
        } else {
            mode.assignIncorrectScore(player, getNumberOfIncorrectAnswers(chosenAnswers));
        }
    }

    @Override
    public int calculateScore(Player player, ArrayList<Choice> chosenAnswers) {
        Mode mode = getMode();

        if (checkAnswerOrder(chosenAnswers)) {
            return mode.calculateCorrectScore(player, 1);
        } else {
            return mode.calculateIncorrectScore(player, getNumberOfIncorrectAnswers(chosenAnswers));
        }
    }

    public boolean checkAnswerOrder(ArrayList<Choice> playerAnswers) {
        ArrayList<Choice> correctChoices = getChoices();
        if (playerAnswers.size() != correctOrder.length) {
            return false;
        }

        for (int i = 0; i < playerAnswers.size(); i++) {
            // correctOrder[i] is 1-based, so we need to convert to 0-based by subtracting 1
            int correctChoiceIndex = correctOrder[i] - 1;
            int correctChoiceId = correctChoices.get(correctChoiceIndex).getId();
            if (playerAnswers.get(i).getId() != correctChoiceId) {
                return false;
            }
        }
        return true;
    }
}