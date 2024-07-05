package tp2.clases;

import org.junit.jupiter.api.Test;

import tp2.clases.exceptions.InvalidNumberOfPlayersException;
import tp2.clases.exceptions.UserNameAlreadyExistsException;
import tp2.clases.model.Game;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void test01SelectedNumberOfPlayersEqualsFour() {
        int expectedValue = 4;
        Game game = new Game(new ArrayList<>());
        ArrayList<String> names = new ArrayList<>();
        names.add("mateo");
        names.add("julian");
        names.add("matias");
        names.add("lucas");
        ArrayList<Player> players = game.selectPlayers(4, names);

        assertEquals(expectedValue, players.size());
    }

    @Test
    public void test02SelectedNumberOfPlayersThrowsAnException() {
        Game game = new Game(new ArrayList<>());

        assertThrows(InvalidNumberOfPlayersException.class, () -> {game.selectPlayers(1, new ArrayList<>());});
    }

    @Test
    public void test03UserRegisteredCorrectly() {
        String expectedValue = "mateo";
        Game game = new Game(new ArrayList<>());
        ArrayList<Player> players = new ArrayList<>();

        game.registerUser(players, new Player("mateo", new Score(0)));
        Player player = players.get(0);

        assertEquals(expectedValue, player.getName());
    }

    @Test
    public void test04SelectedUserNameThrowsAnException() {
        Game game = new Game(new ArrayList<>());
        ArrayList<Player> players = new ArrayList<>();

        game.registerUser(players, new Player("mateo", new Score(0)));
        game.registerUser(players, new Player("julian", new Score(0)));

        assertThrows(UserNameAlreadyExistsException.class, () -> {game.registerUser(players, new Player("mateo", new Score(0)));});
    }
}