package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ContinueButtonEventHandler implements EventHandler<ActionEvent> {

    private final ActionHandler continueHandler;

    public ContinueButtonEventHandler(ActionHandler continueHandler) {
        this.continueHandler = continueHandler;
    }

    @Override
    public void handle(ActionEvent event) {
        continueHandler.execute();
    }
}