package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.modes.PartialMode;
import tp2.clases.model.questions.modes.PenaltyMode;

import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModeTest {

    Player player = new Player("Juan", new Score(3));

    @Test
    // Validación de asignación de puntaje del modo clásico a los jugadores que contestaron correctamente
    public void test01ClassicModeAssignScoreToAPlayerThatAnsweredCorrectly() {
        // Arrange
        int expectedValue = 4;
        ClassicMode mode = new ClassicMode();

        // Act
        mode.assignCorrectScore(player, 3);

        // Assert
        assertEquals(expectedValue, player.getScore());
    }

    @Test
    // Validación de asignación de puntaje del modo clásico a los jugadores que contestaron incorrectamente
    public void test02ClassicModeAssignScoreToAPlayerThatAnsweredIncorrectly() {
        // Arrange
        int expectedValue = 3;
        ClassicMode mode = new ClassicMode();

        // Act
        mode.assignIncorrectScore(player, 3);

        // Assert
        assertEquals(expectedValue, player.getScore());
    }

    @Test
    // Validación de asignación de puntaje del modo con penalidad a los jugadores que contestaron correctamente
    public void test03PenaltyModeAssignScoreToAPlayerThatAnsweredCorrectly() {
        // Arrange
        int expectedValue = 6;
        PenaltyMode mode = new PenaltyMode();

        // Act
        mode.assignCorrectScore(player, 3);

        // Assert
        assertEquals(expectedValue, player.getScore());
    }

    @Test
    // Validación de asignación de puntaje del modo con penalidad a los jugadores que contestaron incorrectamente
    public void test04PenaltyModeAssignScoreToAPlayerThatAnsweredIncorrectly() {
        // Arrange
        int expectedValue = 0;
        PenaltyMode mode = new PenaltyMode();
        Player player = new Player("Juan", new Score(3));

        // Act
        mode.assignIncorrectScore(player, 3);

        // Assert
        assertEquals(expectedValue, player.getScore());
    }

    @Test
    // Validación de asignación de puntaje del modo parcial a los jugadores que contestaron correctamente
    public void test05PartialModeAssignScoreToAPlayerThatAnsweredCorrectly() {
        // Arrange
        int expectedValue = 6;
        PartialMode mode = new PartialMode();
        Player player = new Player("Juan", new Score(3));

        // Act
        mode.assignCorrectScore(player, 3);

        // Assert
        assertEquals(expectedValue, player.getScore());
    }

    @Test
    // Validacion de asignacion de puntaje del modo parcial a los jugadores que contestaron incorrectamente
    public void test06PartialModeAssignScoreToAPlayerThatAnsweredIncorrectly() {
        // Arrange
        int expectedValue = 3;
        PartialMode mode = new PartialMode();

        // Act
        mode.assignIncorrectScore(player, 3);

        // Assert
        assertEquals(expectedValue, player.getScore());
    }

}
