package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.questions.choice.corrections.types.Correction;
import tp2.clases.model.questions.choice.corrections.types.Incorrect;

import static org.junit.jupiter.api.Assertions.*;

public class IncorrectTest {

    @Test
    public void test01CreateInstanceOfIncorrectAndCheckIfItIsCorrectlyInstanciated() {
        Correction correction = new Incorrect();
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    public void test02CreateInstanceOfIncorrectAndCheckIfItIsCorrectlyInstanciated() {
        Correction correction = new Incorrect();
        assertEquals(false, correction.isCorrect());
    }
}