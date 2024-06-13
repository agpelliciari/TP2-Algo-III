package tp2.clases;

public class _ActiveState implements PowerState {
    private Power power;

    public _ActiveState(Power power) {
        this.power = power;
    }

    @Override
    public void activate(Power power) {
        //Already active
    }

    @Override
    public void deactivate(Power power) {
        power.setState(new _InactiveState(power));
    }

    @Override
    public void use(Power power) {
        ////
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
