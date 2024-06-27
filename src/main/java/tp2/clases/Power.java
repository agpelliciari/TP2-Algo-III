package tp2.clases;

public abstract class Power {

    protected PowerState state;
    protected  String name;

    public void setState(PowerState state) {
        this.state = state;
    }

    public boolean isActive() {
        return state.isActive();
    }

    public String getName() { return name; }
}