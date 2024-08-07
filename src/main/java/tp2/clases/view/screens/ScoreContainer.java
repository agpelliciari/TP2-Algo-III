package tp2.clases.view.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;

public class ScoreContainer extends HBox {

    public ScoreContainer() {
        super(10);
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10));
        //this.setStyle("-fx-border-color: #0d3e5a");
    }

    public void addChild(Node child) {
        this.getChildren().add(child);
    }

    public void cleanContainer() {
        this.getChildren().clear();
    }
}
