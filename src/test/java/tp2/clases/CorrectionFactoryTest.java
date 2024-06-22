package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CorrectionFactoryTest {
    @Test
    public void test01AssignCorrectCorrection() {
        Correction correction = CorrectionFactory.assignCorrection("Correcta");
        assertInstanceOf(Correct.class, correction);
    }

    @Test
    public void test02AssignIncorrectCorrection() {
        Correction correction = CorrectionFactory.assignCorrection("Incorrecta");
        assertInstanceOf(Incorrect.class, correction);
    }

    @Test
    public void test03AssignUnknownCorrection() {
        assertThrows(IllegalArgumentException.class, () -> {CorrectionFactory.assignCorrection("Desconocida");});
    }
}
