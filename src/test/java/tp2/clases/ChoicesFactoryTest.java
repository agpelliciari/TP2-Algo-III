package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChoicesFactoryTest {

    Content content = new Content("", "","");
    
    @Test
    public void test01CreatedAnswerHasInstanceOfCorrect() {
        //Arrange

        ArrayList<Choice> choices = new ArrayList<>();

        choices.add(new Choice("Verdadero", "correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        //Act

        ArrayList<Choice> answers = ChoicesFactory.createAnswers("1", question);

        Choice answer = answers.get(0);

        //Assert

        assertTrue(answer.getCorrection().isCorrect());
    }

    @Test
    public void test02CreatedAnswerHasInstanceOfIncorrect() {
        //Arrange

        ArrayList<Choice> choices = new ArrayList<>();

        choices.add(new Choice("Verdadero", "correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        //Act

        ArrayList<Choice> answers = ChoicesFactory.createAnswers("2", question);

        Choice answer = answers.get(0);

        //Assert
        assertFalse(answer.getCorrection().isCorrect());
    }
}
