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
import java.util.List;


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
                invalidPlayersNames.add(playerName);
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


//public class PlayersNamesInputScreen extends VBox {
//    private int numberOfPlayers;
//    private ArrayList<TextField> playerNameTextFields;
//    private Consumer<ArrayList<String>> playersNamesConsumer;
//    private ScrollBarHandler scrollBarHandler;
//
//    public PlayersNamesInputScreen(int numberOfPlayers, Consumer<ArrayList<String>> playersNamesConsumer) {
//        super(20); // Espacio entre elementos
//        this.numberOfPlayers = numberOfPlayers;
//        this.playersNamesConsumer = playersNamesConsumer;
//        this.playerNameTextFields = new ArrayList<>();
//        this.scrollBarHandler = new ScrollBarHandler();
//        setAlignment(Pos.CENTER);
//        setPadding(new Insets(20));
//
//        // Estilos para el VBox
//        setStyle("-fx-background-color: #f0f0f0;");
//
//
//        createPlayerNameInputFields();
//        getChildren().add(scrollBarHandler);
//
//    }
//
//
//
//    private void createPlayerNameInputFields() {
//        for (int i = 0; i < numberOfPlayers; i++) {
//            displayPlayerNameInputFields(i+1);
//            //updatePlayerNameInputFields(i+1);
//        }
//    }
//
//    /*private void updatePlayerNameInputFields(int indexCurrentPlayer) {
//        label.setText("Ingrese el nombre del jugador " + indexCurrentPlayer + ":");
//        playerNameTextField.clear();
//    }*/
//
//    private void displayPlayerNameInputFields(int indexCurrentPlayer) {
//
//        Label label = createPlayerNameInputLabel(indexCurrentPlayer);
//        scrollBarHandler.addNode(label);
//
//        TextField playerNameTextField = createPlayerNameTextField();
//        scrollBarHandler.addNode(playerNameTextField);
//
//        Button confirmButton = createConfirmButton(playerNameTextField);
//        scrollBarHandler.addNode(confirmButton);
//
//
////        Label label = createPlayerNameInputLabel(indexCurrentPlayer);
////        getChildren().add(label);
////
////        TextField playerNameTextField = createPlayerNameTextField();
////        getChildren().add(playerNameTextField);
////
////        Button confirmButton = createConfirmButton(playerNameTextField);
////        getChildren().add(confirmButton);
//
//        playerNameTextField.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                handlePlayerNameEntry(playerNameTextField);
//            }
//        });
//    }
//
//
//    private Label createPlayerNameInputLabel(int indexCurrentPlayer) {
//        Label label = new Label("Ingrese el nombre del jugador " + indexCurrentPlayer + ":");
//        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
//        VBox.setMargin(label, new Insets(0, 0, 5, 0));
//        return label;
//    }
//
//    private TextField createPlayerNameTextField() {
//        TextField playerNameTextField = new TextField();
//        playerNameTextField.setPromptText("Nombre del jugador");
//        playerNameTextField.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");
//        playerNameTextFields.add(playerNameTextField);
//        VBox.setMargin(playerNameTextField, new Insets(0, 0, 10, 0));
//
//        return playerNameTextField;
//    }
//
//    private Button createConfirmButton(TextField playerNameTextField) {
//        Button confirmButton = new Button("Confirmar");
//        confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
//        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #0b4163; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
//        confirmButton.setOnMouseExited(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
//        confirmButton.setOnAction(e -> handlePlayerNameEntry(playerNameTextField));
//        VBox.setMargin(confirmButton, new Insets(0, 0, 20, 0));
//
//        return confirmButton;
//    }
//
//    /*private void handlePlayerNameEntry2(TextField playerNameTextField) {
//        confirmPlayersName(playerNameTextField);
//        if (playerNameEntered()) {
//            playersNames.add(playerNameTextField.getText());
//            playersNamesConsumer.accept(playersNames);
//        }
//    }*/
//
//    private void handlePlayerNameEntry(TextField playerNameTextField) {
//        confirmPlayersName(playerNameTextField);
//        if (allPlayersNamesEntered()) {
//            ArrayList<String> playersNames = new ArrayList<>();
//            for (TextField textField : playerNameTextFields) {
//                playersNames.add(textField.getText());
//            }
//
//            playersNamesConsumer.accept(playersNames);
//        }
//    }
//
//    private void confirmPlayersName(TextField playerNameTextField) {
//        String playerName = playerNameTextField.getText();
//        if (!playerName.isEmpty() && isValidName(playerName)) {
//            playerNameTextField.setDisable(true);
//        } else {
//            showErrorDialog("Por favor ingrese un nombre de jugador válido.");
//        }
//    }
//
//    /*private boolean playerNameEntered() {
//        return playerNameTextField.isDisabled();
//    }*/
//
//    private boolean allPlayersNamesEntered() {
//        for (TextField textField : playerNameTextFields) {
//            if (!textField.isDisabled()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void showErrorDialog(String errorMessage) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(null);
//        alert.setContentText(errorMessage);
//        alert.showAndWait();
//    }
//
//    private boolean isValidName(String name) {
//        return name.matches("[a-zA-Z]+");
//    }
//}
