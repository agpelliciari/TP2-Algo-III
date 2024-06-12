package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    @Test
    public void test01CorrectAnswerInitiatedCorrectly(){

        Answer answer = new Answer("Verdadero","Correcta", 1,'a');
        Correction correction = answer.getCorrection();
        assertTrue(correction instanceof Correct);

    }

    @Test
    public void test02AnswerWithCorrectionInUppercaseInitiatedCorrectly(){
        Answer answer = new Answer("Falso","INCORRECTA", 1,'a');
        Correction correction = answer.getCorrection();
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    public void test03ResponseScoreAssignedCorrectly(){
        Player player = new Player("Mario", 5);
        Answer answer = new Answer("Verdadero","Correcta", 1,'a');
        player.addScore(1);
        int respuestaFinal = player.getScore();

        assertEquals(respuestaFinal,6);

    }

    @Test
    public void test04MultipleIncorrectAnswersSubtractOne(){
        Answer answer = new Answer("Falso","Inorrecta", -1,'a');
        Player player = new Player("Luigi", 4);

        player.subtractScore(1);
        player.subtractScore(1);
        player.subtractScore(1);

        int respuestaFinal = player.getScore();

        assertEquals(respuestaFinal,1);

    }
}