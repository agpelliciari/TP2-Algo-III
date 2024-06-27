package tp2.clases.player.powers.power_states;

import tp2.clases.exceptions.UsedPowerException;
import tp2.clases.player.powers.Power;

public class UsedState implements PowerState {

    private Power power;

    public UsedState(Power power) {
        this.power = power;
    }

    public void activate(Power power) {
        throw new UsedPowerException();
    }

    public void deactivate(Power power) {
        throw new UsedPowerException();
    }

    public void use(Power power) {}

    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isUsed() {
        return true;
    }
}
