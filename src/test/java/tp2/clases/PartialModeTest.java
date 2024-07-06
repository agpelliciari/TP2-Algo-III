package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.PartialMode;
import tp2.clases.model.questions.types.MultipleChoice;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartialModeTest {

    Player player = new Player("Player1", new Score(0));
    Content content = new Content("", "", "");
    MultipleChoice multipleChoice;

    public void initializePartialModeTest() {
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "correcta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        multipleChoice = new MultipleChoice(1, content, new PartialMode(), choices);
    }

    @Test
    // El modo asigna el puntaje a un jugador que responde correctamente
    public void test01PlayerChoosesAllPossibleCorrectChoicesAndNoneIncorrect() {
        // Arrange
        initializePartialModeTest();

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(multipleChoice, "1,2,3");
        multipleChoice.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(3, player.getScore());
    }

    @Test
    // El modo asigna el puntaje a un jugador que responde incorrectamente
    public void test02PlayerChoosesAllPossibleCorrectChoicesAndOneIncorrectObtainsZeroPoints() {
        // Arrange
        initializePartialModeTest();

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(multipleChoice, "1,3,4");
        multipleChoice.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(0, player.getScore());
    }

    @Test
    // El modo asigna el puntaje a un jugador que responde parcialmente correcto
    public void test03PlayerChooses2OutOf3CorrectChoicesAndNoneIncorrectObtains2Points() {
        // Arrange
        initializePartialModeTest();

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(multipleChoice, "1,2");

        multipleChoice.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(2, player.getScore());
    }
}