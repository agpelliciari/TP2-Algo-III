package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.Game;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.types.MultipleChoice;
import tp2.clases.model.questions.types.TrueOrFalse;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExclusivityTest {

    private Game game = new Game();

    // Método que inicializa tres jugadores con puntaje cero dos exclusividades de puntaje cada uno para los tests
    public void initializeExclusivityTest() {
        game.addPlayer(new Player("Ricardo", new Score(0)));
        game.addPlayer(new Player("Carlos", new Score(0)));
        game.addPlayer(new Player("Pedro", new Score(0)));
        game.setNumberOfPlayers(3);
    }

    @Test
    // Correcta asignación del doble de puntaje para el único jugador que respondió correctamente en una jugada con una sola exclusividad elegida
    public void test01CorrectAssignmentOfScoreForPlayerWhoIsTheOnlyOneToAnsweredCorrectlyWithOnlyOneChosenExclusivity() {
        // Arrange
        initializeExclusivityTest();
        game.setExclusivityCount(1);

        // Act
        game.setPlayersScoreWithoutExclusivity(0, 1);
        game.setPlayersScoreWithoutExclusivity(1, 0);
        game.setPlayersScoreWithoutExclusivity(2, 0);
        game.updatePlayersScoreWithExclusivity();

        // Assert
        assertEquals(2, game.getPlayers().get(0).getScore());
        assertEquals(0, game.getPlayers().get(1).getScore());
        assertEquals(0, game.getPlayers().get(2).getScore());
    }

    @Test
    // No hay asignación de puntajes cuando todos los jugadores aciertan en una jugada
    public void test02CorrectNoAssignmentOfScoresWhenAllPlayersAnsweredCorrectly() {
        // Arrange
        initializeExclusivityTest();
        game.setExclusivityCount(1);

        // Act
        game.setPlayersScoreWithoutExclusivity(0, 1);
        game.setPlayersScoreWithoutExclusivity(1, 1);
        game.setPlayersScoreWithoutExclusivity(2, 1);
        game.updatePlayersScoreWithExclusivity();

        // Assert
        assertEquals(0, game.getPlayers().get(0).getScore());
        assertEquals(0, game.getPlayers().get(1).getScore());
        assertEquals(0, game.getPlayers().get(2).getScore());
    }

    @Test
    // Cuando hay dos exclusividades elegidas el puntaje se multiplica por cuatro para el único jugador que acertó
    public void test03CorrectAssignmentOfScoreForPlayerWhoIsTheOnlyOneAnsweredCorrectlyWhenTwoExclusivitiesAreChosen() {
        // Arrange
        initializeExclusivityTest();
        game.setExclusivityCount(2);

        // Act
        game.setPlayersScoreWithoutExclusivity(0, 1);
        game.setPlayersScoreWithoutExclusivity(1, 0);
        game.setPlayersScoreWithoutExclusivity(2, 0);
        game.updatePlayersScoreWithExclusivity();

        // Assert
        assertEquals(4, game.getPlayers().get(0).getScore());
        assertEquals(0, game.getPlayers().get(1).getScore());
        assertEquals(0, game.getPlayers().get(2).getScore());
    }

    @Test
    // Cuando hay tres exclusividades elegidas el puntaje se multiplica por seis para el único jugador que acertó
    public void test04CorrectAssignmentOfScoreForPlayerWhoIsTheOnlyOneAnsweredCorrectlyWhenThreeExclusivitiesAreChosen() {
        // Arrange
        initializeExclusivityTest();
        game.setExclusivityCount(3);

        // Act
        game.setPlayersScoreWithoutExclusivity(0, 1);
        game.setPlayersScoreWithoutExclusivity(1, 0);
        game.setPlayersScoreWithoutExclusivity(2, 0);
        game.updatePlayersScoreWithExclusivity();

        // Assert
        assertEquals(6, game.getPlayers().get(0).getScore());
        assertEquals(0, game.getPlayers().get(1).getScore());
        assertEquals(0, game.getPlayers().get(2).getScore());
    }

    @Test
    // Cuando hay dos (o más) exclusividades elegidas y más de uno acierta el puntaje de los jugadores solo se duplica
    public void test05CorrectAssignmentOfScoreForPlayerWhenMoreThanOneAnsweredCorrectlyWhenTwoExclusivitiesAreChosen() {
        // Arrange
        initializeExclusivityTest();
        game.setExclusivityCount(2);

        // Act
        game.setPlayersScoreWithoutExclusivity(0, 1);
        game.setPlayersScoreWithoutExclusivity(1, 1);
        game.setPlayersScoreWithoutExclusivity(2, 0);
        game.updatePlayersScoreWithExclusivity();

        // Assert
        assertEquals(2, game.getPlayers().get(0).getScore());
        assertEquals(2, game.getPlayers().get(1).getScore());
        assertEquals(0, game.getPlayers().get(2).getScore());
    }
}
