package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RespuestaTest {
    @Test
    public void case01RespuestaCorrectaIniciadaCorrectamente(){

        Respuesta respuesta = new Respuesta("Verdadero","Correcta", 1);
        Correccion correccion = respuesta.getCorreccion();
        assertTrue(correccion instanceof Correcta);

    }

    @Test
    public void case02RespuestaConCorreccionEnMayusclasIniciadaCorrectamente(){
        Respuesta respuesta = new Respuesta("Falso","INCORRECTA", 1);
        Correccion correccion = respuesta.getCorreccion();
        assertTrue(correccion instanceof Incorrecta);
    }

    @Test
    public void case03PuntajeDeRespuestaAsignadoCorrectamente(){
        Jugador jugador = new Jugador("Mario", 5);
        Respuesta respuesta = new Respuesta("Verdadero","Correcta", 1);
        respuesta.asignarPuntaje(jugador);
        int respuestaFinal = jugador.getPuntaje();

        assertEquals(respuestaFinal,6);

    }

    @Test
    public void case04AVariasRespuestasIncorrectasSeLeRestaMenosUno(){
        Respuesta respuesta = new Respuesta("Falso","Inorrecta", -1);
        Jugador jugador = new Jugador("Luigi", 4);

        respuesta.asignarPuntaje(jugador);
        respuesta.asignarPuntaje(jugador);
        respuesta.asignarPuntaje(jugador);

        int respuestaFinal = jugador.getPuntaje();

        assertEquals(respuestaFinal,1);

    }
}