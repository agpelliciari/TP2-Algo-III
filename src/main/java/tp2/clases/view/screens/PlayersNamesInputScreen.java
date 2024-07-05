package tp2.clases.view.screens;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.function.Consumer;

import tp2.clases.model.Game;
import tp2.clases.controllers.handlers.NamesInputButtonHandler;

import static tp2.clases.ConstantsPaths.BACKGROUND_IMAGE_PATH;


public class PlayersNamesInputScreen extends VBox {

    Game game;
    StackPane stackPane;

    private ArrayList<TextField> playerNameTextFields;
    private Consumer<ArrayList<String>> playersNamesConsumer;
    private Button confirmButton;

    public PlayersNamesInputScreen(StackPane stackPane, Game game) {
        super();

        this.stackPane = stackPane;
        this.game = game;

        Image image = new Image(BACKGROUND_IMAGE_PATH);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        this.playerNameTextFields = new ArrayList<>();

        this.confirmButton = new Button("Comenzar Partida");
        confirmButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");
        confirmButton.setDisable(true);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);

        createPlayerNameInputFields();

        NamesInputButtonHandler inputButtonHandler = new NamesInputButtonHandler(game, stackPane, this);
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

        VBox.setMargin(label, new Insets(0, 0, 5, 0));

        return label;
    }

    private TextField createPlayerNameTextField() {
        TextField playerNameTextField = new TextField();
        playerNameTextField.setPromptText("Nombre del jugador");
        playerNameTextField.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        playerNameTextField.setMaxWidth(300);
        addValidationListener(playerNameTextField);

        VBox.setMargin(playerNameTextField, new Insets(0, 0, 10, 0));

        return playerNameTextField;
    }

    private void addValidationListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidName(newValue) && !isRepeatedName(getNames(), newValue)) {
                textField.setStyle("-fx-border-color: green; -fx-border-width: 1px; -fx-background-color: #C6F7D0;");
                textField.setTooltip(null);
            } else {
                textField.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-background-color: #FFC5C5;");

                if (!isValidName(newValue)) {
                    textField.setTooltip(new Tooltip("Nombre invalido. Porfavor ingrese un nombre valido."));
                } else if (isRepeatedName(getNames(), newValue)) {
                    textField.setTooltip(new Tooltip("El nombre ya esta en uso. Porfavor ingrese otro nombre."));
                }
            }
            validateInputs();
        });
    }

    private void validateInputs() {
        boolean allValid = true;
        ArrayList<String> playersNames = getNames();
        for (TextField textField : playerNameTextFields) {
            String playerName = textField.getText();
            if (!isValidName(playerName) || isRepeatedName(playersNames, playerName)) {
                allValid = false;
            }
        }
        confirmButton.setDisable(!allValid);
    }

    private boolean isRepeatedName(ArrayList<String> playersNames, String name) {
        return playersNames.indexOf(name) != playersNames.lastIndexOf(name);
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
}

