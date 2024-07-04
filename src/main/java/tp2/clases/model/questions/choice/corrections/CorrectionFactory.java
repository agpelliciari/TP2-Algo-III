package tp2.clases.model.questions.choice.corrections;

import tp2.clases.model.questions.choice.corrections.types.Incorrect;
import tp2.clases.model.questions.choice.corrections.types.Correct;
import tp2.clases.model.questions.choice.corrections.types.Correction;

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
