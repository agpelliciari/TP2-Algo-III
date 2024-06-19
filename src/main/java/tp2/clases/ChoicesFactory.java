package tp2.clases;

import java.util.ArrayList;

class ChoicesFactory {

    public static ArrayList<Choice> createAnswers(String chosenAnswers, Question question) {
        ArrayList<Choice> choices = new ArrayList<>();

        String[] chosenNumbers = chosenAnswers.split(",");

        for (String chosenNumber : chosenNumbers) {
            int choiceId = Integer.parseInt(chosenNumber.trim());

            for (Choice choice : question.getChoices()) {
                if (choice.getId() == choiceId) {
                    if (choice.getCorrection().isCorrect()) {
                        choices.add(new Choice(choice.getContent(), "Correcta", choice.getId()));
                    } else {
                        choices.add(new Choice(choice.getContent(), "Incorrecta", choice.getId()));
                    }
                    break;
                }
            }
        }
        return choices;
    }
}
