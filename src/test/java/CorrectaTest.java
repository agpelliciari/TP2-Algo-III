import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CorrectaTest {

    @Test
    public void test01CreoInstanciaDeCorrectaYCheckeoQueEstaBienInstanciada() {
        Correccion correccion = new Correcta();
        assertTrue(correccion instanceof Correcta);
    }

    @Test
    public void test02CreoInstanciaDeCorrectaYCheckeoQueEstaBienInstanciada() {
        Correccion correccion = new Correcta();
        assertEquals("correcta", correccion.getCorreccion());
    }
}