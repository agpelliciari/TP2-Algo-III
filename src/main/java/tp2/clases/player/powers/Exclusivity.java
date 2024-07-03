package tp2.clases.player.powers;

import tp2.clases.player.powers.power_states.InactiveState;
import tp2.clases.player.powers.power_states.PowerState;

public class Exclusivity extends Power {

    private PowerState state;
    private int number;
    private final int factor;

    public Exclusivity() {
        this.state = new InactiveState(this);
        this.name = "Exclusividad";
        number = 2;
        factor = 2;
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

    public int getFactor() {
        return factor;
    }

    public void decreaseNumber() {
        if (number > 0)
            number -= 1;
    }
}