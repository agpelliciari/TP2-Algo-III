package tp2.clases.handlers;

import javafx.scene.control.Alert;
import tp2.clases.Game;
import tp2.clases.Player;
import tp2.clases.exceptions.UsedPowerException;

public class NullifierCheckBoxEventHandler {

    private Game game;

    public NullifierCheckBoxEventHandler(Game game) {
        this.game = game;
    }

    public void selectNullifier(Player player, boolean selectedNullifier) {
        if (selectedNullifier) {
            game.activateNullifier();
            try {
                player.useNullifier();
            } catch (UsedPowerException e) {
                showErrorDialog(e.getMessage());
            }
        }
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
