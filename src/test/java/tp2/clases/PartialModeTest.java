package tp2.clases;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PartialModeTest {

    Content content = new Content("", "");

    @Test
    public void test01PlayerChoosesAllPossibleCorrectOptionsAndNoneIncorrect() {

        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "correcta",1,'c'));
        answers.add(new Answer("Answer4", "incorrecta",1,'d'));
        MultipleChoice question = new MultipleChoice(content, new PartialMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();
        ArrayList<Answer> chosenAnswers = player.answer(question, "abc");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(3, player.getScore());
    }

    @Test
    public void test02PlayerChoosesAllPossibleCorrectOptionsAndOneIncorrectObtainsZeroPoints() {

        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "correcta",1,'c'));
        answers.add(new Answer("Answer4", "incorrecta",1,'d'));
        MultipleChoice question = new MultipleChoice(content, new PartialMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();
        ArrayList<Answer> chosenAnswers = player.answer(question, "acd");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(0, player.getScore());
    }
    @Test
    public void test03PlayerChooses2OutOf3CorrectOptionsAndNoneIncorrectObtains2Points() {

        Player player = new Player("Player1",0);

        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1", "correcta",1,'a'));
        answers.add(new Answer("Answer2", "correcta",1,'b'));
        answers.add(new Answer("Answer3", "correcta",1,'c'));
        answers.add(new Answer("Answer4", "incorrecta",1,'d'));
        MultipleChoice question = new MultipleChoice(content, new PartialMode(), answers);

        HashMap<Player, ArrayList<Answer>> playerAnswer = new HashMap<>();
        ArrayList<Answer> chosenAnswers = player.answer(question, "ab");
        playerAnswer.put(player, chosenAnswers);
        question.assignScore(playerAnswer);

        assertEquals(2, player.getScore());
    }

}