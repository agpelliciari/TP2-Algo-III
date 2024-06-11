package tp2.clases;

import com.google.gson.annotations.SerializedName;
import java.util.*;

class Question_ {
    @SerializedName("ID")
    private int id;
    @SerializedName("Tema")
    private String theme;
    @SerializedName("Tipo")
    private String type;
    @SerializedName("Question_")
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
    private final ArrayList<Option> options = new ArrayList<>();
    private final ArrayList<Group> groups = new ArrayList<>();

    public Question_() {}

    public int getID() {
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

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            this.options.add(new Option(i + 1, options[i], new Correct()));
        }
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(String[] text) {
        answer = answer.replaceAll("\\s", "");
        String[] answerParts = answer.split(";");
        int i = 0;
        for (String part : answerParts) {
            String[] data = part.split(":");
            String[] numbersString = data[1].split(",");
            int[] numbers = new int[numbersString.length];
            for (int j = 0; j < numbersString.length; j++)
                numbers[j] = Integer.parseInt(numbersString[j]);
            this.addGroup(new Group(data[0].charAt(0), text[i], numbers));
            i++;
        }
    }

    public boolean checkAllAnswersAreCorrect(String userAnswer) {
        List<String> correctAnswerList = Arrays.asList(answer.split(","));
        List<String> userAnswerList = Arrays.asList(userAnswer.split(","));

        Collections.sort(correctAnswerList);
        Collections.sort(userAnswerList);

        return correctAnswerList.equals(userAnswerList);
    }
}