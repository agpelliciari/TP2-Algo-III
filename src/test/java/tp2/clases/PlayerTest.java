package tp2.clases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tp2.clases.exceptions.InvalidNumberOfChosenChoicesException;
import tp2.clases.exceptions.InvalidChoiceIndexException;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class PlayerTest {
    private Player player;
    @Mock private Question questionMock;
    @Mock private Choice answerMock;
    private Correct correct;
    private Incorrect incorrect;

    private AutoCloseable closeable;

    private ArrayList<Choice> chosenAnswers;

    //mocks are initialized
    @BeforeEach
    public void beforeEach() throws InvalidNumberOfChosenChoicesException, InvalidChoiceIndexException {
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.reset(questionMock, answerMock);

        chosenAnswers = new ArrayList<>();
        chosenAnswers.add(answerMock);

        when(questionMock.assignChosenChoicesToPlayer("")).thenReturn(chosenAnswers);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
    
    @Test
    public void test01AnsweringAQuestionCorrectlyIncreasesTheScore(){
        //Arrange
        correct = new Correct();
        when(chosenAnswers.get(0).getCorrection()).thenReturn(correct);

        int expectedScore = 1;
        player = new Player("name", 0);

        //Act
        ArrayList<Choice> choices = player.setAnswers(questionMock, "");
        choices.add(answerMock);
        player.assignScore(choices.get(0).getCorrection(), 1);
        int scoreObtained = player.getScore();

        //Assert
        assertEquals(scoreObtained, expectedScore);
    }

    @Test
    public void test02AnsweringAQuestionIncorrectlyDecreasesTheScore(){
        //Arrange
        incorrect = new Incorrect();
        when(chosenAnswers.get(0).getCorrection()).thenReturn(incorrect);
        int expectedScore = 0;
        player = new Player("name", 1);

        //Act
        ArrayList<Choice> choices = player.setAnswers(questionMock, "");
        choices.add(answerMock);
        player.assignScore(choices.get(0).getCorrection(), 1);
        int scoreObtained = player.getScore();

        //Assert
        assertEquals(expectedScore, scoreObtained);
    }
}
