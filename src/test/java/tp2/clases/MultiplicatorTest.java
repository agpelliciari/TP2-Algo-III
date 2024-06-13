package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicatorTest {
    Content content = new Content("", "");

    @Test
    public void test01TrueOrFalseQuestionAssignsScoreCorrectly(){

        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "incorrecta",1,'b'));
        TrueOrFalse question = new TrueOrFalse(content, new ClassicMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();

        player.useMultiplicator(2);
        ArrayList<Answer> chosenAnswers = player.answer(question, "a");

        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(2, player.getScore());
    }

    @Test
    public void test02TrueOrFalseQuestionAssignsScoreCorrectly(){

        Player player = new Player("Player1",4);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "incorrecta",1,'b'));
        TrueOrFalse question = new TrueOrFalse(content, new PenaltyMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();

        player.useMultiplicator(2);
        ArrayList<Answer> chosenAnswers = player.answer(question, "b");

        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(2, player.getScore());
    }

    @Test
    public void test03PlayerChoosesAllOptionsCorrectUsingx3Multiplicator(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'c'));
        answers.add(new Answer("Answer4", "incorrecta",1,'d'));
        MultipleChoice question = new MultipleChoice(content, new PartialMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();
        player.useMultiplicator(3);
        ArrayList<Answer> chosenAnswers = player.answer(question, "ab");
        playerAnswer.put(player, chosenAnswers);

        question.assignScore(playerAnswer);

        assertEquals(6, player.getScore());
    }

}
