package tp2.clases.screens;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tp2.clases.Game;
import tp2.clases.handlers.ConfirmButtonHandler;

import java.util.function.Consumer;

public class PlayersInputScreen extends VBox {
    ComboBox<String> pointsCombo;
    ComboBox<String> questionsCombo;
    ComboBox<String> playersCombo;
    private TextField numberOfPlayersTextField;
    private TextField numberOfQuestionsTextField;
    private TextField numberOfPointsTextField;

    Stage stage;

    public PlayersInputScreen(Consumer<Integer> numberOfPlayersConsumer, Consumer<Integer> numberOfQuestionsConsumer, Consumer<Integer> numberOfPointsConsumer) {
        super(20);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        setStyle("-fx-background-color: #f0f0f0;");

        Label label = labelCreator("Ingrese la cantidad de jugadores:");
        numberOfPlayersTextField = textFieldCreator("Cantidad de jugadores");

        Label questionsLabel = labelCreator("Ingrese el limite de preguntas:");
        numberOfQuestionsTextField = textFieldCreator("Limite de preguntas");

        Label pointsLabel = labelCreator("Ingrese el limite de puntos:");
        numberOfPointsTextField = textFieldCreator("Limite de puntos");

        Button confirmButton = getButton(numberOfPlayersConsumer, numberOfQuestionsConsumer, numberOfPointsConsumer);

        numberOfPlayersTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmNumber(numberOfPlayersTextField, numberOfPlayersConsumer, 1);
            }
        });

        numberOfQuestionsTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmNumber(numberOfQuestionsTextField, numberOfQuestionsConsumer, 1);
            }
        });

        numberOfPointsTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmNumber(numberOfPointsTextField, numberOfPointsConsumer, 1);
            }
        });

        getChildren().addAll(label, numberOfPlayersTextField, questionsLabel, numberOfQuestionsTextField, pointsLabel, numberOfPointsTextField, confirmButton);
    }

    public PlayersInputScreen(Stage primaryStage, Scene namesInputScene, Game game) {
        super(30);

        this.stage = primaryStage;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        Image image = new Image("file:textura.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        Label label = labelCreator("Ingrese la cantidad de jugadores:");
        playersCombo = new ComboBox<>(FXCollections.observableArrayList("2", "3", "4", "5", "6"));

        Label questionsLabel = labelCreator("Ingrese el limite de preguntas:");
        questionsCombo = new ComboBox<>(FXCollections.observableArrayList("5", "10", "15", "20", "25"));

        Label pointsLabel = labelCreator("Ingrese el limite de puntos:");
        pointsCombo = new ComboBox<>(FXCollections.observableArrayList("10", "20", "30", "40", "50"));

        Button confirmButton = new Button();
        confirmButton.setText("Comenzar");
        ConfirmButtonHandler confirmButtonHandler = new ConfirmButtonHandler(game, this, namesInputScene, primaryStage);
        confirmButton.setOnAction(confirmButtonHandler);

        getChildren().addAll(label, playersCombo, questionsLabel, questionsCombo, pointsLabel, pointsCombo, confirmButton);

    }

    public int getNumberOfPlayersInput() {
        return Integer.parseInt(playersCombo.getValue());
    }

    public int getQuestionLimitInput() {
        return Integer.parseInt(questionsCombo.getValue());
    }

    public int getPointLimitInput() {
        return Integer.parseInt(pointsCombo.getValue());
    }

    private Label labelCreator(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        return label;
    }

    private TextField textFieldCreator(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        return textField;
    }

    private Button getButton(Consumer<Integer> numberOfPlayersConsumer, Consumer<Integer> numberOfQuestionsConsumer, Consumer<Integer> numberOfPointsConsumer) {
        Button confirmButton = new Button("Confirmar");
        ConfirmButtonHandler confirmButtonHandler = new ConfirmButtonHandler(this, numberOfPlayersConsumer, numberOfQuestionsConsumer, numberOfPointsConsumer);
        confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #0b4163; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnMouseExited(e -> confirmButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #266d99; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        confirmButton.setOnAction(confirmButtonHandler);
        return confirmButton;
    }

    public TextField getNumberOfPlayersTextField() {
        return numberOfPlayersTextField;
    }

    public TextField getNumberOfQuestionsTextField() {
        return numberOfQuestionsTextField;
    }

    public TextField getNumberOfPointsTextField() {
        return numberOfPointsTextField;
    }

    public void confirmNumber(TextField textField, Consumer<Integer> consumer, int minValue) {
        try {
            int value = Integer.parseInt(textField.getText());
            if (value > minValue) {
                consumer.accept(value);
            } else {
                showErrorDialog("Ingrese un número válido (> 1).");
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Ingrese un número válido.");
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
