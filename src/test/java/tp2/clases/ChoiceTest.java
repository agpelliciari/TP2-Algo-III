package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChoiceTest {
    @Test
    public void test01CorrectChoiceInitiatedCorrectly() {
        Choice choice = new Choice("Verdadero","correcta", 1);
        Correction correction = choice.getCorrection();
        assertTrue(correction instanceof Correct);
    }

    @Test
    public void test02ChoiceWithCorrectionInUppercaseInitiatedCorrectly() {
        Choice choice = new Choice("Falso","incorrecta", 1);
        Correction correction = choice.getCorrection();
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    public void test03AnswerScoreAssignedCorrectly() {
        Player player = new Player("Mario", 5);
        Choice choice = new Choice("Verdadero","correcta", 1);
        player.assignScore(new Correct(),1);

        assertEquals(player.getScore(),6);
    }

    @Test
    public void test04MultipleIncorrectAnswersSubtractOne() {
        Choice choice = new Choice("Falso","Incorrecta",1);
        Player player = new Player("Luigi", 4);

        player.assignScore(new Incorrect(),1);
        player.assignScore(new Incorrect(),1);
        player.assignScore(new Incorrect(),1);

        assertEquals(player.getScore(),1);

    }
}