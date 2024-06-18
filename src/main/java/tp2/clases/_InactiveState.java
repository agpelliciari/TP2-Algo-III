package tp2.clases;

public class _InactiveState implements PowerState {
    private Power power;

    public _InactiveState(Power power) {
        this.power = power;
    }

    @Override
    public void activate(Power power) {
        power.setState(new _ActiveState(power));
    }

    @Override
    public void deactivate(Power power) {
        //Already inactive, do nothing
    }

    @Override
    public void use(Power power) {
        ///....
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
