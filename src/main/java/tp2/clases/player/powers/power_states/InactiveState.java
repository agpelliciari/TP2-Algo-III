package tp2.clases.player.powers.power_states;

import tp2.clases.player.powers.Power;

public class InactiveState implements PowerState {

    private Power power;

    public InactiveState(Power power) {
        this.power = power;
    }

    @Override
    public void activate(Power power) {
        power.setState(new ActiveState(power));
    }

    @Override
    public void deactivate(Power power) {
    }

    @Override
    public void use(Power power) {
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isUsed() {
        return false;
    }
}
