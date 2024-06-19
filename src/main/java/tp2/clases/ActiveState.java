package tp2.clases;

public class ActiveState implements PowerState {

    private Power power;

    public ActiveState(Power power) {
        this.power = power;
    }

    @Override
    public void activate(Power power) {}

    @Override
    public void deactivate(Power power) {
        power.setState(new UsedState(power));
    }

    @Override
    public void use(Power power) {}

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isUsed(){
        return false;
    }
}
