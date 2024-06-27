package tp2.clases;

import java.util.ArrayList;

public class TrueOrFalseFactory implements QuestionFactory {

    private Mode mode;

    public TrueOrFalseFactory(Mode mode) {
        this.mode = mode;
    }

    @Override
    public Question createQuestion(String questionId, JsonParser.QuestionString questionString, ArrayList<Choice> choices) {
        int questionIdToInt = (int) Double.parseDouble(questionString.getId());
        return new TrueOrFalse(questionIdToInt, new Content(questionString.getTheme(), questionString.getQuestion() ,questionString.getAnswerText()), mode, choices);
    }
}
