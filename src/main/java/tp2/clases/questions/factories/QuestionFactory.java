package tp2.clases.questions.factories;

import tp2.clases.questions.choice.Choice;
import tp2.clases.JsonParser;
import tp2.clases.questions.types.Question;

import java.util.ArrayList;

public interface QuestionFactory {
    Question createQuestion(JsonParser.QuestionString questionString, ArrayList<Choice> choices);
}
