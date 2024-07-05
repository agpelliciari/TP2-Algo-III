package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.PartialMode;
import tp2.clases.model.questions.types.MultipleChoice;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartialModeTest {

    Content content = new Content("", "", "");

    @Test
    public void test01PlayerChoosesAllPossibleCorrectChoicesAndNoneIncorrect() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "correcta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2,3");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(3, player.getScore());
    }

    @Test
    public void test02PlayerChoosesAllPossibleCorrectChoicesAndOneIncorrectObtainsZeroPoints() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "correcta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,3,4");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(0, player.getScore());
    }

    @Test
    public void test03PlayerChooses2OutOf3CorrectChoicesAndNoneIncorrectObtains2Points() {
        // Arrange
        Player player = new Player("Player1", new Score(0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "correcta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2");

        question.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(2, player.getScore());
    }
}