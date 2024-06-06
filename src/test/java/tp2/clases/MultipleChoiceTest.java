package tp2.clases;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceTest {

    @Test
    public void test01PlayerChoosesOneIncorrectOptionAndDoesntRecievePoints(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'b'));

        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        chosenAnswers.put(player, answers);

        MultipleChoice type = new MultipleChoice();
        type.assignScore(chosenAnswers, 2);

        assertEquals(0, player.getScore());
    }

    @Test
    public void test02PlayerChoosesAllOptionsCorrect(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));

        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        chosenAnswers.put(player, answers);

        MultipleChoice type = new MultipleChoice();
        type.assignScore(chosenAnswers, 2);

        assertEquals(1, player.getScore());
    }

    @Test
    public void test03PlayerChoosesAllOptionsIncorrect(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "incorrecta",1,'a'));
        answers.add(new Answer("Answer2", "incorrecta",1,'b'));

        HashMap<Player, ArrayList<Answer>> chosenAnswers = new HashMap<>();

        chosenAnswers.put(player, answers);

        MultipleChoice type = new MultipleChoice();
        type.assignScore(chosenAnswers, 2);

        assertEquals(0, player.getScore());
    }

}