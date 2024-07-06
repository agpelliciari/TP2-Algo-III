package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.questions.choice.corrections.types.Correct;
import tp2.clases.model.questions.choice.corrections.types.Correction;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CorrectTest {

    @Test
    // Correcta instanciación de la clase Correct
    public void test01CreateInstanceOfCorrectAndCheckIfItIsCorrectlyInstanciated() {
        Correction correction = new Correct();
        assertTrue(correction instanceof Correct);
    }

    @Test
    // Correcta instanciación de la clase Correct mediante el método isCorrect
    public void test02CreateInstanceOfCorrectAndCheckIfItIsCorrectlyInstanciatedWithMethod() {
        Correction correction = new Correct();
        assertEquals(true, correction.isCorrect());
    }
}