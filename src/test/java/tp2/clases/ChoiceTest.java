package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.choice.corrections.types.Correct;
import tp2.clases.model.questions.choice.corrections.types.Correction;
import tp2.clases.model.questions.choice.corrections.types.Incorrect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChoiceTest {

    // Correcta creación de una instancia de opción correcta
    @Test
    public void test01CorrectChoiceInitiatedCorrectly() {
        // Arrange
        Choice choice = new Choice("Verdadero","correcta", 1);

        // Act
        Correction correction = choice.getCorrection();

        // Assert
        assertTrue(correction instanceof Correct);
    }

    @Test
    // Correcta creación de una instancia de opción incorrecta
    public void test02IncorrectChoiceInitiatedCorrectly() {
        // Arrange
        Choice choice = new Choice("Falso","incorrecta", 1);

        // Act
        Correction correction = choice.getCorrection();

        // Assert
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    // Asignación de puntaje correcta a un jugador con una intancia de opción correcta
    public void test03AnswerScoreAssignedCorrectlyForCorrectChoice() {
        // Arrange
        Player player = new Player("Mario", new Score(5));
        Choice choice = new Choice("Verdadero","correcta", 1);

        // Act
        player.assignScore(choice.getCorrection(),1);

        // Assert
        assertEquals(player.getScore(),6);
    }
}