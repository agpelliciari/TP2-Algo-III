package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.questions.choice.corrections.types.Correct;
import tp2.clases.questions.choice.corrections.types.Correction;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CorrectTest {

    @Test
    public void test01CreateInstanceOfCorrectAndCheckIfItIsCorrectlyInstanciated() {
        Correction correction = new Correct();
        assertTrue(correction instanceof Correct);
    }

    @Test
    public void test02CreateInstanceOfCorrectAndCheckIfItIsCorrectlyInstanciated() {
        Correction correction = new Correct();
        assertEquals(true, correction.isCorrect());
    }
}