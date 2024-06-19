package tp2.clases;

public class Exclusivity {

    private boolean bool;
    private int number;
    private final int multiplier;

    public Exclusivity(boolean bool) {
        this.bool = bool;
        number = 2;
        multiplier = 2;
    }

    public boolean getBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public int getNumber() {
        return number;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void decreaseNumber() {
        if (number > 0) {
            number -= 1;
        }
    }
}