package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.types.MultipleChoice;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceTest {

    Content content = new Content("", "", "");

    @Test
    // Un jugador tiene una respuesta incorrecta y no se le asigna el puntaje
    public void test01PlayerChoosesOneIncorrectChoiceAndDoesntReceivePoints() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "incorrecta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2,3");
        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(0, player.getScore());
    }

    @Test
    // Un jugador tiene todas las respuestas correctas y se le asigna el puntaje
    public void test02PlayerChoosesAllChoicesCorrect() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "incorrecta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2");
        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(1, player.getScore());
    }

    @Test
    // Un jugador tiene todas las respuestas incorrectas y no se le asigna el puntaje
    public void test03PlayerChoosesAllChoicesIncorrect() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "incorrecta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "3,4");
        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(0, player.getScore());
    }
}