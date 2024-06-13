package tp2.clases;

import java.util.ArrayList;

class ChoicesFactory {
    public static ArrayList<Choice> createAnswers(String chosenAnswers, Question question) {
        ArrayList<Choice> choices = new ArrayList<>();

        String[] chosenNumbers = chosenAnswers.split(",");

        for (String chosenNumber : chosenNumbers) {
            int optionId = Integer.parseInt(chosenNumber.trim());

            for (Choice option : question.getChoices()) {
                if (option.getId() == optionId) {
                    if (option.getCorrection().isCorrect()) {
                        choices.add(new Choice(option.getContent(), "Correcta", option.getId()));
                    } else {
                        choices.add(new Choice(option.getContent(), "Incorrecta", option.getId()));
                    }
                    break;
                }
            }
        }
        return choices;
    }
}
