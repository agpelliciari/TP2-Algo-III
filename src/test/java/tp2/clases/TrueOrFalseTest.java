package tp2.clases;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TrueOrFalseTest {
    @Test
    public void test01TrueOrFalseQuestionAssignsScoreCorrectly(){

        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "incorrecta",1,'b'));
        TrueOrFalse question = new TrueOrFalse("", new ClassicMode(), answers, "");

        player.answer(question, "a");

        assertEquals(1, player.getScore());
    }

    @Test
    public void test02TrueOrFalseQuestionWronglyAnsweredAssignsScoreCorrectly(){

        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "incorrecta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        TrueOrFalse question = new TrueOrFalse("", new ClassicMode(), answers, "");

        player.answer(question, "a");

        assertEquals(0, player.getScore());
    }

    @Test
    public void testMultiplePlayers() {
        Player player1 = new Player("Player1",0);
        Player player2 = new Player("Player2",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "incorrecta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        TrueOrFalse question = new TrueOrFalse("", new ClassicMode(), answers, "");

        player1.answer(question, "a");
        player2.answer(question, "b");

        assertEquals(0, player1.getScore());
        assertEquals(1, player2.getScore());
    }
}