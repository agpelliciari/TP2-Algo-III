package tp2.clases.controllers.handlers;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ScrollBarHandler extends ScrollPane {

    private VBox content;

    public ScrollBarHandler() {
        content = new VBox();
        setContent(content);
        setFitToWidth(true);
        setHbarPolicy(ScrollBarPolicy.NEVER);
    }
}