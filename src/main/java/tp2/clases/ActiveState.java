package tp2.clases;

public class ActiveState implements MultiplicatorState {

    private final Multiplicator multiplicator;

    public ActiveState(Multiplicator multiplicator) {
        this.multiplicator = multiplicator;
    }

    @Override
    public void apply(Player player) {
        multiplicator.setState(new UsedState(multiplicator));
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void activate(Multiplicator multiplicator) {
        // Already active
    }

    @Override
    public void deactivate(Multiplicator multiplicator) {
        multiplicator.setState(new UsedState(multiplicator));
    }
}

