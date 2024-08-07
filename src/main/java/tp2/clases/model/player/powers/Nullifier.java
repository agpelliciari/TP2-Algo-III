package tp2.clases.model.player.powers;

import tp2.clases.exceptions.UsedPowerException;
import tp2.clases.model.player.powers.power_states.ActiveState;
import tp2.clases.model.player.powers.power_states.InactiveState;
import tp2.clases.model.player.powers.power_states.PowerState;
import tp2.clases.model.player.powers.power_states.UsedState;
import tp2.clases.model.player.score.Score;

public class Nullifier extends Power {

    private PowerState state;

    public Nullifier() {
        this.state = new InactiveState(this);
        this.name = "Anulador";
    }

    public void apply(Score score) {
        if (!isUsed()) {
            state = new ActiveState(this);
        }
        else {
            throw new UsedPowerException();
        }
    }

    public boolean isUsed() {
        return state.isUsed();
    }

    public void cancel(Score score) {
        if (!state.isActive()) {
            score.cancelScore();
        }
    }

    public boolean isActive() {
        return state.isActive();
    }

    public void disable(Score score) {
        score.restoreScore();
        if (isActive())
            state = new UsedState(this);
    }
}
