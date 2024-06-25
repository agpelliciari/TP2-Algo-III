package tp2.clases;

import java.util.ArrayList;

public class OrderedChoiceFactory implements QuestionFactory {

    @Override
    public Question createQuestion(String questionId, JsonParser.QuestionString questionString, ArrayList<Choice> choices) {
        int[] correctOrder = getIntArrayFromString(questionString.getAnswer());
        int questionIdToInt = (int) Double.parseDouble(questionString.getId());
        return new OrderedChoice(questionIdToInt, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices, correctOrder);
    }

    private int[] getIntArrayFromString(String Choice) {
        String[] answerParts = Choice.split(",");
        int[] answerInt = new int[answerParts.length];
        for (int i = 0; i < answerParts.length; i++) {
            answerInt[i] = Integer.parseInt(answerParts[i]);
        }
        return answerInt;
    }
}
