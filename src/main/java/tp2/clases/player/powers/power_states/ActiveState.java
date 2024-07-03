package tp2.clases.player.powers.power_states;

import tp2.clases.player.powers.Power;

public class ActiveState implements PowerState {

    private Power power;

    public ActiveState(Power power) {
        this.power = power;
    }

    @Override
    public void activate(Power power) {
        power.setState(new ActiveState(power));
    }

    @Override
    public void deactivate(Power power) {
        power.setState(new UsedState(power));
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isUsed() {
        return false;
    }
}
