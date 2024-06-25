package tp2.clases;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonParser {

    public static class QuestionString {
        @SerializedName("ID")
        private String id;
        @SerializedName("Tema")
        private String theme;
        @SerializedName("Tipo")
        private String type;
        @SerializedName("Pregunta")
        private String question;
        @SerializedName("Respuesta")
        private String Choice;
        @SerializedName("Opcion 1")
        private String choice1;
        @SerializedName("Opcion 2")
        private String choice2;
        @SerializedName("Opcion 3")
        private String choice3;
        @SerializedName("Opcion 4")
        private String choice4;
        @SerializedName("Opcion 5")
        private String choice5;
        @SerializedName("Opcion 6")
        private String choice6;
        @SerializedName("Grupo A")
        private String groupA;
        @SerializedName("Grupo B")
        private String groupB;
        @SerializedName("Texto respuesta")
        private String answerText;

        public QuestionString() {}

        public String getId() {
            return id;
        }

        public String getTheme() {
            return theme;
        }

        public String getType() {
            return type;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return Choice;
        }

        public String getChoice1() {
            return choice1;
        }

        public String getChoice2() {
            return choice2;
        }

        public String getChoice3() {
            return choice3;
        }

        public String getChoice4() {
            return choice4;
        }

        public String getChoice5() {
            return choice5;
        }

        public String getChoice6() {
            return choice6;
        }

        public String getGroupAText() {
            return groupA;
        }

        public String getGroupBText() {
            return groupB;
        }

        public String getAnswerText() {
            return answerText;
        }
    }

    public JsonParser() {}

    public ArrayList<QuestionString> questionsStringParser(String fileName) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(fileName)) {
            TypeToken<ArrayList<QuestionString>> questionListType = new TypeToken<ArrayList<QuestionString>>() {
            };
            return gson.fromJson(reader, questionListType.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
    public ArrayList<Question> questionsParser(String fileName) {
        ArrayList<Question> questions = new ArrayList<>();

        for (QuestionString questionString : questionsStringParser(fileName)) {
            String[] choicesString = {
                    questionString.getChoice1(),
                    questionString.getChoice2(),
                    questionString.getChoice3(),
                    questionString.getChoice4(),
                    questionString.getChoice5(),
                    questionString.getChoice6()
            };

            String[] validChoicesString = Arrays.stream(choicesString).filter(choice -> choice != null && !choice.isEmpty()).toArray(String[]::new);

            ArrayList<Choice> choices = ChoicesFactory.createChoices(questionString, validChoicesString);

            QuestionFactory factory = QuestionFactoryProvider.getFactory(questionString.getType());

            questions.add(factory.createQuestion(questionString.getId(), questionString, choices));
        }

        return questions;
    }
}