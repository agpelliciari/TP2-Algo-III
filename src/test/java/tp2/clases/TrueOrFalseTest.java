package tp2.clases;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrueOrFalseTest {

    Content content = new Content("", "", "");

    @Test
    public void test01TrueOrFalseQuestionAssignsScoreCorrectly() {
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "correcta", 1));
        choices.add(new Choice("Answer2", "incorrecta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1");
        question.assignScore(player, chosenAnswers);

        assertEquals(1, player.getScore());
    }

    @Test
    public void test02TrueOrFalseQuestionWronglyAnsweredAssignsScoreCorrectly() {
        Player player = new Player("Player1", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "incorrecta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        ArrayList<Choice> chosenAnswers = player.setAnswers(question, "1");
        question.assignScore(player, chosenAnswers);

        assertEquals(0, player.getScore());
    }

    @Test
    public void test03MultiplePlayers() {
        Player player1 = new Player("Player1", 0);
        Player player2 = new Player("Player2", 0);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Answer1", "incorrecta", 1));
        choices.add(new Choice("Answer2", "correcta", 2));
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        ArrayList<Choice> chosenAnswers1 = player1.setAnswers(question, "1");
        ArrayList<Choice> chosenAnswers2 = player2.setAnswers(question, "2");
        question.assignScore(player1, chosenAnswers1);
        question.assignScore(player2, chosenAnswers2);

        assertEquals(0, player1.getScore());
        assertEquals(1, player2.getScore());
    }
}
