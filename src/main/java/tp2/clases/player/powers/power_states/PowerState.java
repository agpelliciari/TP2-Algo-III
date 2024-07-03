package tp2.clases.player.powers.power_states;

import tp2.clases.player.powers.Power;

public interface PowerState {

    void activate(Power power);

    void deactivate(Power power);

    boolean isActive();

    boolean isUsed();
}
