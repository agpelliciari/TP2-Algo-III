package tp2.clases;


import org.junit.jupiter.api.Test;
import tp2.clases.exceptions.InvalidNumberOfChosenOptionsException;
import tp2.clases.exceptions.UserNameAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class QuestionTest {

    @Test
    public void test01ATrueOrFalseQuestionHasOneCorrectAnswer() {
        //Arrange
        int expectedValue = 1;
        ArrayList<Answer> answers = new ArrayList<Answer>();

        answers.add(new Answer("Verdadero", "Correcta", 1, 'a'));
        answers.add(new Answer("Falso", "Incorrecta", 1, 'b'));

        Question question = new Question("", new TrueOrFalse(), answers, "");

        //Act

        int numberOfCorrectAnswers = question.getNumberOfCorrectAnswers();

        //Assert

        assertEquals(expectedValue, numberOfCorrectAnswers);
    }

    @Test
    public void test02AMultipleChoiceQuestionHasThreeCorrectAnswers() {
        //Arrange
        int expectedValue = 3;
        ArrayList<Answer> answers = new ArrayList<Answer>();

        answers.add(new Answer("a", "Correcta", 1, 'a'));
        answers.add(new Answer("b", "Incorrecta", 1, 'b'));
        answers.add(new Answer("c", "Correcta", 1, 'c'));
        answers.add(new Answer("d", "Correcta", 1, 'd'));

        Question question = new Question("", new MultipleChoice(), answers, "");

        //Act

        int numberOfCorrectAnswers = question.getNumberOfCorrectAnswers();

        //Assert

        assertEquals(expectedValue, numberOfCorrectAnswers);
    }

    @Test
    public void test03ATrueOrFalseQuestionReturnsTheChosenOptions() {
        //Arrange
        ArrayList<Answer> expectedValue = new ArrayList<Answer>();
        ArrayList<Answer> answers = new ArrayList<Answer>();

        Answer answer1 = new Answer("a", "Correcta", 1, 'a');
        answers.add(answer1);
        expectedValue.add(answer1);

        answers.add(new Answer("c", "Incorrecta", 1, 'c'));

        Question question = new Question("", new TrueOrFalse(), answers, "");

        //Act
        ArrayList<Answer> chosenOptions = question.choiceOption("a");

        //Assert

        assertEquals(expectedValue, chosenOptions);
    }

    @Test
    public void test04AMultipleChoiceQuestionReturnsTheChosenOptions() {
        //Arrange
        ArrayList<Answer> expectedValue = new ArrayList<Answer>();
        ArrayList<Answer> answers = new ArrayList<Answer>();

        Answer answer1 = new Answer("a", "Correcta", 1, 'a');
        answers.add(answer1);
        expectedValue.add(answer1);

        Answer answer2 = new Answer("b", "Incorrecta", 1, 'b');
        answers.add(answer2);
        expectedValue.add(answer2);

        answers.add(new Answer("c", "Correcta", 1, 'c'));

        answers.add(new Answer("d", "Correcta", 1, 'd'));

        Question question = new Question("", new MultipleChoice(), answers, "");

        //Act
        ArrayList<Answer> chosenOptions = question.choiceOption("ab");

        //Assert

        assertEquals(expectedValue, chosenOptions);
    }

    @Test
    public void test05ATrueOrFalseQuestionThrowsAnExceptionWhenAnUserChoosesTwoAnswersInAQuestionWithTwoOptions() {
        //Arrange
        ArrayList<Answer> answers = new ArrayList<Answer>();

        answers.add(new Answer("a", "Correcta", 1, 'a'));
        answers.add(new Answer("c", "Incorrecta", 1, 'c'));

        Question question = new Question("", new TrueOrFalse(), answers, "");


        //Assert

        assertThrows(InvalidNumberOfChosenOptionsException.class, () -> {question.choiceOption("ac");});
    }

    @Test
    public void test05AMultipleChoiceQuestionThrowsAnExceptionWhenAnUserChoosesFourAnswersInAQuestionWithFourOptions() {
        //Arrange
        ArrayList<Answer> answers = new ArrayList<Answer>();

        answers.add(new Answer("a", "Correcta", 1, 'a'));
        answers.add(new Answer("c", "Incorrecta", 1, 'c'));
        answers.add(new Answer("b", "Correcta", 1, 'b'));
        answers.add(new Answer("d", "Incorrecta", 1, 'd'));

        Question question = new Question("", new TrueOrFalse(), answers, "");


        //Assert

        assertThrows(InvalidNumberOfChosenOptionsException.class, () -> {question.choiceOption("acbd");});
    }
}