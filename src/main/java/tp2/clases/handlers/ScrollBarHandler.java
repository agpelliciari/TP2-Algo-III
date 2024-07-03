package tp2.clases.handlers;

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