package tp2.clases;


import org.junit.jupiter.api.Test;
import tp2.clases.exceptions.InvalidNumberOfChosenChoicesException;
import tp2.clases.exceptions.UserNameAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class QuestionTest {

    Content content = new Content("", "","");

    @Test
    public void test01ATrueOrFalseQuestionHasOneCorrectAnswer() {
        //Arrange
        int expectedValue = 1;
        ArrayList<Choice> choices = new ArrayList<>();

        choices.add(new Choice("Verdadero", "correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        //Act
        int numberOfCorrectAnswers = question.getNumberOfCorrectAnswers(question.getChoices());

        //Assert
        assertEquals(expectedValue, numberOfCorrectAnswers);
    }

    @Test
    public void test02AMultipleChoiceQuestionHasThreeCorrectAnswers() {
        //Arrange
        int expectedValue = 3;
        ArrayList<Choice> choices = new ArrayList<>();

        choices.add(new Choice("1", "correcta", 1));
        choices.add(new Choice("2", "Incorrecta", 2));
        choices.add(new Choice("3", "Correcta", 3));
        choices.add(new Choice("4", "Correcta", 4));

        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        //Act
        int numberOfCorrectAnswers = question.getNumberOfCorrectAnswers(choices);

        //Assert
        assertEquals(expectedValue, numberOfCorrectAnswers);
    }

    @Test
    public void test03ATrueOrFalseQuestionReturnsTheChosenChoices() {
        //Arrange
        ArrayList<Choice> expectedValue = new ArrayList<>();
        ArrayList<Choice> choices = new ArrayList<>();

        Choice answer1 = new Choice("1", "correcta", 1);
        choices.add(answer1);
        expectedValue.add(answer1);

        choices.add(new Choice("3", "Incorrecta", 3));

        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        //Act
        ArrayList<Choice> assignChosenChoicesToPlayer = question.assignChosenChoicesToPlayer("1");

        //Assert
        assertEquals(expectedValue, assignChosenChoicesToPlayer);
    }

    @Test
    public void test04AMultipleChoiceQuestionReturnsTheChosenChoices() {
        //Arrange
        ArrayList<Choice> expectedValue = new ArrayList<>();
        ArrayList<Choice> choices = new ArrayList<>();

        Choice answer1 = new Choice("1", "correcta", 1);
        choices.add(answer1);
        expectedValue.add(answer1);

        Choice answer2 = new Choice("2", "Incorrecta", 2);
        choices.add(answer2);
        expectedValue.add(answer2);

        choices.add(new Choice("3", "Correcta", 3));
        choices.add(new Choice("4", "Correcta", 4));

        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        //Act
        ArrayList<Choice> assignChosenChoicesToPlayer = question.assignChosenChoicesToPlayer("1,2");

        //Assert
        assertEquals(expectedValue, assignChosenChoicesToPlayer);
    }

    @Test
    public void test05ATrueOrFalseQuestionThrowsAnExceptionWhenAnUserChoosesTwoAnswersInAQuestionWithTwoChoices() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();

        choices.add(new Choice("1", "correcta", 1));
        choices.add(new Choice("3", "Incorrecta", 3));

        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        // Assert
        assertThrows(InvalidNumberOfChosenChoicesException.class, () -> { question.assignChosenChoicesToPlayer("1,3"); });
    }

    @Test
    public void test05AMultipleChoiceQuestionThrowsAnExceptionWhenAnUserChoosesFourAnswersInAQuestionWithFourChoices() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();

        choices.add(new Choice("1", "correcta", 1));
        choices.add(new Choice("3", "Incorrecta", 3));
        choices.add(new Choice("2", "Correcta", 2));
        choices.add(new Choice("4", "Incorrecta", 4));

        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        //Assert
        assertThrows(InvalidNumberOfChosenChoicesException.class, () -> {question.assignChosenChoicesToPlayer("1,3,2,4");});
    }
}