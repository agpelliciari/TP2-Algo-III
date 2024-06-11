package tp2.clases;

public class Option {
    private int number;
    private String text;
    private Correction correction;

    public Option(int number, String text, Correction correction) {
        this.number = number;
        this.text = text;
        this.correction = correction;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public Correction getCorrection() {
        return correction;
    }
}
