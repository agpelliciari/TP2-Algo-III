package tp2.clases;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("My first window");

        StackPane layout = new StackPane();
        Button button = new Button();
        button.setText("button text");
        layout.getChildren().add(button);
        Scene scene = new Scene(new StackPane(layout), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}