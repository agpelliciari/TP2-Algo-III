package tp2.clases.player.powers;

import tp2.clases.player.powers.power_states.PowerState;

public abstract class Power {

    protected PowerState state;
    protected  String name;

    public void setState(PowerState state) {
        this.state = state;
    }

    public boolean isActive() {
        return state.isActive();
    }

    public String getName() { return name; }
}