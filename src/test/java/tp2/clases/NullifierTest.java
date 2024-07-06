package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.corrections.types.Correct;
import tp2.clases.model.player.powers.Nullifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullifierTest {
    private Nullifier nullifier;
    private Score score;
    private Correct correct;

    @Test
    // Un jugador no recibe puntos cuando otro jugador utiliza el anulador
    public void test01IfAPlayerReceivesTheScoreCancelerIfHeAnswersAQuestionCorrectlyHeDoesNotReceivePoints() {
        // Arrange
        score = new Score(0);
        correct = new Correct();
        Player otherPlayer = new Player("Pedro", score);

        int expectedScore = 0;
        nullifier = new Nullifier();

        // Act
        nullifier.cancel(score);

        // Another player answered a question correctly
        otherPlayer.assignScore(correct, 1);
        int scoreObtained = otherPlayer.getScore();

        // Assert
        assertEquals(expectedScore, scoreObtained);
    }
}
