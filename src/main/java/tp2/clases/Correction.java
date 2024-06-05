package tp2.clases;

import java.util.Objects;

public class Correction {
    private String correction;

    public void setCorrection(String correction) {
        this.correction = correction.toLowerCase();
    }

    public String getCorrection() {
        return correction;
    }

    public static Correction assignCorrection(String correction) {
        correction = correction.toLowerCase();
        if (Objects.equals(correction, "correcta")) {
            return new Correct();
        } else if (Objects.equals(correction, "incorrecta")) {
            return new Incorrect();
        }
        return null;
    }

    public Correction(String correccion) {
        setCorrection(correccion);
    }


}