package tp2.clases.screens;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.function.Consumer;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.handlers.NamesInputButtonHandler;


public class PlayersNamesInputScreen extends VBox {

    Game game;
    Stage stage;

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
        //this.confirmButton = createConfirmButton(inputButtonHandler);

        scrollPane.setContent(contentVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(20);

        setStyle("-fx-background-color: #f0f0f0;");

        //createPlayerNameInputFields(game);
        getChildren().addAll(scrollPane, confirmButton);
    }

    public PlayersNamesInputScreen(Stage primaryStage, Scene gameScene, Game game) {
        super();

        this.stage = primaryStage;
        this.game = game;

        Image image = new Image("file:textura.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        this.playerNameTextFields = new ArrayList<>();

        this.confirmButton = new Button("Confirmar");
        confirmButton.setDisable(true);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        createPlayerNameInputFields();

        NamesInputButtonHandler inputButtonHandler = new NamesInputButtonHandler(game, gameScene, primaryStage, this);
        confirmButton.setOnAction(inputButtonHandler);

        getChildren().addAll(confirmButton);
    }

    private void createPlayerNameInputFields() {
        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            displayPlayerNameInputFields(i + 1);
        }
    }

    private void displayPlayerNameInputFields(int indexCurrentPlayer) {
        Label label = createPlayerNameInputLabel(indexCurrentPlayer);
        this.getChildren().add(label);

        TextField playerNameTextField = createPlayerNameTextField();
        this.getChildren().add(playerNameTextField);
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

        addValidationListener(playerNameTextField);

        VBox.setMargin(playerNameTextField, new Insets(0, 0, 10, 0));

        return playerNameTextField;
    }

    private void addValidationListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidName(newValue)) {
                textField.setStyle("-fx-border-color: green; -fx-border-width: 1px;");
            } else {
                textField.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
            }
            validateInputs();
        });
    }

    private void validateInputs() {
        boolean allValid = true;
        for (TextField textField : playerNameTextFields) {
            if (!isValidName(textField.getText())) {
                allValid = false;
            }
        }
        confirmButton.setDisable(!allValid);
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public ArrayList<String> getNames() {
        ArrayList<String> playersNames = new ArrayList<>();
        for (TextField textField : playerNameTextFields) {
            String playerName = textField.getText();
            playersNames.add(playerName);
        }

        return playersNames;
    }

   /* private Button createConfirmButton(NamesInputButtonHandler inputButtonHandler) {
        Button confirmButton = new Button("Confirmar");
        confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #0b4163; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnMouseExited(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnAction(e -> {
            //handleConfirmButton();
            inputButtonHandler.handle(e);
        });

        VBox.setMargin(confirmButton, new Insets(20, 0, 0, 0));

        return confirmButton;
    }

    public ArrayList<String> handleConfirmButton() {
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
            //names.add(playerName);
        }

        if (!allNamesValid) {
            String invalidPlayers = String.join(",", invalidPlayersNames);
            showErrorDialog("Por favor jugador/es número " + invalidPlayers + " ingrese/n un nombre de jugador válido.");
        }

        if (allNamesValid) {
            //playersNamesConsumer.accept(playersNames);
            this.game.registerUsers(playersNames);
        }
        else{
            String invalidPlayers = String.join(",", invalidPlayersNames);
            showErrorDialog("Por favor jugador/es número " + invalidPlayers + " ingrese/n un nombre de jugador válido.");
        }
        return playersNames;
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
     }

    */
}

