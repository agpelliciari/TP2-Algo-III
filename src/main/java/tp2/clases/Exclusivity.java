package tp2.clases;

public class Exclusivity {
    private boolean bool;
    private int multiplier;

    public Exclusivity(boolean bool) {
        this.bool = bool;
        multiplier = 2;
    }

    public boolean getBool() {
        return bool;
    }

    public int getMultiplier() {
        return multiplier;
    }
}