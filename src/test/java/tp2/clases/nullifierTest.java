package tp2.clases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class nullifierTest {
    private Nullifier nullifier;
    private Score score;
    private Correct correct;

    @Test
    public void test01IfAPlayerReceivesTheScoreCancelerIfHeAnswersAQuestionCorrectlyHeDoesNotReceivePoints(){
        //Arrange
        score = new Score(0);
        correct = new Correct();
        Player otherPlayer = new Player(score);

        int expectedScore = 0;
        nullifier = new Nullifier();

        //Act
        nullifier.apply(score);

        //another player answered a question correctly
        otherPlayer.assignScore(correct, 1);
        int scoreObtained = otherPlayer.getScore();

        //Assert
        assertEquals(expectedScore, scoreObtained);

    }
}
