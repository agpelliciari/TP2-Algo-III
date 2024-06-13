package tp2.clases;

public class Multiplicator {

    private MultiplicatorState state;
    private final int factor;
    private boolean used;

    public Multiplicator(int factor) {
        this.factor = factor;
        this.state = new InactiveState(this);
        this.used = false;
    }


    public int getFactor() {
        return factor;
    }

    public void setState(MultiplicatorState state) {
        this.state = state;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public void apply(Player player) {
        state.apply(player);
    }

    public boolean isActive() {
        return state.isActive();
    }

    public boolean wasUsed() {
        return used;
    }

    public void activate() {
        state.activate(this);
    }

    public void deactivate() {
        state.deactivate(this);
    }
}
