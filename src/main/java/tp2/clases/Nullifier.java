package tp2.clases;

public class Nullifier extends Power {
    private PowerState state;
    private boolean used;


    public Nullifier(){
        this.state = new _InactiveState(this);
        this.used = false;
    }

    public void apply(Score score) {
        score.cancelScore();
        state = new _ActiveState(this);
        used = true;
    }

    public boolean isUsed() {
        return used;
    }
}
