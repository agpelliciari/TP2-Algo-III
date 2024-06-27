package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.questions.choice.*;
import tp2.clases.questions.Content;
import tp2.clases.questions.modes.ClassicMode;
import tp2.clases.questions.types.MultipleChoice;
import tp2.clases.questions.types.TrueOrFalse;

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

    @Test
    public void test03FactoryCreatesMultipleInstances() {
        //Arrange

        ArrayList<Choice> choices = new ArrayList<>();

        choices.add(new Choice("a", "correcta", 1));
        choices.add(new Choice("b", "Incorrecta", 2));
        choices.add(new Choice("c", "correcta", 3));
        choices.add(new Choice("d", "Incorrecta", 4));

        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        //Act

        ArrayList<Choice> answers = ChoicesFactory.createAnswers("1,2,3", question);

        Choice answerOne = answers.get(0);
        Choice answerTwo = answers.get(1);
        Choice answerThree = answers.get(2);

        //Assert

        assertTrue(answerOne.getCorrection().isCorrect());
        assertFalse(answerTwo.getCorrection().isCorrect());
        assertTrue(answerThree.getCorrection().isCorrect());
    }

    @Test
    public void test04FactoryHandlesAnOutOfBoundsAnswer() {
        //Arrange

        ArrayList<Choice> choices = new ArrayList<>();

        choices.add(new Choice("Verdadero", "correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        //Act

        ArrayList<Choice> answers = ChoicesFactory.createAnswers("1234432344", question);

        Choice answer = answers.get(0);

        //Assert

        assertFalse(answer.getCorrection().isCorrect());
    }
}
