package tp2.clases;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonParser {
    public class QuestionString {
        @SerializedName("ID")
        private String id;
        @SerializedName("Tema")
        private String theme;
        @SerializedName("Tipo")
        private String type;
        @SerializedName("Pregunta")
        private String question;
        @SerializedName("Respuesta")
        private String answer;
        @SerializedName("Opcion 1")
        private String option1;
        @SerializedName("Opcion 2")
        private String option2;
        @SerializedName("Opcion 3")
        private String option3;
        @SerializedName("Opcion 4")
        private String option4;
        @SerializedName("Opcion 5")
        private String option5;
        @SerializedName("Opcion 6")
        private String option6;
        @SerializedName("Grupo A")
        private String groupA;
        @SerializedName("Grupo B")
        private String groupB;
        @SerializedName("Texto respuesta")
        private String answerText;

        public QuestionString() {
        }

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
            return answer;
        }

        public String getOption1() {
            return option1;
        }

        public String getOption2() {
            return option2;
        }

        public String getOption3() {
            return option3;
        }

        public String getOption4() {
            return option4;
        }

        public String getOption5() {
            return option5;
        }

        public String getOption6() {
            return option6;
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

    public JsonParser() {
    }

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
                    questionString.getOption1(),
                    questionString.getOption2(),
                    questionString.getOption3(),
                    questionString.getOption4(),
                    questionString.getOption5(),
                    questionString.getOption6()
            };
            String[] validChoicesString = Arrays.stream(choicesString).filter(option -> option != null && !option.isEmpty()).toArray(String[]::new);
            ArrayList<Choice> choices = new ArrayList<>();

            int i = 1;
            if (!questionString.getType().equals("Ordered choice") && !questionString.getType().equals("Ordered Choice") && !questionString.type.equals("Group Choice")) {
                for (String validChoiceString : validChoicesString) {
                    if (contains(questionString.getAnswer(), i)) {
                        choices.add(new Choice(validChoiceString, "Correcta", i++));
                    } else {
                        choices.add(new Choice(validChoiceString, "Incorrecta", i++));
                    }
                }
            } else {
                for (String validChoiceString : validChoicesString) {
                    choices.add(new Choice(validChoiceString, "Correcta", i++));
                }
            }

            int questionId = (int) Double.parseDouble(questionString.getId());

            switch (questionString.getType()) {
                case "Verdadero Falso Simple":
                case "Verdadero Falso":
                    questions.add(new TrueOrFalse(questionId, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices));
                    break;

                case "Verdadero Falso Penalidad":
                    questions.add(new TrueOrFalse(questionId, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new PenaltyMode(), choices));
                    break;
                case "Multiple Choice Simple":
                    questions.add(new MultipleChoice(questionId, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices));
                    break;
                case "Multiple Choice Puntaje Parcial":
                    questions.add(new MultipleChoice(questionId, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new PartialMode(), choices));
                    break;
                case "Multiple Choice Penalidad":
                    questions.add(new MultipleChoice(questionId, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new PenaltyMode(), choices));
                    break;
                case "Ordered choice":
                case "Ordered Choice":
                    int[] correctOrder = getIntArrayFromString(questionString.getAnswer());
                    questions.add(new OrderedChoice(questionId, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices, correctOrder));
                    break;
                case "Group Choice":
                    questions.add(new GroupChoice(questionId, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices));
                    if (questionString.getGroupAText() != null && questionString.getGroupBText() != null) {
                        String[] groupsString = {
                                questionString.getGroupAText(),
                                questionString.getGroupBText()
                        };
                        ArrayList<Group> groups = new ArrayList<>();
                        String answerString = questionString.getAnswer().replaceAll("\\s", "");
                        String[] answerParts = answerString.split(";");
                        i = 0;
                        for (String part : answerParts) {
                            String[] data = part.split(":");
                            String[] numbersString = data[1].split(",");
                            int[] numbers = new int[numbersString.length];
                            for (int j = 0; j < numbersString.length; j++)
                                numbers[j] = (int) Double.parseDouble(numbersString[j]);
                            groups.add(new Group(data[0].charAt(0), groupsString[i++], numbers));
                        }
                        GroupChoice groupChoice = new GroupChoice(questionId, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices);
                        groupChoice.addGroups(groups);
                        questions.add(groupChoice);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown question type: " + questionString.getType());
            }
        }
        return questions;
    }

    private int[] getIntArrayFromString(String answer) {
        String[] answerParts = answer.split(",");
        int[] answerInt = new int[answerParts.length];
        for (int i = 0; i < answerParts.length; i++) {
            answerInt[i] = Integer.parseInt(answerParts[i]);
        }
        return answerInt;
    }

    private static boolean contains(String correctChoicesString, int choice) {
        String[] answers = correctChoicesString.split(",");
        for (String answer : answers)
            if (Integer.parseInt(answer.trim()) == choice)
               return true;
        return false;
    }
}