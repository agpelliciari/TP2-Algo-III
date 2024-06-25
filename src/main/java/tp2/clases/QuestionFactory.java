package tp2.clases;

import java.util.ArrayList;

public interface QuestionFactory {
    Question createQuestion(String questionId, JsonParser.QuestionString questionString, ArrayList<Choice> choices);
}
