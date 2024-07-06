package tp2.clases.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import tp2.clases.model.Game;
import tp2.clases.controllers.handlers.ConfirmButtonHandler;

import java.util.Arrays;
import java.util.List;

import static tp2.clases.ConstantsPaths.BACKGROUND_IMAGE_PATH;


public class PlayersInputScreen extends VBox {

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

    public PlayersInputScreen(StackPane stackPane, Game game, MediaPlayer mediaPlayer) {
        super(30);
        this.stackPane = stackPane;
        this.mediaPlayer = mediaPlayer;
        this.game = game;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        Image image = new Image(BACKGROUND_IMAGE_PATH);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        confirmButton = new Button();
        confirmButton.setText("Confirmar");
        confirmButton.setStyle("-fx-font-size: 20px; -fx-background-color: #007bff; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 2, 2);");
        ConfirmButtonHandler confirmButtonHandler = new ConfirmButtonHandler(game, this, stackPane);
        confirmButton.setOnAction(confirmButtonHandler);
        confirmButton.setDisable(true);

        displayPlayerOptionsToggleGroup();
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

    public int getNumberOfPlayersInput() {
        return Integer.parseInt(selectedTogglePlayerQuantity.getText());
    }

    public int getQuestionLimitInput() {
        return Integer.parseInt(selectedToggleQuestionLimit.getText());
    }

    public int getPointLimitInput() {
        return Integer.parseInt(selectedToggleScoreLimit.getText());
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
}
