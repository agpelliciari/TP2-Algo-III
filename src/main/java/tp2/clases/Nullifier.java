package tp2.clases;

public class Nullifier extends Power {
    private PowerState state;
    private boolean used;


    public Nullifier(){
        this.state = new InactiveState(this);
        this.used = false;
    }

    public void apply(Score score) {
        score.cancelScore();
        state = new ActiveState(this);
        used = true;
    }

    public boolean isUsed() {
        return used;
    }
}
