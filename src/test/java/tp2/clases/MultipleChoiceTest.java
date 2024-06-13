package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceTest {

    Content content = new Content("", "","");

    @Test
    public void test01PlayerChoosesOneIncorrectOptionAndDoesntReceivePoints(){
        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",1));
        choices.add(new Choice("Answer2", "correcta",2));
        choices.add(new Choice("Answer3", "incorrecta",3));
        choices.add(new Choice("Answer4", "incorrecta",4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();
        ArrayList<Choice> chosenAnswers = player.Choice(question, "1,2,3");

        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(0, player.getScore());
    }

    @Test
    public void test02PlayerChoosesAllOptionsCorrect(){
        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",1));
        choices.add(new Choice("Answer2", "correcta",2));
        choices.add(new Choice("Answer3", "incorrecta",3));
        choices.add(new Choice("Answer4", "incorrecta",4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();
        ArrayList<Choice> chosenAnswers = player.Choice(question, "1,2");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(1, player.getScore());
    }

    @Test
    public void test03PlayerChoosesAllOptionsIncorrect(){
        Player player = new Player("Player1",0);
        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",1));
        choices.add(new Choice("Answer2", "correcta",2));
        choices.add(new Choice("Answer3", "incorrecta",3));
        choices.add(new Choice("Answer4", "incorrecta",4));
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        ArrayList<Choice> chosenAnswers = player.Choice(question, "3,4");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(0, player.getScore());
    }

}