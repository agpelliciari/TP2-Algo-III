package tp2.clases;

public class Respuesta {
    public String contenido;
    public Correccion correccion;
    public int puntaje;

    public Respuesta(String contenido, String correccion, int puntaje) {
        this.contenido = contenido;
        this.correccion = Correccion.asignarCorreccion(correccion);
        this.puntaje = puntaje;
    }

    public void asignarPuntaje(Jugador unJugador) {
//        correccion.asignarPuntaje(unJugador);
        unJugador.setPuntaje(unJugador.getPuntaje() + puntaje);
    }

    public Correccion getCorreccion() {
        return correccion;
    }

    public String getContenido() {
        return contenido;
    }

    public int getPuntaje() {
        return puntaje;
    }
}

