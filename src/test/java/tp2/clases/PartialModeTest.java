package tp2.clases;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PartialModeTest {

    Content content = new Content("", "","");

    @Test
    public void test01PlayerChoosesAllPossibleCorrectChoicesAndNoneIncorrect() {

        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",1));
        choices.add(new Choice("Answer2", "correcta",2));
        choices.add(new Choice("Answer3", "correcta",3));
        choices.add(new Choice("Answer4", "incorrecta",4));
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2,3");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(3, player.getScore());
    }

    @Test
    public void test02PlayerChoosesAllPossibleCorrectChoicesAndOneIncorrectObtainsZeroPoints() {

        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",1));
        choices.add(new Choice("Answer2", "correcta",2));
        choices.add(new Choice("Answer3", "correcta",3));
        choices.add(new Choice("Answer4", "incorrecta",4));
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,3,4");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(0, player.getScore());
    }
    @Test
    public void test03PlayerChooses2OutOf3CorrectChoicesAndNoneIncorrectObtains2Points() {

        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",1));
        choices.add(new Choice("Answer2", "correcta",2));
        choices.add(new Choice("Answer3", "correcta",3));
        choices.add(new Choice("Answer4", "incorrecta",4));
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();
        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1,2");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(2, player.getScore());
    }

}