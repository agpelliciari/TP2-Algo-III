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


        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        chosenAnswers.put(player, answers);

        TrueOrFalse type = new TrueOrFalse();
        type.assignScore(chosenAnswers, 2);

        assertEquals(1, player.getScore());
    }

    @Test
    public void test02TrueOrFalseQuestionWronglyAnsweredAssignsScoreCorrectly(){

        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "incorrecta",1,'a'));

        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        TrueOrFalse type = new TrueOrFalse();
        type.assignScore(chosenAnswers, 1);

        assertEquals(0, player.getScore());
    }

    @Test
    public void testMultiplePlayers() {
        Player player1 = new Player("Player1",0);
        Player player2 = new Player("Player2",0);

        ArrayList<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("Answer2", "incorrecta",0,'b'));

        ArrayList<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("Answer1", "correcta",1,'a'));

        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();
        chosenAnswers.put(player1, answers1);
        chosenAnswers.put(player2, answers2);

        TrueOrFalse type = new TrueOrFalse();
        type.assignScore(chosenAnswers, 1);

        assertEquals(0, player1.getScore());
        assertEquals(1, player2.getScore());
    }
}