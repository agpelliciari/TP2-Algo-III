package tp2.clases.model.questions.factories;

import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.JsonParser;
import tp2.clases.model.questions.types.Question;

import java.util.ArrayList;

public interface QuestionFactory {
    Question createQuestion(JsonParser.QuestionString questionString, ArrayList<Choice> choices);
}
