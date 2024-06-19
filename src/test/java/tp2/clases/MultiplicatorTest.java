package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicatorTest {

    Content content = new Content("", "", "");

    @Test
    public void test01TrueOrFalseQuestionAssignsScoreCorrectly() {
        // Arrange
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "incorrecta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        // Act
        player.useMultiplicator(2);
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(2, player.getScore());
    }

    @Test
    public void test02TrueOrFalseQuestionAssignsScoreCorrectly() {
        // Arrange
        Player player = new Player("Player1", 4);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "incorrecta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new PenaltyMode(), choices);

        // Act
        player.useMultiplicator(2);
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "2");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(2, player.getScore());
    }

    @Test
    public void test03PlayerChoosesAllOptionsCorrectUsingx3Multiplicator() {
        // Arrange
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "incorrecta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        // Act
        player.useMultiplicator(3);
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(6, player.getScore());
    }

}