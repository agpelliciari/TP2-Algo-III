package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GroupChoiceTest {

    @Test
    public void test01PlayerGroupsAllOptionsRight() {
        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta",1));
        choices.add(new Choice("Answer2", "incorrecta",2));
        choices.add(new Choice("Answer3", "incorrecta",3));
        choices.add(new Choice("Answer4", "correcta",4));
        Content content = new Content("","","");
        GroupChoice question = new GroupChoice(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();

        ArrayList<Choice> groupAAnswers = player.Choice(question, "1,4");
        playerAnswer.put(player, groupAAnswers);
        question.assignScore(playerAnswer);


        assertEquals(1, player.getScore());
    }

    @Test
    public void test02PlayerGroupsAllOptionsWrong() {
        Player player = new Player("Player1",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "incorrecta",1));
        choices.add(new Choice("Answer2", "correcta",2));
        choices.add(new Choice("Answer3", "incorrecta",3));
        choices.add(new Choice("Answer4", "correcta",4));
        Content content = new Content("","","");
        GroupChoice question = new GroupChoice(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playerAnswer = new HashMap<>();

        ArrayList<Choice> groupAAnswers = player.Choice(question, "1,4");
        playerAnswer.put(player, groupAAnswers);
        question.assignScore(playerAnswer);

        assertEquals(0, player.getScore());
    }

    @Test
    public void test03OnePlayerGetsGroupsCorrectAndOneDoesnt() {
        Player player1 = new Player("Player1",0);
        Player player2 = new Player("Player2",0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "incorrecta",1));
        choices.add(new Choice("Answer2", "correcta",2));
        choices.add(new Choice("Answer3", "incorrecta",3));
        choices.add(new Choice("Answer4", "correcta",4));
        Content content = new Content("","","");
        GroupChoice question = new GroupChoice(1, content, new ClassicMode(), choices);
        HashMap<Player, ArrayList<Choice>> playerAnswers = new HashMap<>();;

        ArrayList<Choice> groupA1Answers = player1.Choice(question, "1,4");
        playerAnswers.put(player1, groupA1Answers);

        ArrayList<Choice> groupA2Answers = player2.Choice(question, "2,4");
        playerAnswers.put(player2, groupA2Answers);
        question.assignScore(playerAnswers);

        assertEquals(0, player1.getScore());
        assertEquals(1, player2.getScore());
    }
}