package tp2.clases.handlers;

import javafx.scene.control.Alert;
import tp2.clases.Player;
import tp2.clases.exceptions.UsedPowerException;

import java.util.ArrayList;

public class NullifierCheckBoxEventHandler {

    public void selectNullifier(Player player, ArrayList<Player> players, boolean selectedNullifier) {
        if (selectedNullifier) {
            try {
                player.useNullifier();
                notifyNullifierIsActive(players);
            } catch (UsedPowerException e) {
                showErrorDialog(e.getMessage());
            }
        }
    }

    private void notifyNullifierIsActive(ArrayList<Player> players) {
        for (Player player: players) {
            player.aNullifierIsActivated();
        }
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    /*public void deactivateNullifier(ArrayList<Player> players) {
        for (Player player: players) {
            player.disableNullifier();
        }
    }*/
}
