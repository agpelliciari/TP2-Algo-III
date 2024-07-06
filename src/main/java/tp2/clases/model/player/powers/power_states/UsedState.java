package tp2.clases.model.player.powers.power_states;

import tp2.clases.model.player.powers.Power;

public class UsedState implements PowerState {

    private Power power;

    public UsedState(Power power) {
        this.power = power;
    }

    @Override
    public void activate(Power power) {
        power.setState(new ActiveState(power));
    }

    @Override
    public void deactivate(Power power) {}

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isUsed() {
        return true;
    }
}
