package tp2.clases.questions.factories;

import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.Content;
import tp2.clases.JsonParser;
import tp2.clases.questions.modes.ClassicMode;
import tp2.clases.questions.types.OrderedChoice;
import tp2.clases.questions.types.Question;

import java.util.ArrayList;

public class OrderedChoiceFactory implements QuestionFactory {

    @Override
    public Question createQuestion(JsonParser.QuestionString questionString, ArrayList<Choice> choices) {
        int[] correctOrder = getIntArrayFromString(questionString.getAnswer());
        int questionIdToInt = (int) Double.parseDouble(questionString.getId());
        return new OrderedChoice(questionIdToInt, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices, correctOrder);
    }

    private int[] getIntArrayFromString(String choice) {
        String[] answerParts = choice.split(",");
        int[] answerInt = new int[answerParts.length];
        for (int i = 0; i < answerParts.length; i++) {
            answerInt[i] = Integer.parseInt(answerParts[i]);
        }
        return answerInt;
    }
}
