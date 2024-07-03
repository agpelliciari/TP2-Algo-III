package tp2.clases.questions.factories;

import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.Content;
import tp2.clases.JsonParser;
import tp2.clases.questions.modes.Mode;
import tp2.clases.questions.types.Question;
import tp2.clases.questions.types.TrueOrFalse;

import java.util.ArrayList;

public class TrueOrFalseFactory implements QuestionFactory {

    private Mode mode;

    public TrueOrFalseFactory(Mode mode) {
        this.mode = mode;
    }

    @Override
    public Question createQuestion(JsonParser.QuestionString questionString, ArrayList<Choice> choices) {
        int questionIdToInt = (int) Double.parseDouble(questionString.getId());
        return new TrueOrFalse(questionIdToInt, new Content(questionString.getTheme(), questionString.getQuestion() ,questionString.getAnswerText()), mode, choices);
    }
}
