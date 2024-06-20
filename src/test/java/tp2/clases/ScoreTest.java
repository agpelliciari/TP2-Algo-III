package tp2.clases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


import java.util.ArrayList;

public class ScoreTest {
    private Score score;
/*    @Mock private Correct correctMock;
    @Mock private Incorrect incorrectMock;

    private AutoCloseable closeable;

    //mocks are initialized
    @BeforeEach
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.reset(correctMock, incorrectMock);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
*/
    @Test
    public void test01AddingPointsToTheCurrentScoreIncreasesTheTotalScore() {
        //Arrange
//        when(correctMock.assignScore(1)).thenReturn(1);

        int expectedScore = 10;
        score = new Score(9);

        //Act
        score.addScore(1);
        int scoreObtained = score.getScore();

        //Assert
        assertEquals(expectedScore, scoreObtained);
    }

    @Test
    public void test02SubtractingPointsFromTheCurrentScoreDecreasesTheTotalScore() {
        //Arrange
//        when(incorrectMock.assignScore(1)).thenReturn(-1);

        int expectedScore = 0;
        score = new Score(1);

        //Act
        score.subtractScore(1);
        int scoreObtained = score.getScore();

        //Assert
        assertEquals(expectedScore, scoreObtained);
    }
}
