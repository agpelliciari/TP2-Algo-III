package tp2.clases.exceptions;

public class UsedPowerException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Ese poder ya fue usado y no puede volver a seleccionarse";
    }
}
