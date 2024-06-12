package tp2.clases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class PlayerTest {
    private Player player;
    private Score score;
    @Mock private Question questionMock;
    @Mock private Answer answerMock;
    private Correct correct;
    private Incorrect incorrect;

    private AutoCloseable closeable;

    private ArrayList<Answer> chosenAnswers;

    //mocks are initialized
    @BeforeEach
    public void beforeEach(){
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.reset(questionMock, answerMock);

        chosenAnswers = new ArrayList<Answer>();
        chosenAnswers.add(answerMock);

        when(questionMock.choiceOption("")).thenReturn(chosenAnswers);
        when(chosenAnswers.get(0).getScore()).thenReturn(1);
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
        ArrayList<Answer> answers = player.answer(questionMock, "");
        answers.add(answerMock);
        player.assignScore(answers.get(0).getCorrection(), answers.get(0).getScore());
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
        ArrayList<Answer> answers = player.answer(questionMock, "");
        answers.add(answerMock);
        player.assignScore(answers.get(0).getCorrection(), answers.get(0).getScore());
        int scoreObtained = player.getScore();

        //Assert
        assertEquals(expectedScore, scoreObtained);

    }
}
