package tp2.clases;

public class Exclusivity extends Power {

    private PowerState state;
    private int number;
    private final int multiplier;

    public Exclusivity() {
        this.state = new InactiveState(this);
        number = 2;
        multiplier = 2;
    }

    public PowerState getState() {
        return state;
    }

    public void setState(PowerState state) {
        this.state = state;
    }

    public boolean isActive() {
        return state.isActive();
    }

    public void activate() {
        state.activate(this);
    }

    public void deactivate() {
        state.deactivate(this);
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