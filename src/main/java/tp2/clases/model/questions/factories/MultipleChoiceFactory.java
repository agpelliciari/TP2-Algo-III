package tp2.clases.model.questions.factories;

import tp2.clases.model.questions.modes.Mode;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.JsonParser;
import tp2.clases.model.questions.types.MultipleChoice;
import tp2.clases.model.questions.types.Question;

import java.util.ArrayList;

public class MultipleChoiceFactory implements QuestionFactory {
    private Mode mode;

    public MultipleChoiceFactory(Mode mode) {
        this.mode = mode;
    }

    @Override
    public Question createQuestion(JsonParser.QuestionString questionString, ArrayList<Choice> choices) {
        int questionIdToInt = (int) Double.parseDouble(questionString.getId());
        return new MultipleChoice(questionIdToInt, new Content(questionString.getTheme(), questionString.getQuestion() ,questionString.getAnswerText()), mode, choices);
    }
}
