package tp2.clases;

public abstract class Power {

    protected PowerState state;

    public void setState(PowerState state){
        this.state = state;
    }

    public boolean isActive(){
        return state.isActive();
    }
}
