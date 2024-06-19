package tp2.clases.exceptions;

public class InvalidAnswerFormatException extends RuntimeException {

    public InvalidAnswerFormatException() {
        super();
    }

    public InvalidAnswerFormatException(String message) {
        super(message);
    }
}
