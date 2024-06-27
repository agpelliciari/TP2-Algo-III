package tp2.clases.player.powers;

import tp2.clases.player.powers.power_states.InactiveState;
import tp2.clases.player.powers.power_states.PowerState;

public class Multiplicator extends Power {

    private PowerState state;
    private final int factor;
    private boolean used;

    public Multiplicator(Integer factor) {
        this.factor = factor;
        this.state = new InactiveState(this);
        this.used = false;
        this.name = "Multiplicador X" + factor.toString();
    }

    public int getFactor() {
        return factor;
    }

    public void setState(PowerState state) {
        this.state = state;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public boolean isActive() {
        return state.isActive();
    }

    public boolean isUsed() {
        return state.isUsed();
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
