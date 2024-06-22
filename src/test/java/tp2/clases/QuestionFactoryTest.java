package tp2.clases;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFactoryTest {
    Choice choice1 = new Choice("", "correcta", 1);
    Choice choice2 = new Choice("", "incorrecta", 2);
    ArrayList<Choice> choices = new ArrayList<Choice>();
    private QuestionFactory questionFactory;

    @Test
    public void test01ItIsPossibleToCreateAQuestionWithTheExpectedTheme() {
        //Arrange
        String expectedTheme = "Sports";
        questionFactory = new QuestionFactory();
        choices.add(choice1);
        choices.add(choice2);

        //Act
        TrueOrFalse question = questionFactory.createTrueFalse(1, "Sports", "", "Verdadero Falso", choices, "");


        //Assert
        assertTrue(question.getContent().hasTheme(expectedTheme));
    }

    @Test
    public void test02ItIsPossibleToCreateAMultipleChoiceQuestionWithPartialScore() {
        //Arrange
        Mode expectedMode = new PartialMode();
        questionFactory = new QuestionFactory();
        choices.add(choice1);
        choices.add(choice2);
        
        //Act
        MultipleChoice question = questionFactory.createMultipleChoice(2, "Art", "", "Multiple Choice Puntaje Parcial", choices, "");
        Mode obtainedMode = question.getMode();

        //Assert
        assertNotEquals(expectedMode, obtainedMode);
    }

    @Test
    public void test03IsItPossibleToCreateAnOrderedChoiceQuestionWithCorrectOrderOfYourChoices() {
        //Arrange
        int[] expectedOrder = { 1, 2 };
        questionFactory = new QuestionFactory();
        choices.add(choice1);
        choices.add(choice2);

        //Act
        OrderedChoice question = questionFactory.createOrderedChoice(3, "Science", "", "Ordered Choice", choices, "", expectedOrder);

        //Assert
        assertTrue(question.checkAnswerOrder(choices));
    }

    @Test
    public void test04ItIsPossibleToCreateAGroupChoiceQuestionWithTwoGroups() {
        //Arrange
        ArrayList<Group> groups = new ArrayList<>();
        int[] choicesIdGroupA = { 1 };
        int[] choicesIdGroupB = { 2 };
        groups.add(new Group('A', "", choicesIdGroupA));
        groups.add(new Group('B', "", choicesIdGroupB));

        questionFactory = new QuestionFactory();
        choices.add(choice1);
        choices.add(choice2);

        //Act
        GroupChoice question = questionFactory.createGroupChoice(3, "Science", "", "Ordered Choice", choices, "");
        question.addGroups(groups);

        //Assert
        assertEquals(groups, question.getGroups());
    }
}