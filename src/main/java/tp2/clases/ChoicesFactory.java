package tp2.clases;

import java.util.ArrayList;

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

        for (String chosenNumber : chosenNumbers) {
            try {
                int choiceId = Integer.parseInt(chosenNumber.trim());
                Choice questionChoice = null;

                for (Choice choice : question.getChoices()) {
                    if (choice.getId() == choiceId) {
                        questionChoice = choice;
                        break;
                    }
                }

                if (questionChoice != null) {
                    String correctionType = questionChoice.getCorrection().isCorrect() ? "Correcta" : "Incorrecta";
                    choices.add(new Choice(questionChoice.getContent(), correctionType, questionChoice.getId()));
                } else {
                    choices.add(new Choice("", "Incorrecta", -1));
                }

            } catch (NumberFormatException error) {
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