package tp2.clases.player.powers.power_states;

import tp2.clases.player.powers.Power;

public interface PowerState {

    public abstract void activate(Power power);

    public abstract void deactivate(Power power);

    public abstract void use(Power power);

    public abstract boolean isActive();

    public abstract boolean isUsed();
}
