package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncorrectaTest {

    @Test
    public void test01CreoInstanciaDeIncorrectaYCheckeoQueEstaBienInstanciada() {
        Correccion correccion = new Incorrecta();
        assertTrue(correccion instanceof Incorrecta);
    }

    @Test
    public void test02CreoInstanciaDeIncorrectaYCheckeoQueEstaBienInstanciada() {
        Correccion correccion = new Incorrecta();
        assertEquals("incorrecta", correccion.getCorreccion());
    }
}