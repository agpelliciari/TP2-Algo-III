package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.modes.PartialMode;
import tp2.clases.model.questions.modes.PenaltyMode;
import tp2.clases.model.questions.types.MultipleChoice;
import tp2.clases.model.questions.types.TrueOrFalse;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplierTest {

    Content content = new Content("", "", "");

    @Test
    // Una pregunta de verdadero falso asigna el puntaje correctamente luego de que un jugador utiliza el multiplicador
    public void test01TrueOrFalseQuestionAssignsScoreCorrectly() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "incorrecta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        // Act
        player.useMultiplier(2);
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(2, player.getScore());
    }

    @Test
    // Una pregunta de verdadero falso con penalidad asigna el puntaje correctamente luego de que un jugador utiliza el multiplicador
    public void test02TrueOrFalseQuestionWithPenaltyAssignsScoreCorrectly() {
        // Arrange
        Player player = new Player("Player1", new Score(4));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "incorrecta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new PenaltyMode(), choices);

        // Act
        player.useMultiplier(2);
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "2");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(2, player.getScore());
    }

    @Test
    // Una pregunta de multiple choice con puntaje parcial asigna el puntaje correctamente luego de que un jugador utiliza el multiplicador
    public void test03PlayerChoosesAllOptionsCorrectUsingx3Multiplier() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "incorrecta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        // Act
        player.useMultiplier(3);
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(6, player.getScore());
    }

}