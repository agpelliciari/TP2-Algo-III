package tp2.clases.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import tp2.clases.model.Game;
import tp2.clases.controllers.handlers.ConfirmButtonHandler;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class PlayersInputScreen extends VBox {

    ComboBox<String> pointsCombo;
    ComboBox<String> questionsCombo;
    ComboBox<String> playersCombo;
    private TextField numberOfPlayersTextField;
    private TextField numberOfQuestionsTextField;
    private TextField numberOfPointsTextField;
    private List<String> playerQuantityOptions = Arrays.stream("2,3,4,5,6".split(",")).toList();
    private List<String> questionLimitOptions = Arrays.stream("5,10,15,20,25".split(",")).toList();
    private List<String> scoreLimitOptions = Arrays.stream("10,20,30,40,50".split(",")).toList();
    ToggleGroup toggleGroupPlayerQuantity;
    ToggleGroup toggleGroupQuestionLimit;
    ToggleGroup toggleGroupScoreLimit;
    RadioButton selectedTogglePlayerQuantity;
    RadioButton selectedToggleQuestionLimit;
    RadioButton selectedToggleScoreLimit;

    private Game game;
    private StackPane stackPane;
    Button confirmButton;
    private MediaPlayer mediaPlayer;

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

    public PlayersInputScreen(StackPane stackPane, Game game, MediaPlayer mediaPlayer) {
        super(30);
        this.stackPane = stackPane;
        this.mediaPlayer = mediaPlayer;
        this.game = game;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        Image image = new Image("file:src/main/resources/images/white-background.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        confirmButton = new Button();
        confirmButton.setText("Confirmar");
        confirmButton.setStyle("-fx-font-size: 20px; -fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");
        ConfirmButtonHandler confirmButtonHandler = new ConfirmButtonHandler(game, this, stackPane);
        confirmButton.setOnAction(confirmButtonHandler);
        confirmButton.setDisable(true);

        displayPlayerOptionsToggleGroup();
/*

        Label label = labelCreator("Ingrese la cantidad de jugadores:");
        playersCombo = new ComboBox<>(FXCollections.observableArrayList("2", "3", "4", "5", "6"));
        addValidationListener(playersCombo);

        Label questionsLabel = labelCreator("Ingrese el limite de preguntas:");
        questionsCombo = new ComboBox<>(FXCollections.observableArrayList("5", "10", "15", "20", "25"));
        addValidationListener(questionsCombo);

        Label pointsLabel = labelCreator("Ingrese el limite de puntos:");
        pointsCombo = new ComboBox<>(FXCollections.observableArrayList("10", "20", "30", "40", "50"));
        addValidationListener(pointsCombo);
*/

        //getChildren().addAll(label, playersCombo, questionsLabel, questionsCombo, pointsLabel, pointsCombo, confirmButton);
    }

    private void displayPlayerOptionsToggleGroup() {
        Label label = labelCreator("Seleccione la cantidad de jugadores:");
        getChildren().add(label);
        toggleGroupPlayerQuantity = new ToggleGroup();

        for (String option: playerQuantityOptions) {
            RadioButton radioButton = radioButtonCreator(option, toggleGroupPlayerQuantity);
            radioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("")) {
                    selectedTogglePlayerQuantity = (RadioButton) toggleGroupPlayerQuantity.getSelectedToggle();
                    getChildren().clear();
                    displayQuestionLimitOptionsToggleGroup();
                }
            });
            getChildren().add(radioButton);
        }
        getChildren().add(confirmButton);
    }

    private void displayQuestionLimitOptionsToggleGroup() {
        Label label = labelCreator("Seleccione el limite de preguntas:");
        getChildren().add(label);
        toggleGroupQuestionLimit = new ToggleGroup();

        for (String option : questionLimitOptions) {
            RadioButton radioButton = radioButtonCreator(option, toggleGroupQuestionLimit);
            radioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("")) {
                    selectedToggleQuestionLimit = (RadioButton) toggleGroupQuestionLimit.getSelectedToggle();
                    getChildren().clear();
                    displayScoreLimitOptionsToggleGroup();
                }
            });
            getChildren().add(radioButton);
        }
        getChildren().add(confirmButton);
    }

    private void displayScoreLimitOptionsToggleGroup() {
        Label label = labelCreator("Seleccione el limite de puntos:");
        getChildren().add(label);
        toggleGroupScoreLimit = new ToggleGroup();

        for (String option: scoreLimitOptions) {
            RadioButton radioButton = radioButtonCreator(option, toggleGroupScoreLimit);
            radioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("")) {
                    selectedToggleScoreLimit = (RadioButton) toggleGroupScoreLimit.getSelectedToggle();
                    confirmButton.setDisable(false);
                }
            });
            getChildren().add(radioButton);
        }

        getChildren().add(confirmButton);
    }

    private void addValidationListener(ComboBox<String> comboBox) {
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> validateInputs());
    }

    private void validateInputs() {
        boolean allValid = playersCombo.getValue() != null &&
                questionsCombo.getValue() != null &&
                pointsCombo.getValue() != null;
        confirmButton.setDisable(!allValid);
    }

    public int getNumberOfPlayersInput() {
        return Integer.parseInt(selectedTogglePlayerQuantity.getText());
        //return Integer.parseInt(playersCombo.getValue());
    }

    public int getQuestionLimitInput() {
        return Integer.parseInt(selectedToggleQuestionLimit.getText());
        //return Integer.parseInt(questionsCombo.getValue());
    }

    public int getPointLimitInput() {
        return Integer.parseInt(selectedToggleScoreLimit.getText());
        //return Integer.parseInt(pointsCombo.getValue());
    }

    private Label labelCreator(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333;");
        return label;
    }

    private RadioButton radioButtonCreator(String text, ToggleGroup toggleGroup) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setSelected(false);
        radioButton.setStyle("-fx-background-radius: 15px; -fx-padding: 8px; -fx-border-width: 1px; -fx-font-size: 15px; -fx-stroke-width: 2px;");

        return radioButton;
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
