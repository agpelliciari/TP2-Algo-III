package tp2.clases;

public class InactiveState implements MultiplicatorState {
    private final Multiplicator multiplicator;

    public InactiveState(Multiplicator multiplicator) {
        this.multiplicator = multiplicator;
    }

    @Override
    public void apply(Player player) {
        throw new IllegalStateException("Multiplicator is not active.");
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void activate(Multiplicator multiplicator) {
        multiplicator.setState(new ActiveState(multiplicator));
    }

    @Override
    public void deactivate(Multiplicator multiplicator) {
        // Already inactive, do nothing
    }

}
