package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.types.OrderedChoice;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderedChoiceTest {

    Content content = new Content("", "", "");
    Player player = new Player("Player1", new Score(0));
    OrderedChoice orderedChoice;

    public void initializeOrderedChoiceTest() {
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "correcta", 3));
        choices.add(new Choice("Answer4", "correcta", 4));
        orderedChoice = new OrderedChoice(1, content, new ClassicMode(), choices, new int[] {1, 3, 2, 4});
    }

    @Test
    // La pregunta asigna el puntaje a un jugador que responde correctamente
    public void test01PlayerGetsOrderCorrectReceivesOnePoint() {
        // Arrange
        initializeOrderedChoiceTest();

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(orderedChoice, "1,3,2,4");
        orderedChoice.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(1, player.getScore());
    }

    @Test
    // La pregunta asigna el puntaje a un jugador que responde incorrectamente
    public void test02PlayerGetsOrderIncorrectReceivesZeroPoints() {
        // Arrange
        initializeOrderedChoiceTest();

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(orderedChoice, "1,2,3,4");
        orderedChoice.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(0, player.getScore());
    }
}
