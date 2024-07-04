package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.JsonParser;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.factories.QuestionFactory;
import tp2.clases.model.questions.factories.QuestionFactoryProvider;
import tp2.clases.model.questions.types.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFactoryTest {
    ArrayList<Choice> choices = new ArrayList<Choice>();
    ArrayList<JsonParser.QuestionString> questionsString = JsonParser.questionsStringParser("src/main/resources/preguntas.json");

    @Test
    public void test01AQuestionFactoryCreatesATrueOrFalseQuestion() {
        //Arrange
        QuestionFactory factory = QuestionFactoryProvider.getFactory("Verdadero Falso");

        //Act
        Question question = factory.createQuestion(questionsString.get(0), choices);

        //Assert
        assertInstanceOf(TrueOrFalse.class, question);
    }

    @Test
    public void test02AQuestionFactoryCreatesAMultipleChoiceQuestion() {
        //Arrange
        QuestionFactory factory = QuestionFactoryProvider.getFactory("Multiple Choice Simple");

        //Act
        Question question = factory.createQuestion(questionsString.get(0), choices);

        //Assert
        assertInstanceOf(MultipleChoice.class, question);
    }

    @Test
    public void test03AQuestionFactoryCreatesAOrderedChoiceQuestion() {
        //Arrange
        QuestionFactory factory = QuestionFactoryProvider.getFactory("Ordered Choice");

        //Act
        Question question = factory.createQuestion(questionsString.get(0), choices);

        //Assert
        assertInstanceOf(OrderedChoice.class, question);
    }

    @Test
    public void test04AQuestionFactoryCreatesAGroupChoiceQuestion() {
        //Arrange
        QuestionFactory factory = QuestionFactoryProvider.getFactory("Group Choice");

        //Act
        Question question = factory.createQuestion(questionsString.get(0), choices);

        //Assert
        assertInstanceOf(GroupChoice.class, question);
    }
}