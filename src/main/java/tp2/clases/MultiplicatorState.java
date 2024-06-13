package tp2.clases;

public interface MultiplicatorState {
    void apply(Player player);
    boolean isActive();
    void activate(Multiplicator multiplicator);
    void deactivate(Multiplicator multiplicator);
}
