import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CorreccionTest {
    @Test
    public void case01TestAsignarCorreccionCorrecta() {
        Correccion correccion = Correccion.asignarCorreccion("correcta");
        assertTrue(correccion instanceof Correcta);
    }

    @Test
    public void case02TestAsignarCorreccionIncorrecta() {
        Correccion correccion = Correccion.asignarCorreccion("incorrecta");
        assertTrue(correccion instanceof Incorrecta);
    }

    @Test
    public void case03TestAsignarCorreccionDesconocida() {
        Correccion correccion = Correccion.asignarCorreccion("desconocida");
        assertNull(correccion);
    }

    @Test
    public void case04IngresoEnMayusculasYMinusculasYLuegoCambioEIgualmenteAsignaLaClaseCorrecta() {
        Correccion correccion = new Correccion("CoRRectA");
        correccion.setCorreccion("INCORRECTA");
        assertEquals("incorrecta", correccion.getCorreccion());
    }

    @Test
    public void case05IngresoEnMayusculasYAsignaLaClaseCorrectamente() {
        Correccion correccion = new Correccion("CORRECTA");
        assertEquals("correcta", correccion.getCorreccion());
    }
}
