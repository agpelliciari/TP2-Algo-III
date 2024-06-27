package tp2.clases.screens;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.scene.control.ScrollPane;


public class PlayersNamesInputScreen extends VBox {

    private int numberOfPlayers;
    private ArrayList<TextField> playerNameTextFields;
    private Consumer<ArrayList<String>> playersNamesConsumer;
    private ScrollPane scrollPane;
    private VBox contentVBox;
    private Button confirmButton;

    public PlayersNamesInputScreen(int numberOfPlayers, Consumer<ArrayList<String>> playersNamesConsumer) {
        super();
        this.numberOfPlayers = numberOfPlayers;
        this.playersNamesConsumer = playersNamesConsumer;
        this.playerNameTextFields = new ArrayList<>();
        this.scrollPane = new ScrollPane();
        this.contentVBox = new VBox(10);
        this.confirmButton = createConfirmButton();

        scrollPane.setContent(contentVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        setStyle("-fx-background-color: #f0f0f0;");

        createPlayerNameInputFields();
        getChildren().addAll(scrollPane, confirmButton);
    }

    private void createPlayerNameInputFields() {
        for (int i = 0; i < numberOfPlayers; i++) {
            displayPlayerNameInputFields(i + 1);
        }
    }

    private void displayPlayerNameInputFields(int indexCurrentPlayer) {
        Label label = createPlayerNameInputLabel(indexCurrentPlayer);
        contentVBox.getChildren().add(label);

        TextField playerNameTextField = createPlayerNameTextField();
        contentVBox.getChildren().add(playerNameTextField);
        playerNameTextFields.add(playerNameTextField);
    }

    private Label createPlayerNameInputLabel(int indexCurrentPlayer) {
        Label label = new Label("Ingrese el nombre del jugador " + indexCurrentPlayer + ":");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");

        VBox.setMargin(label, new Insets(0, 0, 5, 0)); // Márgenes en la parte inferior

        return label;
    }

    private TextField createPlayerNameTextField() {
        TextField playerNameTextField = new TextField();
        playerNameTextField.setPromptText("Nombre del jugador");
        playerNameTextField.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        VBox.setMargin(playerNameTextField, new Insets(0, 0, 10, 0));

        return playerNameTextField;
    }

    private Button createConfirmButton() {
        Button confirmButton = new Button("Confirmar");
        confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #0b4163; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnMouseExited(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnAction(e -> handleConfirmButton());

        VBox.setMargin(confirmButton, new Insets(20, 0, 0, 0));

        return confirmButton;
    }

    private void handleConfirmButton() {
        ArrayList<String> playersNames = new ArrayList<>();
        ArrayList<String> invalidPlayersNames = new ArrayList<>();
        boolean allNamesValid = true;
        for (int i = 0; i < playerNameTextFields.size(); i++) {
            TextField textField = playerNameTextFields.get(i);
            String playerName = textField.getText();
            if (!isValidName(playerName)) {
                invalidPlayersNames.add(String.valueOf(i+1));
                allNamesValid = false;
//                break;
            }
            playersNames.add(playerName);
        }

        if (allNamesValid) {
            playersNamesConsumer.accept(playersNames);
        }
        else{
            String invalidPlayers = String.join(",", invalidPlayersNames);
            showErrorDialog("Por favor jugador/es número " + invalidPlayers + " ingrese/n un nombre de jugador válido.");

        }
    }



    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}

