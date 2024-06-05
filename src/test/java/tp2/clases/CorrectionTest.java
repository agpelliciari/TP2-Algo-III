package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CorrectionTest {
    @Test
    public void case01TestAsignarCorreccionCorrecta() {
        Correction correction = Correction.asignarCorreccion("correcta");
        assertTrue(correction instanceof Correct);
    }

    @Test
    public void case02TestAsignarCorreccionIncorrecta() {
        Correction correction = Correction.asignarCorreccion("incorrecta");
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    public void case03TestAsignarCorreccionDesconocida() {
        Correction correction = Correction.asignarCorreccion("desconocida");
        assertNull(correction);
    }

    @Test
    public void case04IngresoEnMayusculasYMinusculasYLuegoCambioEIgualmenteAsignaLaClaseCorrecta() {
        Correction correction = new Correction("CoRRectA");
        correction.setCorreccion("INCORRECTA");
        assertEquals("incorrecta", correction.getCorreccion());
    }

    @Test
    public void case05IngresoEnMayusculasYAsignaLaClaseCorrectamente() {
        Correction correction = new Correction("CORRECTA");
        assertEquals("correcta", correction.getCorreccion());
    }
}
