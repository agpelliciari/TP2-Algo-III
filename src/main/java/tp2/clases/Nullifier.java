package tp2.clases;

import tp2.clases.exceptions.UsedPowerException;

public class Nullifier extends Power {

    private PowerState state;

    public Nullifier() {
        this.state = new InactiveState(this);
        this.name = "Anulador";
    }

    public void apply(Score score) {
        if(!isUsed()) {
            score.cancelScore();
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

        if (isActive()) {
            state = new UsedState(this);
        }
    }
}
