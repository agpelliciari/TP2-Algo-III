package tp2.clases.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class QuestionScreenBuilder {

    public static Node createLabel(String text, int fontSize, boolean bold) {
        return createLabel(text, fontSize, bold, null);
    }

    public static Node createLabel(String text, int fontSize, boolean bold, String textColor) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: " + fontSize + "px;" + (bold ? " -fx-font-weight: bold;" : "") + (textColor != null ? " -fx-text-fill: " + textColor + ";" : ""));
        label.setWrapText(true);
        return label;
    }

    public static Node createMultiplierContainer(CheckBox multiplierCheckBox) {
        HBox multiplierContainer = new HBox(10);
        multiplierContainer.setAlignment(Pos.CENTER);
        multiplierContainer.getChildren().addAll(multiplierCheckBox);
        return multiplierContainer;
    }

    public static TextFlow createText(String content, int fontSize) {
        Text text = new Text(content);
        text.setStyle("-fx-font-size: " + fontSize + "px;");
        text.setWrappingWidth(780);

        TextFlow textFlow = new TextFlow(text);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(new Insets(10));
        return textFlow;
    }


}