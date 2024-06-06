package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceWithPenaltyTest {
    @Test
    public void test01PlayerChooses2CorrectAnd1IncorrectOutOf3CorrectOptions(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'b'));

        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        chosenAnswers.put(player, answers);

        MultipleChoiceWithPenalty type = new MultipleChoiceWithPenalty();
        type.assignScore(chosenAnswers, 3);

        assertEquals(1, player.getScore());
    }

    @Test
    public void test02PlayerChooses2CorrectAnd3IncorrectOutOf3CorrectOptionsEndsUpWithNegativePoints(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'b'));
        answers.add(new Answer("Answer4", "incorrecta",1,'b'));
        answers.add(new Answer("Answer5", "incorrecta",1,'b'));



        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        chosenAnswers.put(player, answers);

        MultipleChoiceWithPenalty type = new MultipleChoiceWithPenalty();
        type.assignScore(chosenAnswers, 3);

        assertEquals(-1, player.getScore());
    }

    @Test
    public void testMultiplePlayers() {
        Player player1 = new Player("Player1",0);
        Player player2 = new Player("Player2",0);
        Player player3 = new Player("Player3",0);

        ArrayList<Answer> answers1 = new ArrayList<>();

        answers1.add(new Answer("Answer1", "incorrecta",0,'b'));
        answers1.add(new Answer("Answer2", "correcta",0,'b'));


        ArrayList<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("Answer1", "correcta",1,'a'));
        answers2.add(new Answer("Answer2", "correcta",1,'b'));


        ArrayList<Answer> answers3 = new ArrayList<>();
        answers3.add(new Answer("Answer1", "correcta",1,'a'));
        answers3.add(new Answer("Answer2", "correcta",1,'b'));
        answers3.add(new Answer("Answer2", "correcta",1,'c'));

        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();
        chosenAnswers.put(player1, answers1);
        chosenAnswers.put(player2, answers2);
        chosenAnswers.put(player3, answers3);

        MultipleChoiceWithPenalty type = new MultipleChoiceWithPenalty();
        type.assignScore(chosenAnswers, 3);

        assertEquals(0, player1.getScore());
        assertEquals(2, player2.getScore());
        assertEquals(3, player3.getScore());
    }
}