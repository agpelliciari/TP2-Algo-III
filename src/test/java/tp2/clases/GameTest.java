package tp2.clases;

import org.junit.jupiter.api.Test;

import tp2.clases.exceptions.InvalidNumberOfPlayersException;
import tp2.clases.exceptions.UserNameAlreadyExistsException;
import tp2.clases.model.Game;
import tp2.clases.model.JsonParser;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.types.Question;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    // Se establece la cantidad de jugadores que tendra el juego
    public void test01SelectedNumberOfPlayersEqualsThree() {
        // Arrange
        int expectedValue = 3;

        Game game = new Game();
        game.setNumberOfPlayers(3);

        // Act

        int numberOfPlayers = game.getNumberOfPlayers();

        // Assert
        assertEquals(expectedValue, numberOfPlayers);
    }

    @Test
    // La funcion que verifica si se termino el juego devuelve true cuando se llega al limite de preguntas
    public void test02TheGameIsFinishedWhenTheQuestionCountReachesTheLimit() {
        // Arrange
        boolean expectedValue = true;

        ArrayList<Question> questions = JsonParser.questionsParser("src/main/resources/preguntas.json");

        Game game = new Game(questions);
        game.setPointLimit(10);
        game.setQuestionLimit(3);

        // Act
        for (int i = 0; i < 3; i++) {
            game.getRandomQuestionIndex();
        }

        // Assert
        assertEquals(expectedValue, game.isFinished());
    }

    @Test
    // Se registran 3 usuarios correctamente
    public void test03UsersRegisteredCorrectly() {
        // Arrange
        ArrayList<String> names = new ArrayList<>();
        names.add("mateo");
        names.add("julian");
        names.add("lucas");

        Game game = new Game();

        // Act
        game.registerUsers(names);

        // Assert
        assertEquals("mateo", game.getPlayer(0).getName());
        assertEquals("julian", game.getPlayer(1).getName());
        assertEquals("lucas", game.getPlayer(2).getName());
    }

    @Test
    // El nombre de usuario seleccionado arroja una excepcion
    public void test04SelectedUserNameThrowsAnException() {
        // Arrange
        Game game = new Game(new ArrayList<>());
        ArrayList<Player> players = new ArrayList<>();

        // Act
        game.registerUser(players, new Player("mateo", new Score(0)));
        game.registerUser(players, new Player("julian", new Score(0)));

        // Assert
        assertThrows(UserNameAlreadyExistsException.class, () -> {game.registerUser(players, new Player("mateo", new Score(0)));});
    }
}