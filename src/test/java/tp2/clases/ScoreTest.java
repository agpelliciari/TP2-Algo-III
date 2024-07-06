package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import tp2.clases.model.player.score.Score;

public class ScoreTest {

    private Score score;

    @Test
    // Se le incrementa el valor al puntaje correctamente
    public void test01AddingPointsToTheCurrentScoreIncreasesTheTotalScore() {
        // Arrange

        int expectedScore = 10;
        score = new Score(9);

        // Act
        score.addScore(1);
        int scoreObtained = score.getScore();

        // Assert
        assertEquals(expectedScore, scoreObtained);
    }

    @Test
    // Se le decrementa el valor al puntaje correctamente
    public void test02SubtractingPointsFromTheCurrentScoreDecreasesTheTotalScore() {
        // Arrange

        int expectedScore = 0;
        score = new Score(1);

        // Act
        score.subtractScore(1);
        int scoreObtained = score.getScore();

        // Assert
        assertEquals(expectedScore, scoreObtained);
    }
}
