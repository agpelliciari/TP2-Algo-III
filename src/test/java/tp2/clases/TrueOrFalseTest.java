package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.types.TrueOrFalse;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrueOrFalseTest {

    Content content = new Content("", "", "");

    @Test
    // Una pregunta de verdadero o falso asigna el puntaje correctamente a un jugador que acierta la respuesta
    public void test01TrueOrFalseQuestionAssignsScoreCorrectly() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "incorrecta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1");
        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(1, player.getScore());
    }

    @Test
    // Una pregunta de verdadero o falso asigna el puntaje correctamente a un jugador que no acierta la respuesta
    public void test02TrueOrFalseQuestionWronglyAnsweredAssignsScoreCorrectly() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "incorrecta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1");
        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(0, player.getScore());
    }

    @Test
    // Una pregunta de verdadero o falso asigna el puntaje a multiples jugadores, el primero no acierta, el segundo si
    public void test03MultiplePlayers() {
        // Arrange
        Player player1 = new Player("Player1", new Score(0));
        Player player2 = new Player("Player2", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "incorrecta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers1 = player1.setAnswers(question, "1");
        ArrayList<Choice> chosenAnswers2 = player2.setAnswers(question, "2");
        question.assignScore(player1, chosenAnswers1);
        question.assignScore(player2, chosenAnswers2);

        // Assert
        assertEquals(0, player1.getScore());
        assertEquals(1, player2.getScore());
    }
}
