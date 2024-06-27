package tp2.clases;

public class Multiplicator extends Power{

    private PowerState state;
    private final int factor;

    public Multiplicator(Integer factor) {
        this.factor = factor;
        this.state = new InactiveState(this);
        this.name = "Multiplicador X" + factor.toString();
    }

    public int getFactor() {
        return factor;
    }

    public void setState(PowerState state) {
        this.state = state;
    }

    public boolean isActive() {
        return state.isActive();
    }

    public boolean isUsed() {
        return state.isUsed();
    }

    public void activate() {
        state.activate(this);
    }

    public void deactivate() {
        state.deactivate(this);
    }
}
