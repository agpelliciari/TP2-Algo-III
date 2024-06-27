package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.player.Player;
import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.Content;
import tp2.clases.questions.modes.ClassicMode;
import tp2.clases.questions.types.MultipleChoice;
import tp2.clases.questions.types.TrueOrFalse;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExclusivityTest {
    private Game game = new Game();
    Content content = new Content("", "", "");

    @Test
    public void test01CorrectAssignmentOfScoreForPlayerWhoIsTheOnlyOneToAnsweredCorrectlyWithExclusivityInQuestionWithoutPenalty() {
        game.addPlayer(new Player("Carlos", 0));
        game.addPlayer(new Player("Ricardo", 0));
        game.addPlayer(new Player("Pedro", 0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero", "Correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        game.addQuestion(new TrueOrFalse(3, content, new ClassicMode(), choices));

        ArrayList<String[]> chosenChoices = new ArrayList<>();
        chosenChoices.add(new String[]{"1"});
        chosenChoices.add(new String[]{"2"});
        chosenChoices.add(new String[]{"2"});

        ArrayList<boolean[]> chosenExclusivities = new ArrayList<>();
        chosenExclusivities.add(new boolean[]{true});
        chosenExclusivities.add(new boolean[]{false});
        chosenExclusivities.add(new boolean[]{false});

        game.assignScoreWithExclusivity(chosenChoices, chosenExclusivities);

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

        ArrayList<String[]> chosenChoices = new ArrayList<>();
        chosenChoices.add(new String[]{"1"});
        chosenChoices.add(new String[]{"1"});
        chosenChoices.add(new String[]{"2"});

        ArrayList<boolean[]> chosenExclusivities = new ArrayList<>();
        chosenExclusivities.add(new boolean[]{true});
        chosenExclusivities.add(new boolean[]{false});
        chosenExclusivities.add(new boolean[]{false});

        game.assignScoreWithExclusivity(chosenChoices, chosenExclusivities);

       assertEquals(0, game.getPlayers().get(0).getScore());
       assertEquals(0, game.getPlayers().get(1).getScore());
    }

    @Test
    public void test03PlayerWhoUseTwoTimesExclusivityInInQuestionWithoutPenaltyDoesntHaveMoreExclusivity() {
        game.addPlayer(new Player("Carlos", 0));
        game.addPlayer(new Player("Ricardo", 0));
        game.addPlayer(new Player("Pedro", 0));

        ArrayList<Choice> choices1 = new ArrayList<>();
        choices1.add(new Choice("Verdadero", "Correcta", 1));
        choices1.add(new Choice("Falso", "Incorrecta", 2));

        game.addQuestion(new TrueOrFalse(1, content, new ClassicMode(), choices1));

        ArrayList<Choice> choices2 = new ArrayList<>();
        choices2.add(new Choice("", "Correcta", 1));
        choices2.add(new Choice("b", "Incorrecta", 2));
        choices2.add(new Choice("c", "Correcta", 3));
        choices2.add(new Choice("d", "Incorrecta", 4));

        game.addQuestion(new MultipleChoice(2, content, new ClassicMode(), choices2));

        ArrayList<String[]> chosenChoices = new ArrayList<>();
        chosenChoices.add(new String[]{"1", "1,2"});
        chosenChoices.add(new String[]{"2", "1,3"});
        chosenChoices.add(new String[]{"2", "1,3"});

        ArrayList<boolean[]> chosenExclusivities = new ArrayList<>();
        chosenExclusivities.add(new boolean[]{true, true});
        chosenExclusivities.add(new boolean[]{false, true});
        chosenExclusivities.add(new boolean[]{false, true});

        game.assignScoreWithExclusivity(chosenChoices, chosenExclusivities);

        assertEquals(0, game.getPlayers().get(0).getExclusivity().getNumber());
    }

    @Test
    public void test04IfAllPlayersAnsweredCorrectlyInQuestionWithoutPenaltyWhithSomePlayerExclusivityNoOneScores() {
        game.addPlayer(new Player("Carlos", 0));
        game.addPlayer(new Player("Ricardo", 0));
        game.addPlayer(new Player("Pedro", 0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero", "Incorrecta", 1));
        choices.add(new Choice("Falso", "Correcta", 2));

        game.addQuestion(new TrueOrFalse(1, content, new ClassicMode(), choices));

        ArrayList<String[]> chosenChoices = new ArrayList<>();
        chosenChoices.add(new String[]{"2"});
        chosenChoices.add(new String[]{"2"});
        chosenChoices.add(new String[]{"2"});

        ArrayList<boolean[]> chosenExclusivities = new ArrayList<>();
        chosenExclusivities.add(new boolean[]{true});
        chosenExclusivities.add(new boolean[]{false});
        chosenExclusivities.add(new boolean[]{false});

        game.assignScoreWithExclusivity(chosenChoices, chosenExclusivities);

        int i = 0;
        for (Player player : game.getPlayers())
            assertEquals(0, game.getPlayers().get(i++).getScore());
    }

    @Test
    public void test05IfTwoPlayersUseExclusivityAndOnlyOneAnsweredCorrectlyInQuestionWithoutPenaltyMultipliersMultiplies() {
        game.addPlayer(new Player("Carlos", 0));
        game.addPlayer(new Player("Ricardo", 0));
        game.addPlayer(new Player("Pedro", 0));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero", "Correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        game.addQuestion(new TrueOrFalse(1, content, new ClassicMode(), choices));

        ArrayList<String[]> chosenChoices = new ArrayList<>();
        chosenChoices.add(new String[]{"1"});
        chosenChoices.add(new String[]{"2"});
        chosenChoices.add(new String[]{"2"});

        ArrayList<boolean[]> chosenExclusivities = new ArrayList<>();
        chosenExclusivities.add(new boolean[]{true});
        chosenExclusivities.add(new boolean[]{true});
        chosenExclusivities.add(new boolean[]{false});

        game.assignScoreWithExclusivity(chosenChoices, chosenExclusivities);

        assertEquals(4, game.getPlayers().get(0).getScore());
    }
}
