package tp2.clases.handlers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class StartButtonEventHandler implements EventHandler<ActionEvent>{
    private final ActionHandler startHandler;
    private Button button;

    public StartButtonEventHandler(ActionHandler startHandler) {
        this.startHandler = startHandler;
    }

    @Override
    public void handle(ActionEvent event) {
        startHandler.execute();
    }
}
