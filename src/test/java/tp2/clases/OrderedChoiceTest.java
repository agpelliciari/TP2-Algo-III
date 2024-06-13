package tp2.clases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class OrderedChoiceTest {

    Content content = new Content("", "","");

    @Test
    public void test01PlayerGetsOrderCorrectReceivesOnePoint(){
        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",1));
        choices.add(new Choice("Answer2", "correcta",3));
        choices.add(new Choice("Answer3", "correcta",2));
        choices.add(new Choice("Answer4", "correcta",4));
        OrderedChoice question = new OrderedChoice(1, content, new ClassicMode(), choices, new int[]{1,2,3,4});

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();
        ArrayList<Choice> chosenAnswers = player.Choice(question, "1,3,2,4");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(1, player.getScore());
    }
    @Test
    public void test02PlayerGetsOrderIncorrectReceivesZeroPoints(){
        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",'1'));
        choices.add(new Choice("Answer2", "correcta",'3'));
        choices.add(new Choice("Answer3", "correcta",'2'));
        choices.add(new Choice("Answer4", "correcta",'4'));
        OrderedChoice question = new OrderedChoice(1, content, new ClassicMode(), choices, new int[]{1,3,2,4});

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();
        ArrayList<Choice> chosenAnswers = player.Choice(question, "1,2,3,4");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(0, player.getScore());
    }
}