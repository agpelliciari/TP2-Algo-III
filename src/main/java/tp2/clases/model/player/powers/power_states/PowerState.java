package tp2.clases.model.player.powers.power_states;

import tp2.clases.model.player.powers.Power;

public interface PowerState {

    void activate(Power power);

    void deactivate(Power power);

    boolean isActive();

    boolean isUsed();
}
