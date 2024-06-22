package tp2.clases;

import java.util.ArrayList;
import java.util.HashMap;

class ChoicesFactory {

    private static boolean contains(String answer, int i) {
        String[] answerParts = answer.split(",");
        for (String part : answerParts) {
            if (Integer.parseInt(part.trim()) == i) {
                return true;
            }
        }
        return false;
    }

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

    public static ArrayList<Choice> createChoices(JsonParser.QuestionString questionString, String[] validChoicesString) {
        ArrayList<Choice> choices = new ArrayList<>();
        int i = 1;

        if (!questionString.getType().equalsIgnoreCase("Ordered choice") &&
                !questionString.getType().equalsIgnoreCase("Group Choice")) {
            for (String validChoiceString : validChoicesString) {
                if (contains(questionString.getAnswer(), i)) {
                    choices.add(new Choice(validChoiceString, "Correcta", i++));
                } else {
                    choices.add(new Choice(validChoiceString, "Incorrecta", i++));
                }
            }
        } else {
            for (String validChoiceString : validChoicesString) {
                choices.add(new Choice(validChoiceString, "Correcta", i++));
            }
        }

        return choices;
    }
}
