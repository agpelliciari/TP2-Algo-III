package tp2.clases.handlers;

import javafx.scene.control.Alert;
import tp2.clases.Player;
import tp2.clases.exceptions.UsedPowerException;

public class MultiplicatorButtonHandler {

    String factorString;

    public MultiplicatorButtonHandler(String factorString) {
        this.factorString = factorString;
    }

    public void selectMultiplier(Player player, boolean selectedMultiplier) {

        int factor = handleMultiplicator(this.factorString, selectedMultiplier);

        if (selectedMultiplier) {
            try {
                player.useMultiplicator(factor);
            } catch (UsedPowerException e) {
                showErrorDialog(e.getMessage());
            }
        }
    }
    public int handleMultiplicator(String factorText, boolean selectedMultiplier) {
        if (factorText.matches("[23]")) {  // Validar que el factor sea 2 o 3
            return Integer.parseInt(factorText);
        } else {
            if (selectedMultiplier) {
                showErrorDialog("Por favor ingrese un multiplicador válido (2 o 3).");
            }
//            else if (factorText.matches("") && selectedMultiplier) {
//                showErrorDialog("Por favor elija un multiplicador (x2 o x3)");
//            }
        }
        return 0;
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
