package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CorrectionTest {
    @Test
    public void test01AssignCorrectCorrection() {
        Correction correction = Correction.assignCorrection("correcta");
        assertTrue(correction instanceof Correct);
    }

    @Test
    public void test02AssignIncorrectCorrection() {
        Correction correction = Correction.assignCorrection("incorrecta");
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    public void test03AssignUnknownCorrection() {
        Correction correction = Correction.assignCorrection("desconocida");
        assertNull(correction);
    }
    
}
