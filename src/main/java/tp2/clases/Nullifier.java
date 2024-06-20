package tp2.clases;

import tp2.clases.exceptions.UsedPowerException;

public class Nullifier extends Power {

    private PowerState state;
    private boolean used;


    public Nullifier(){
        this.state = new InactiveState(this);
        this.used = false;
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
        score.cancelScore();
    }

    public boolean isActive() {
        return state.isActive();
    }
}
