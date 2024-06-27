package tp2.clases.questions.factories;

import tp2.clases.*;
import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.Content;
import tp2.clases.questions.modes.ClassicMode;
import tp2.clases.questions.types.GroupChoice;
import tp2.clases.questions.types.Question;

import java.util.ArrayList;

public class GroupChoiceFactory implements QuestionFactory {

    @Override
    public Question createQuestion(String questionId, JsonParser.QuestionString questionString, ArrayList<Choice> choices) {
        int questionIdToInt = (int) Double.parseDouble(questionString.getId());

        GroupChoice groupChoice = new GroupChoice(questionIdToInt, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices);

        if (questionString.getGroupAText() != null && questionString.getGroupBText() != null) {
            String[] groupsString = {
                    questionString.getGroupAText(),
                    questionString.getGroupBText()
            };
            ArrayList<Group> groups = new ArrayList<>();
            String answerString = questionString.getAnswer().replaceAll("\\s", "");
            String[] answerParts = answerString.split(";");
            int i = 0;
            for (String part : answerParts) {
                String[] data = part.split(":");
                String[] numbersString = data[1].split(",");
                int[] numbers = new int[numbersString.length];
                for (int j = 0; j < numbersString.length; j++)
                    numbers[j] = (int) Double.parseDouble(numbersString[j]);
                groups.add(new Group(data[0].charAt(0), groupsString[i++], numbers));
            }
            groupChoice.addGroups(groups);
        }
        return groupChoice;
    }
}
