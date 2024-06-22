package tp2.clases;

public class CorrectionFactory {

    public static Correction assignCorrection(String correction) {
        correction = correction.toLowerCase();
        return switch (correction) {
            case "correcta" -> new Correct();
            case "incorrecta" -> new Incorrect();
            default -> throw new IllegalArgumentException();
        };
    }
}
