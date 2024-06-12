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
    @Mock private Correct correctMock;
    @Mock private Incorrect incorrectMock;

    private AutoCloseable closeable;

    private ArrayList<Answer> chosenAnswers;

    //mocks are initialized
    @BeforeEach
    public void beforeEach(){
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.reset(questionMock, answerMock, correctMock, incorrectMock);

        chosenAnswers = new ArrayList<Answer>();
        chosenAnswers.add(answerMock);

        when(questionMock.choiceOption("")).thenReturn(chosenAnswers);
        when(chosenAnswers.get(0).getCorrection()).thenReturn(correctMock);
        when(chosenAnswers.get(0).getScore()).thenReturn(1);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
    
    @Test
    public void test01AnsweringAQuestionCorrectlyIncreasesTheScore(){
        //Arrange
        score = new Score(0);
        player = new Player("name", 0);
        when(chosenAnswers.get(0).getCorrection()).thenReturn(correctMock);
        //correctMock.assignScore(score, 1);
        //when(correctMock.assignScore(score, 1)).then(score.addScore(1));


        int expectedScore = 1;

        //Act
        ArrayList<Answer> answers = player.answer(questionMock, "");
        player.assignScore(answers.get(0).getCorrection(), answers.get(0).getScore());
        int scoreObtained = player.getScore();

        //Assert
        assertEquals(scoreObtained, expectedScore);
        
    }

    @Test
    public void test02AnsweringAQuestionIncorrectlyDecreasesTheScore(){
        //Arrange
        score = new Score(1);
        when(chosenAnswers.get(0).getCorrection()).thenReturn(incorrectMock);
        //when(incorrectMock.assignScore(score,1)).then(score.subtractScore(1));

        int expectedScore = 0;
        player = new Player("name", 1);

        //Act
        ArrayList<Answer> answers = player.answer(questionMock, "");
        player.assignScore(answers.get(0).getCorrection(), answers.get(0).getScore());
        int scoreObtained = player.getScore();

        //Assert
        assertEquals(expectedScore, scoreObtained);

    }
}
