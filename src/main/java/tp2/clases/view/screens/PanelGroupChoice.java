package tp2.clases.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tp2.clases.model.Game;

public class PanelGroupChoice {

    Stage stage;

    public PanelGroupChoice(Stage primaryStage, Game game, int playerIndex, int questionIndex) {
        stage = primaryStage;

        VBox groupA = new VBox();
        groupA.setSpacing(10);
        groupA.setStyle("-fx-border-color: black; -fx-padding: 10;");
        Label groupALabel = new Label("Grupo A");
        groupA.getChildren().add(groupALabel);

        VBox groupB = new VBox();
        groupB.setSpacing(10);
        groupB.setStyle("-fx-border-color: black; -fx-padding: 10;");
        Label groupBLabel = new Label("Grupo B");
        groupB.getChildren().add(groupBLabel);

        VBox buttonsBox = new VBox();
        buttonsBox.setSpacing(10);
        buttonsBox.setStyle("-fx-border-color: black; -fx-padding: 10;");
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        buttonsBox.getChildren().addAll(button1, button2);

        // Configurar los orígenes del arrastre
        button1.setOnDragDetected(event -> {
            Dragboard db = button1.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(button1.getText());
            db.setContent(content);
            event.consume();
        });

        button2.setOnDragDetected(event -> {
            Dragboard db = button2.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(button2.getText());
            db.setContent(content);
            event.consume();
        });

        // Configurar el destino del arrastre para Grupo A
        groupA.setOnDragOver(event -> {
            if (event.getGestureSource() != groupA && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        groupA.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Button draggedButton = new Button(db.getString());
                draggedButton.setOnDragDetected(dragEvent -> {
                    Dragboard db2 = draggedButton.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(draggedButton.getText());
                    db2.setContent(content);
                    dragEvent.consume();
                });
                groupA.getChildren().add(draggedButton);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        // Configurar el destino del arrastre para Grupo B
        groupB.setOnDragOver(event -> {
            if (event.getGestureSource() != groupB && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        groupB.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Button draggedButton = new Button(db.getString());
                draggedButton.setOnDragDetected(dragEvent -> {
                    Dragboard db2 = draggedButton.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(draggedButton.getText());
                    db2.setContent(content);
                    dragEvent.consume();
                });
                groupB.getChildren().add(draggedButton);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        VBox root = new VBox(buttonsBox, groupA, groupB);
        root.setSpacing(20);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Drag and Drop Example with Buttons");
        primaryStage.show();
    }
}
