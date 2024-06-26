package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import tp2.clases.screens.PlayersInputScreen;

import java.util.function.Consumer;

public class ConfirmButtonHandler implements EventHandler<ActionEvent> {

    private PlayersInputScreen playersInputScreen;
    private Consumer<Integer> numberOfPlayersConsumer;

    public ConfirmButtonHandler(PlayersInputScreen playersInputScreen, Consumer<Integer> numberOfPlayersConsumer) {
        this.playersInputScreen = playersInputScreen;
        this.numberOfPlayersConsumer = numberOfPlayersConsumer;
    }

    @Override
    public void handle(ActionEvent event) {
        playersInputScreen.confirmNumberOfPlayers(numberOfPlayersConsumer);
    }
}

