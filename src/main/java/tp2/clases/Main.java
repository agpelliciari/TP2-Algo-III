package tp2.clases;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.*;

import javafx.application.Application;

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

        //to run javaFx basic example
        ///
        launch(args);
        ////
      
        JsonParser jsonParser = new JsonParser();
        ArrayList<Question> questions = jsonParser.questionsParser("src/main/resources/preguntas.json");
        for (Question question : questions) {
            System.out.println("ID: " + question.getId());
            System.out.println("Tema: " + question.getContent().getTheme());
            System.out.println("Pregunta: " + question.getContent().getPrompt());
            System.out.print("Respuesta: ");
            if (question instanceof OrderedChoice) {
                String orderedResponse = Arrays.stream(((OrderedChoice) question).getCorrectOrder())
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(","));
                System.out.print(orderedResponse);
            } else if (question instanceof GroupChoice) {
                StringBuilder answer = new StringBuilder();
                boolean firstGroup = true;
                for (Group group : ((GroupChoice) question).getGroups()) {
                    if (!firstGroup) {
                        answer.append("; ");
                    }
                    answer.append(group.getLetter()).append(": ");
                    boolean firstChoice = true;
                    for (Choice choice : group.getChoices()) {
                        if (!firstChoice) {
                            answer.append(",");
                        }
                        answer.append(choice.getId());
                        firstChoice = false;
                    }
                    firstGroup = false;
                }
                System.out.print(answer.toString());
            } else {
                boolean first = true;
                for (Choice choice : question.getChoices()) {
                    if (choice.getCorrection().isCorrect()) {
                        if (!first) {
                            System.out.print(",");
                        }
                        System.out.print(choice.getId());
                        first = false;
                    }
                }
            }
            System.out.println();
            for (Choice choice : question.getChoices()) {
                System.out.println("Opcion " + choice.getId() + ": " + choice.getContent());
            }
            if (question instanceof GroupChoice) {
                for (Group group : ((GroupChoice) question).getGroups()) {
                    System.out.println("Grupo " + group.getLetter() + ": " + group.getText());
                }
            }
            System.out.println("Texto Respuesta: " + question.getContent().getAnswerText());
            System.out.println();
        }
    }
}