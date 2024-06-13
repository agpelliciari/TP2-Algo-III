package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GroupChoiceTest {

    @Test
    public void test01PlayerGroupsAllOptionsRight() {
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "incorrecta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'c'));
        answers.add(new Answer("Answer4", "correcta",1,'d'));
        Content content = new Content("","");
        GroupChoice question = new GroupChoice(content, new ClassicMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();

        ArrayList<Answer> groupAAnswers = player.answer(question, "ad");
        playerAnswer.put(player, groupAAnswers);
        question.assignScore(playerAnswer);


        assertEquals(1, player.getScore());
    }

    @Test
    public void test02PlayerGroupsAllOptionsWrong() {
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "incorrecta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'c'));
        answers.add(new Answer("Answer4", "correcta",1,'d'));
        Content content = new Content("","");
        GroupChoice question = new GroupChoice(content, new ClassicMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();

        ArrayList<Answer> groupAAnswers = player.answer(question, "ad");
        playerAnswer.put(player, groupAAnswers);
        question.assignScore(playerAnswer);

        assertEquals(0, player.getScore());
    }

    @Test
    public void test03OnePlayerGetsGroupsCorrectAndOneDoesnt() {
        Player player1 = new Player("Player1",0);
        Player player2 = new Player("Player2",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "incorrecta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'c'));
        answers.add(new Answer("Answer4", "correcta",1,'d'));
        Content content = new Content("","");
        GroupChoice question = new GroupChoice(content, new ClassicMode(), answers);
        HashMap<Player, ArrayList<Answer>> playerAnswers = new HashMap<>();;

        ArrayList<Answer> groupA1Answers = player1.answer(question, "ad");
        playerAnswers.put(player1, groupA1Answers);


        ArrayList<Answer> groupA2Answers = player2.answer(question, "bd");
        playerAnswers.put(player2, groupA2Answers);
        question.assignScore(playerAnswers);

        assertEquals(0, player1.getScore());
        assertEquals(1, player2.getScore());
    }

}