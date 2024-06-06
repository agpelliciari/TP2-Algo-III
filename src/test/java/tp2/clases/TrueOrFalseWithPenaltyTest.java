package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TrueOrFalseWithPenaltyTest {
    @Test
    public void test01TrueOrFalseQuestionChoosesRightOptionAssignsScoreCorrectly(){

        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));


        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        chosenAnswers.put(player, answers);

        TrueOrFalseWithPenalty type = new TrueOrFalseWithPenalty();
        type.assignScore(chosenAnswers, 1);

        assertEquals(1, player.getScore());
    }


    @Test
    public void test02TrueOrFalseQuestionChoosesWrongOptionAssignsScoreCorrectly(){

        Player player = new Player("Player1",1);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "incorrecta",1,'a'));


        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        chosenAnswers.put(player, answers);

        TrueOrFalseWithPenalty type = new TrueOrFalseWithPenalty();
        type.assignScore(chosenAnswers, 1);

        assertEquals(0, player.getScore());
    }
    @Test
    public void test03MultiplePlayersAssignsScoreCorrectly() {
        Player player1 = new Player("Player1",2);
        Player player2 = new Player("Player2",3);

        ArrayList<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("Answer2", "incorrecta",0,'b'));

        ArrayList<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("Answer1", "correcta",1,'a'));

        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();
        chosenAnswers.put(player1, answers1);
        chosenAnswers.put(player2, answers2);

        TrueOrFalseWithPenalty type = new TrueOrFalseWithPenalty();
        type.assignScore(chosenAnswers, 1);

        assertEquals(1, player1.getScore());
        assertEquals(4, player2.getScore());
    }
}