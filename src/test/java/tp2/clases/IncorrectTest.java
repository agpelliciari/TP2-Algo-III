package tp2.clases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncorrectTest {

    @Test
    public void test01CreoInstanciaDeIncorrectaYCheckeoQueEstaBienInstanciada() {
        Correction correction = new Incorrect();
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    public void test02CreoInstanciaDeIncorrectaYCheckeoQueEstaBienInstanciada() {
        Correction correction = new Incorrect();
        assertEquals("incorrecta", correction.getCorreccion());
    }
}