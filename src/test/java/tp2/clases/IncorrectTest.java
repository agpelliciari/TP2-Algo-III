package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.questions.choice.corrections.types.Correction;
import tp2.clases.model.questions.choice.corrections.types.Incorrect;

import static org.junit.jupiter.api.Assertions.*;

public class IncorrectTest {

    @Test
    // Se chequea que la instanciacion sea correcta
    public void test01CreateInstanceOfIncorrectAndCheckIfItIsCorrectlyInstanciated() {
        // Arrange
        Correction correction = new Incorrect();

        // Assert
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    // Se chequea que la instanciacion sea correcta
    public void test02CreateInstanceOfIncorrectAndCheckIfItIsCorrectlyInstanciated() {
        // Arrange
        Correction correction = new Incorrect();

        // Assert
        assertEquals(false, correction.isCorrect());
    }
}