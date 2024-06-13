package tp2.clases;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExclusivityTest {
    private Game game = new Game(10);
    Content content = new Content("", "", "");

    public static boolean intToBool(int num) {
        return num != 0;
    }

    @Test
    public void test01CorrectAssignmentOfScoreForPlayerWhoIsTheOnlyOneToAnsweredCorrectlyWithExclusivityInQuestionWithoutPenalty() {
        game.addPlayer(new Player("Carlos", 0));
        game.addPlayer(new Player("Ricardo", 0));
        game.addPlayer(new Player("Pedro", 0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero", "Correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        game.addQuestion(new TrueOrFalse(3, content, new ClassicMode(), choices));

        for (Question question : game.getQuestions()) {
            int[] playersCorrectAnswers = new int[game.getPlayers().size()];

            Player playerWhoAnsweredCorrectly = null;
            game.getPlayers().get(0).assignExclusivity(true);
            if (intToBool(playersCorrectAnswers[0] = question.getNumberOfCorrectAnswers(game.getPlayers().get(0).setAnswers(question, "1")))) {
                playerWhoAnsweredCorrectly = game.getPlayers().get(0);
                game.getPlayers().get(0).setNumberOfCorrectAnswers(playersCorrectAnswers[0]);
            }

            game.getPlayers().get(1).assignExclusivity(false);
            if (intToBool(playersCorrectAnswers[1] = question.getNumberOfCorrectAnswers(game.getPlayers().get(1).setAnswers(question, "2")))) {
                playerWhoAnsweredCorrectly = game.getPlayers().get(1);
                game.getPlayers().get(1).setNumberOfCorrectAnswers(playersCorrectAnswers[1]);
            }

            game.getPlayers().get(2).assignExclusivity(false);
            if (intToBool(playersCorrectAnswers[2] = question.getNumberOfCorrectAnswers(game.getPlayers().get(2).setAnswers(question, "2")))) {
                playerWhoAnsweredCorrectly = game.getPlayers().get(2);
                game.getPlayers().get(2).setNumberOfCorrectAnswers(playersCorrectAnswers[2]);
            }

            if (game.checkIfOnlyOneCorrectAnswer(playersCorrectAnswers)) {
                assert playerWhoAnsweredCorrectly != null;
                if (playerWhoAnsweredCorrectly.getExclusivity().getBool()) {
                    playerWhoAnsweredCorrectly.assignScore(new Correct(), playerWhoAnsweredCorrectly.getNumberOfCorrectAnswers() * playerWhoAnsweredCorrectly.getExclusivity().getMultiplier());
                }
            }
        }
        assertEquals(2, game.getPlayers().get(0).getScore());
    }

    @Test
    public void test02CorrectAssignmentOfScoreForPlayerWhoIsNotTheOnlyOneToAnsweredCorrectlyWithExclusivityInQuestionWithoutPenalty() {
        game.addPlayer(new Player("Carlos", 0));
        game.addPlayer(new Player("Ricardo", 0));
        game.addPlayer(new Player("Pedro", 0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero", "Correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        game.addQuestion(new TrueOrFalse(3, content, new ClassicMode(), choices));

        for (Question question : game.getQuestions()) {
            int[] playersCorrectAnswers = new int[game.getPlayers().size()];

            ArrayList<Player> playersWhoAnsweredCorrectly = new ArrayList<>();
            game.getPlayers().get(0).assignExclusivity(true);
            if (intToBool(playersCorrectAnswers[0] = question.getNumberOfCorrectAnswers(game.getPlayers().get(0).setAnswers(question, "1")))) {
                playersWhoAnsweredCorrectly.add(game.getPlayers().get(0));
                game.getPlayers().get(0).setNumberOfCorrectAnswers(playersCorrectAnswers[0]);
            }

            game.getPlayers().get(1).assignExclusivity(false);
            if (intToBool(playersCorrectAnswers[1] = question.getNumberOfCorrectAnswers(game.getPlayers().get(1).setAnswers(question, "1")))) {
                playersWhoAnsweredCorrectly.add(game.getPlayers().get(1));
                game.getPlayers().get(1).setNumberOfCorrectAnswers(playersCorrectAnswers[1]);
            }

            game.getPlayers().get(2).assignExclusivity(false);
            if (intToBool(playersCorrectAnswers[2] = question.getNumberOfCorrectAnswers(game.getPlayers().get(2).setAnswers(question, "2")))) {
                playersWhoAnsweredCorrectly.add(game.getPlayers().get(2));
                game.getPlayers().get(2).setNumberOfCorrectAnswers(playersCorrectAnswers[2]);
            }

            if (game.checkIfOnlyOneCorrectAnswer(playersCorrectAnswers)) {
                assert playersWhoAnsweredCorrectly.get(0) != null;
                if (playersWhoAnsweredCorrectly.get(0).getExclusivity().getBool()) {
                    playersWhoAnsweredCorrectly.get(0).assignScore(new Correct(), playersWhoAnsweredCorrectly.get(0).getNumberOfCorrectAnswers() * playersWhoAnsweredCorrectly.get(0).getExclusivity().getMultiplier());
                }
            } else {
                for (Player playerWhoAnsweredCorrectly : playersWhoAnsweredCorrectly) {
                    playerWhoAnsweredCorrectly.assignScore(new Correct(), playerWhoAnsweredCorrectly.getNumberOfCorrectAnswers());
                }
            }
        }
        assertEquals(1, game.getPlayers().get(0).getScore());
        assertEquals(1, game.getPlayers().get(1).getScore());
    }
}
