package tp2.clases;

import java.util.Objects;

//public abstract class Correccion
public class Correccion {
    private String correccion;

    public void setCorreccion(String correccion) {
        this.correccion = correccion.toLowerCase();
    }

    public String getCorreccion() {
        return correccion;
    }

    public static Correccion asignarCorreccion(String correccion) {
        correccion = correccion.toLowerCase();
        if (Objects.equals(correccion, "correcta")) {
            return new Correcta();
        } else if (Objects.equals(correccion, "incorrecta")) {
            return new Incorrecta();
        }
        return null;
    }

    public Correccion(String correccion) {
        setCorreccion(correccion);
    }

//    public abstract void asignarPuntaje(Jugador jugador);
}
