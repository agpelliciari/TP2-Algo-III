package tp2.clases.model.questions.factories;

import tp2.clases.model.questions.modes.Mode;
import tp2.clases.model.questions.types.TrueOrFalse;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.JsonParser;
import tp2.clases.model.questions.types.Question;

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
