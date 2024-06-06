package tp2.clases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ScoreTest {
    private Score score;
    @Mock private Correction correction;
    @Mock private Correct correct;
    @Mock private Incorrect incorrect;

    @Test
    public void test01ACorrectCorrectionIncreasesTheScore(){
        //Arrange

        int expectedScore = 10;
        score = new Score(9);

        //Act

        score.assignScore(correct, 1);
        int scoreObtained = score.getScore();

        //Assert

        assertEquals(expectedScore, scoreObtained);
    }

    @Test
    public void test02AIncorrectCorrectionDecreasesTheScore(){
        //Arrange

        int expectedScore = 0;
        score = new Score(1);

        //Act

        score.assignScore(correct, 1);
        int scoreObtained = score.getScore();

        //Assert

        assertEquals(expectedScore, scoreObtained);
    }
}
