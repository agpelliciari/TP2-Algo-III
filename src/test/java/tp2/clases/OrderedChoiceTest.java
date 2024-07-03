package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.player.Player;
import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.Content;
import tp2.clases.questions.modes.ClassicMode;
import tp2.clases.questions.types.OrderedChoice;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderedChoiceTest {

    Content content = new Content("", "", "");

    @Test
    public void test01PlayerGetsOrderCorrectReceivesOnePoint() {
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "correcta", 3));
        choices.add(new Choice("Answer4", "correcta", 4));
        OrderedChoice question = new OrderedChoice(1, content, new ClassicMode(), choices, new int[] {1, 3, 2, 4});

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,3,2,4");
        question.assignScore(player, chosenAnswers);

        assertEquals(1, player.getScore());
    }

    @Test
    public void test02PlayerGetsOrderIncorrectReceivesZeroPoints() {
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "correcta", 3));
        choices.add(new Choice("Answer4", "correcta", 4));
        OrderedChoice question = new OrderedChoice(1, content, new ClassicMode(), choices, new int[] {1, 3, 2, 4});

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2,3,4");
        question.assignScore(player, chosenAnswers);

        assertEquals(0, player.getScore());
    }
}
