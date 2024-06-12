package tp2.clases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class OrderedChoiceTest {

    Content content = new Content("", "");

    @Test
    public void test01PlayerGetsOrderCorrectReceivesOnePoint(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'c'));
        answers.add(new Answer("Answer3", "correcta",1,'b'));
        answers.add(new Answer("Answer4", "correcta",1,'d'));
        OrderedChoice question = new OrderedChoice(content, new ClassicMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();
        ArrayList<Answer> chosenAnswers = player.answer(question, "acbd");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(1, player.getScore());
    }
    @Test
    public void test02PlayerGetsOrderIncorrectRecievesZeroPoints(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'c'));
        answers.add(new Answer("Answer3", "correcta",1,'b'));
        answers.add(new Answer("Answer4", "correcta",1,'d'));
        OrderedChoice question = new OrderedChoice(content, new ClassicMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();
        ArrayList<Answer> chosenAnswers = player.answer(question, "abcd");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(0, player.getScore());
    }
}