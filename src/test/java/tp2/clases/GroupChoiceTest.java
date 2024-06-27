package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.player.Player;
import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.Content;
import tp2.clases.questions.modes.ClassicMode;
import tp2.clases.questions.types.GroupChoice;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class GroupChoiceTest {

    @Test
    public void test01PlayerCorrectlyPlacesChoicesInGroups() {
        Player player = new Player("Player", 0);

        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group('A',"", new int[]{1,2,5}));
        groups.add(new Group('B',"", new int[]{3,4,6}));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("", "correcta", 1));
        choices.add(new Choice("", "correcta", 2));
        choices.add(new Choice("", "correcta", 3));
        choices.add(new Choice("", "correcta", 4));
        choices.add(new Choice("", "correcta", 5));
        choices.add(new Choice("", "correcta", 6));
        Content content = new Content("", "", "");
        GroupChoice groupChoice = new GroupChoice(1, content, new ClassicMode(), choices);
        groupChoice.addGroups(groups);

        ArrayList<Choice> chosenAnswersGroupA = player.setAnswers(groupChoice, "1,2,5");
        ArrayList<Choice> chosenAnswersGroupB = player.setAnswers(groupChoice, "3,4,6");

        groupChoice.getGroups().get(0).assignScore(player, chosenAnswersGroupA);
        groupChoice.getGroups().get(1).assignScore(player, chosenAnswersGroupB);

        assertEquals(2, player.getScore());
    }

    @Test
    public void test02PlayerIncorrectlyPlacesChoicesInGroups() {
        Player player = new Player("Player", 0);

        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group('A',"", new int[]{1,2,5}));
        groups.add(new Group('B',"", new int[]{3,4,6}));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("", "correcta", 1));
        choices.add(new Choice("", "correcta", 2));
        choices.add(new Choice("", "correcta", 3));
        choices.add(new Choice("", "correcta", 4));
        choices.add(new Choice("", "correcta", 5));
        choices.add(new Choice("", "correcta", 6));
        Content content = new Content("", "", "");
        GroupChoice groupChoice = new GroupChoice(1, content, new ClassicMode(), choices);
        groupChoice.addGroups(groups);

        ArrayList<Choice> chosenAnswersGroupA = player.setAnswers(groupChoice, "1,3,6");
        ArrayList<Choice> chosenAnswersGroupB = player.setAnswers(groupChoice, "2,4,5");

        groupChoice.getGroups().get(0).assignScore(player, chosenAnswersGroupA);
        groupChoice.getGroups().get(1).assignScore(player, chosenAnswersGroupB);

        assertEquals(0, player.getScore());
    }
}