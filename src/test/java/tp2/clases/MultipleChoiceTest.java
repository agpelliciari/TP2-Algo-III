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
        answers.add(new Answer("Answer3", "incorrecta",1,'c'));
        answers.add(new Answer("Answer4", "incorrecta",1,'d'));
        MultipleChoice question = new MultipleChoice("", new ClassicMode(), answers, "");

        player.answer(question, "abc");

        assertEquals(0, player.getScore());
    }

    @Test
    public void test02PlayerChoosesAllOptionsCorrect(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'c'));
        answers.add(new Answer("Answer4", "incorrecta",1,'d'));
        MultipleChoice question = new MultipleChoice("", new ClassicMode(), answers, "");

        player.answer(question, "ab");

        assertEquals(1, player.getScore());
    }

    @Test
    public void test03PlayerChoosesAllOptionsIncorrect(){
        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "incorrecta",1,'c'));
        answers.add(new Answer("Answer4", "incorrecta",1,'d'));
        MultipleChoice question = new MultipleChoice("", new ClassicMode(), answers, "");

        player.answer(question, "cd");

        assertEquals(0, player.getScore());
    }

}