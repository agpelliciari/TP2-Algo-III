package tp2.clases;

public interface PowerState {

    public abstract void activate(Power power);

    public abstract void deactivate(Power power);

    public abstract void use(Power power);

    public abstract boolean isActive();
}
