package tp2.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    @Test
    public void case01RespuestaCorrectaIniciadaCorrectamente(){

        Answer answer = new Answer("Verdadero","Correcta", 1);
        Correction correction = answer.getCorreccion();
        assertTrue(correction instanceof Correct);

    }

    @Test
    public void case02RespuestaConCorreccionEnMayusclasIniciadaCorrectamente(){
        Answer answer = new Answer("Falso","INCORRECTA", 1);
        Correction correction = answer.getCorreccion();
        assertTrue(correction instanceof Incorrect);
    }

    @Test
    public void case03PuntajeDeRespuestaAsignadoCorrectamente(){
        Player player = new Player("Mario", 5);
        Answer answer = new Answer("Verdadero","Correcta", 1);
        answer.asignarPuntaje(player);
        int respuestaFinal = player.getScore();

        assertEquals(respuestaFinal,6);

    }

    @Test
    public void case04AVariasRespuestasIncorrectasSeLeRestaMenosUno(){
        Answer answer = new Answer("Falso","Inorrecta", -1);
        Player player = new Player("Luigi", 4);

        answer.asignarPuntaje(player);
        answer.asignarPuntaje(player);
        answer.asignarPuntaje(player);

        int respuestaFinal = player.getScore();

        assertEquals(respuestaFinal,1);

    }
}