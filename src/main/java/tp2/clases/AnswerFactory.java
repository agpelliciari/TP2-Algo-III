package tp2.clases;

import java.util.ArrayList;

class AnswerFactory {

    public static ArrayList<Answer> createAnswers(String chosenAnswers, Question question) {
        ArrayList<Answer> answers = new ArrayList<Answer>();

        for (char chosenAnswer : chosenAnswers.toCharArray()) {
            for (Answer option : question.getOptions()) {
                if ((option.equals(chosenAnswer))) {
                    if (option.getCorrection().isCorrect()) {
                        answers.add(new Answer(option.getContent(), "correcta", 1, option.getId()));
                    } else {
                        answers.add(new Answer(option.getContent(), "incorrecta", 1, option.getId()));
                    }
                }
            }
        }
        return answers;
    }
}
