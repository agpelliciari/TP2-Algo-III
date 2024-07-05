package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.JsonParser;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.types.GroupChoice;
import tp2.clases.model.questions.types.OrderedChoice;
import tp2.clases.model.questions.types.Question;
import tp2.clases.model.questions.types.TrueOrFalse;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonParserTest {
    Player player = new Player("Jugador", new Score(0));
    ArrayList<Question> questions = JsonParser.questionsParser("src/main/resources/preguntas.json");

    @Test
    // Correcto parseo de una pregunta de tipo Ordered Choice
    public void test01CheckIfTheParsingOfAnOrderedChoiceIsCorrect() {
        // Arrange
        OrderedChoice orderedChoice = (OrderedChoice) questions.get(0);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(orderedChoice, "2,1,4,3");
        orderedChoice.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(1, player.getScore());
        assertEquals(1, orderedChoice.getId());
        assertTrue(orderedChoice.getMode() instanceof ClassicMode);

        assertEquals("CIENCIAS", orderedChoice.getContent().getTheme());
        assertEquals("Ordene de MAYOR A MENOR los siguientes objetos hogareños según su nivel de radiación electromagnética emitido (el máximo recomendado es 100 microTeslas)", orderedChoice.getContent().getPrompt());
        assertEquals("Televisor de tubo CRT", orderedChoice.getChoices().get(0).getContent());
        assertEquals("Microondas", orderedChoice.getChoices().get(1).getContent());
        assertEquals("Imanes del delivery", orderedChoice.getChoices().get(2).getContent());
        assertEquals("Heladera", orderedChoice.getChoices().get(3).getContent());
        assertEquals("El microondas emite a  3 cm entre 73 y 200µ, y a 30 cm entre 4 a 8 µT. ", orderedChoice.getContent().getAnswerText());
    }

    @Test
    // Correcto parseo de una pregunta de tipo Group Choice
    public void test02CheckIfTheParsingOfAnGroupChoiceIsCorrect() {
        // Arrange
        GroupChoice groupChoice = (GroupChoice) questions.get(17);

        // Act
        ArrayList<Choice> chosenAnswersGroupA = player.setAnswers(groupChoice, "1,2,5");
        ArrayList<Choice> chosenAnswersGroupB = player.setAnswers(groupChoice, "3,4,6");

        ArrayList<ArrayList<Choice>> groupsChosenAnswers = new ArrayList<>();
        groupsChosenAnswers.add(chosenAnswersGroupA);
        groupsChosenAnswers.add(chosenAnswersGroupB);

        int score = groupChoice.calculateTotalScore(groupsChosenAnswers);
        player.addToScore(score);

        // Assert
        assertEquals(1, player.getScore());
        assertTrue(groupChoice.getMode() instanceof ClassicMode);
        assertEquals(18, groupChoice.getId());

        assertEquals("DEPORTES", groupChoice.getContent().getTheme());
        assertEquals("Asigne las siguientes leyendas del deporte nacional a disciplina grupales (Fútbol, Básquet, Voley, Rugby,) o individuales (atletismo, tenis, artes marciales, ajedrez, etc):", groupChoice.getContent().getPrompt());
        assertEquals("Lio Messi", groupChoice.getChoices().get(0).getContent());
        assertEquals("Manu Ginóbili", groupChoice.getChoices().get(1).getContent());
        assertEquals("Juan Martín del Potro", groupChoice.getChoices().get(2).getContent());
        assertEquals("Miguel Najdorf", groupChoice.getChoices().get(3).getContent());
        assertEquals("Hugo Conte", groupChoice.getChoices().get(4).getContent());
        assertEquals("José Meolans", groupChoice.getChoices().get(5).getContent());
        assertEquals("say no more...", groupChoice.getContent().getAnswerText());
    }

    @Test
    // Correcto parseo de una pregunta que no es ni Group Choice ni Ordered Choice, ya que tienen misma estructura
    public void test03CheckIfTheParsingOfTheFirsQuestionIsCorrect() {
        // Arrange
        TrueOrFalse trueOrFalse = (TrueOrFalse) questions.get(2);

        // Act
        ArrayList<Choice> chosenAnswers = player.setAnswers(trueOrFalse, "2");
        trueOrFalse.assignScore(player, chosenAnswers);

        // Assert
        assertEquals(1, player.getScore());
        assertTrue(trueOrFalse.getMode() instanceof ClassicMode);
        assertEquals(3, trueOrFalse.getId());

        assertEquals("CIENCIAS", trueOrFalse.getContent().getTheme());
        assertEquals("El punto de ebullición del agua a 3300m del mar es 100 grados centígrados", trueOrFalse.getContent().getPrompt());
        assertEquals("Verdadero", trueOrFalse.getChoices().get(0).getContent());
        assertEquals("Falso", trueOrFalse.getChoices().get(1).getContent());
        assertEquals("En la altura, la temperatura de ebullición es menor por la mayor presión atmosférica", trueOrFalse.getContent().getAnswerText());
    }
}