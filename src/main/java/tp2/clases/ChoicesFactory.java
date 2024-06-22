package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;

class ChoicesFactory {

    public static ArrayList<Choice> createAnswers(String chosenAnswers, Question question) {
        ArrayList<Choice> choices = new ArrayList<>();
        String[] chosenNumbers = chosenAnswers.split(",");

        HashMap<Integer, Choice> questionChoicesMap = new HashMap<>();
        for (Choice choice : question.getChoices()) {
            questionChoicesMap.put(choice.getId(), choice);
        }

        for (String chosenNumber : chosenNumbers) {
            try {
                int choiceId = Integer.parseInt(chosenNumber.trim());
                Choice questionChoice = questionChoicesMap.get(choiceId);

                String correctionType = questionChoice.getCorrection().isCorrect() ? "Correcta" : "Incorrecta";
                choices.add(new Choice(questionChoice.getContent(), correctionType, questionChoice.getId()));

            } catch (NumberFormatException | NullPointerException error) {
                // Handling errors in case of an out-of-bounds answer the answer will automatically be incorrect
                choices.add(new Choice("", "Incorrecta", -1));
            }
        }

        return choices;
    }
}
