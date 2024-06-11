package tp2.clases;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("src/main/resources/preguntas.json")) {
            TypeToken<ArrayList<Question_>> questionListType = new TypeToken<ArrayList<Question_>>() {};
            ArrayList<Question_> questions = gson.fromJson(reader, questionListType.getType());

            for (Question_ question : questions) {
                String[] options = {
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4(),
                        question.getOption5(),
                        question.getOption6()
                };
                options = Arrays.stream(options).filter(option -> option != null && !option.isEmpty()).toArray(String[]::new);
                question.setOptions(options);

                if (question.getGroupAText() != null && question.getGroupBText() != null) {
                    String[] groups = {
                            question.getGroupAText(),
                            question.getGroupBText()
                    };
                    question.setGroups(groups);
                }
            }

            for (Question_ question : questions) {
                System.out.println("ID: " + question.getID());
                System.out.println("Tema: " + question.getTheme());
                System.out.println("Tipo: " + question.getType());
                System.out.println("Question_: " + question.getQuestion());
                System.out.println("Respuesta: " + question.getAnswer());
                for (Option option : question.getOptions())
                    System.out.println("Opcion " + option.getNumber() + ": " + option.getText());
                for (Group group : question.getGroups())
                    System.out.println("Grupo " + group.getLetter() + ": " + group.getText());
                System.out.println("Texto respuesta: " + question.getAnswerText());
                System.out.println("Respuesta del usuario: 1,2");
                System.out.println(question.checkAllAnswersAreCorrect("1,2"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
