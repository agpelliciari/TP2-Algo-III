package tp2.clases;

import org.junit.jupiter.api.Test;
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
        assertEquals("incorrecta", correction.getCorrection());
    }
}