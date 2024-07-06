package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.types.GroupChoice;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupChoiceTest {

    private Player player;
    private GroupChoice groupChoice;

    public void initializeGroupChoiceTest() {
        player = new Player("Player", new Score(0));

        ArrayList<GroupChoice.Group> groups = new ArrayList<>();
        groups.add(GroupChoice.createGroup("", new int[] {1, 2, 5}));
        groups.add(GroupChoice.createGroup("", new int[] {3, 4, 6}));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("", "correcta", 1));
        choices.add(new Choice("", "correcta", 2));
        choices.add(new Choice("", "correcta", 3));
        choices.add(new Choice("", "correcta", 4));
        choices.add(new Choice("", "correcta", 5));
        choices.add(new Choice("", "correcta", 6));

        Content content = new Content("", "", "");
        groupChoice = new GroupChoice(1, content, new ClassicMode(), choices);
        groupChoice.addGroups(groups);
    }

    @Test
    // El jugador coloca bien las opciones en ambos grupos y no se le asigna puntaje
    public void test01PlayerWhoCorrectlyPlacesChoicesInGroupsScores() {
        // Arrange
        initializeGroupChoiceTest();

        // Act
        ArrayList<Choice> chosenAnswersGroupA = player.setAnswers(groupChoice, "1,2,5");
        ArrayList<Choice> chosenAnswersGroupB = player.setAnswers(groupChoice, "3,4,6");

        ArrayList<ArrayList<Choice>> groupsChosenAnswers = new ArrayList<>();
        groupsChosenAnswers.add(chosenAnswersGroupA);
        groupsChosenAnswers.add(chosenAnswersGroupB);

        int score = groupChoice.calculateTotalScore(groupsChosenAnswers);
        player.addToScore(score);

        // Assert
        assertEquals(1, player.getScore());
    }

    @Test
    // El jugador se equivoca al colocar las opciones en los grupos
    public void test02PlayerIncorrectlyPlacesChoicesInGroups() {
        // Arrange
        initializeGroupChoiceTest();

        // Act
        ArrayList<Choice> chosenAnswersGroupA = player.setAnswers(groupChoice, "1,3,6");
        ArrayList<Choice> chosenAnswersGroupB = player.setAnswers(groupChoice, "2,4,5");

        ArrayList<ArrayList<Choice>> groupsChosenAnswers = new ArrayList<>();
        groupsChosenAnswers.add(chosenAnswersGroupA);
        groupsChosenAnswers.add(chosenAnswersGroupB);

        int score = groupChoice.calculateTotalScore(groupsChosenAnswers);
        player.addToScore(score);

        // Assert
        assertEquals(0, player.getScore());
    }
}