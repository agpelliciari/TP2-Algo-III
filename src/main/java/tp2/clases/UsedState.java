package tp2.clases;

public class UsedState implements MultiplicatorState {
    private final Multiplicator multiplicator;

    public UsedState(Multiplicator multiplicator) {
        this.multiplicator = multiplicator;
    }

    @Override
    public void apply(Player player) {
        throw new IllegalStateException("Multiplicator has already been used.");
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void activate(Multiplicator multiplicator) {
        // Cannot activate a used multiplicator
    }

    @Override
    public void deactivate(Multiplicator multiplicator) {
        // Cannot deactivate a used multiplicator
    }
}
