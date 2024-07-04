package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.types.MultipleChoice;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceTest {

    Content content = new Content("", "", "");

    @Test
    public void test01PlayerChoosesOneIncorrectChoiceAndDoesntReceivePoints() {
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "incorrecta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2,3");
        question.assignScore(player, chosenAnswers);

        assertEquals(0, player.getScore());
    }

    @Test
    public void test02PlayerChoosesAllChoicesCorrect() {
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "incorrecta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2");
        question.assignScore(player, chosenAnswers);

        assertEquals(1, player.getScore());
    }

    @Test
    public void test03PlayerChoosesAllChoicesIncorrect() {
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        choices.add(new Choice("Answer3", "incorrecta", 3));
        choices.add(new Choice("Answer4", "incorrecta", 4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "3,4");
        question.assignScore(player, chosenAnswers);

        assertEquals(0, player.getScore());
    }
}