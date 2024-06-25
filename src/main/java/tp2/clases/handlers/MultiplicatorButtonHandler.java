package tp2.clases.handlers;
import java.util.function.BiConsumer;
import javafx.scene.control.Alert;

public class MultiplicatorButtonHandler {
    private BiConsumer<Boolean, Integer> applyMultiplicatorConsumer;

    public MultiplicatorButtonHandler(BiConsumer<Boolean, Integer> applyMultiplicatorConsumer) {
        this.applyMultiplicatorConsumer = applyMultiplicatorConsumer;
    }

    public void handleMultiplicator(boolean useMultiplicator, String factorText) {
        if (factorText.matches("[23]")) {  // Validar que el factor sea 2 o 3
            int factor = Integer.parseInt(factorText);
            applyMultiplicatorConsumer.accept(useMultiplicator, factor);
        } else {
            showErrorDialog("Por favor ingrese un factor válido (2 o 3).");
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
