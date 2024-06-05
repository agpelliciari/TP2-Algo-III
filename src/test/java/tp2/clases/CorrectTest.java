package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CorrectTest {

    @Test
    public void test01CreoInstanciaDeCorrectaYCheckeoQueEstaBienInstanciada() {
        Correction correction = new Correct();
        assertTrue(correction instanceof Correct);
    }

    @Test
    public void test02CreoInstanciaDeCorrectaYCheckeoQueEstaBienInstanciada() {
        Correction correction = new Correct();
        assertEquals("correcta", correction.getCorreccion());
    }
}