package tp2.clases.model.questions.factories;

import tp2.clases.model.JsonParser;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.types.GroupChoice;
import tp2.clases.model.questions.types.Question;

import java.util.ArrayList;

public class GroupChoiceFactory implements QuestionFactory {

    @Override
    public Question createQuestion(JsonParser.QuestionString questionString, ArrayList<Choice> choices) {
        int questionIdToInt = (int) Double.parseDouble(questionString.getId());

        GroupChoice groupChoice = new GroupChoice(questionIdToInt, new Content(questionString.getTheme(), questionString.getQuestion(), questionString.getAnswerText()), new ClassicMode(), choices);

        if (questionString.getGroupAText() != null && questionString.getGroupBText() != null) {
            String[] groupsString = {
                    questionString.getGroupAText(),
                    questionString.getGroupBText()
            };
            ArrayList<GroupChoice.Group> groups = new ArrayList<>();
            String answerString = questionString.getAnswer().replaceAll("\\s", "");
            String[] answerParts = answerString.split(";");
            int i = 0;
            for (String part : answerParts) {
                String[] data = part.split(":");
                String[] numbersString = data[1].split(",");
                int[] numbers = new int[numbersString.length];
                for (int j = 0; j < numbersString.length; j++)
                    numbers[j] = (int) Double.parseDouble(numbersString[j]);
                groups.add(GroupChoice.createGroup(groupsString[i++], numbers));
            }
            groupChoice.addGroups(groups);
        }
        return groupChoice;
    }
}
