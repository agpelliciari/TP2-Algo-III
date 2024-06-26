package tp2.clases.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import tp2.clases.screens.PlayersInputScreen;

import java.util.function.Consumer;

public class ConfirmButtonHandler implements EventHandler<ActionEvent> {

    private PlayersInputScreen playersInputScreen;
    private Consumer<Integer> numberOfPlayersConsumer;
    private Consumer<Integer> numberOfQuestionsConsumer;
    private Consumer<Integer> numberOfPointsConsumer;

    public ConfirmButtonHandler(PlayersInputScreen playersInputScreen, Consumer<Integer> numberOfPlayersConsumer, Consumer<Integer> numberOfQuestionsConsumer, Consumer<Integer> numberOfPointsConsumer) {
        this.playersInputScreen = playersInputScreen;
        this.numberOfPlayersConsumer = numberOfPlayersConsumer;
        this.numberOfQuestionsConsumer = numberOfQuestionsConsumer;
        this.numberOfPointsConsumer = numberOfPointsConsumer;
    }

    @Override
    public void handle(ActionEvent event) {
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfPlayersTextField(), numberOfPlayersConsumer, 1);
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfQuestionsTextField(), numberOfQuestionsConsumer, 1);
        playersInputScreen.confirmNumber(playersInputScreen.getNumberOfPointsTextField(), numberOfPointsConsumer, 1);
    }
}

