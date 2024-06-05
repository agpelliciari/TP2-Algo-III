package tp2.clases;

import org.junit.jupiter.api.Test;

import tp2.clases.exceptions.InvalidNumberOfPlayersException;
import tp2.clases.exceptions.UserNameAlreadyExistsException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void test01SelectedNumberOfPlayersEqualsFour() {
        int expectedValue = 4;
        Game game = new Game(new ArrayList<Question>(), 2000, new Panel());

        ArrayList<Player> players = game.selectPlayers(4);

        assertEquals(expectedValue, players.size());
    }

    @Test
    public void test02SelectedNumberOfPlayersThrowsAnException() {
        Game game = new Game(new ArrayList<Question>(), 2000, new Panel());

        assertThrows(InvalidNumberOfPlayersException.class, () -> {game.selectPlayers(1);});
    }

    @Test
    public void test03UserRegisteredCorrectly() {
        Player expectedValue = new Player("mateo", 0);
        Game game = new Game(new ArrayList<Question>(), 2000, new Panel());
        ArrayList<Player> players = new ArrayList<>();

        game.registerUser(players, new Player("mateo", 0));

        assertEquals(expectedValue, players.get(0));
    }

    @Test
    public void test04SelectedUserNameThrowsAnException() {
        Game game = new Game(new ArrayList<Question>(), 2000, new Panel());
        ArrayList<Player> players = new ArrayList<>();

        game.registerUser(players, new Player("mateo", 0));
        game.registerUser(players, new Player("julian", 0));

        assertThrows(UserNameAlreadyExistsException.class, () -> {game.registerUser(players, new Player("mateo", 0));});
    }
}