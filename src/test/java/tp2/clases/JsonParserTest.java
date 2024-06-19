package tp2.clases;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonParserTest {
    JsonParser jsonParser = new JsonParser();
    ArrayList<Question> questions = jsonParser.questionsParser("src/main/resources/preguntas.json");

    @Test
    public void test01CheckIfTheParsingOfTheFirsQuestionIsCorrect() {
      assertEquals(1, questions.get(0).getId());
      assertEquals("CIENCIAS", questions.get(0).getContent().getTheme());
      assertEquals("Ordene de MAYOR A MENOR los siguientes objetos hogareños según su nivel de radiación electromagnética emitido (el máximo recomendado es 100 microTeslas)", questions.get(0).getContent().getPrompt());
      assertTrue(questions.get(0).getCorrectAnswers().get(1).getCorrection().isCorrect());
      assertTrue(questions.get(0).getCorrectAnswers().get(0).getCorrection().isCorrect());
      assertTrue(questions.get(0).getCorrectAnswers().get(3).getCorrection().isCorrect());
      assertTrue(questions.get(0).getCorrectAnswers().get(2).getCorrection().isCorrect());
      assertEquals("Televisor de tubo CRT", questions.get(0).getChoices().get(0).getContent());
      assertEquals("Microondas", questions.get(0).getChoices().get(1).getContent());
      assertEquals("Imanes del delivery", questions.get(0).getChoices().get(2).getContent());
      assertEquals("Heladera", questions.get(0).getChoices().get(3).getContent());
      assertEquals("El microondas emite a  3 cm entre 73 y 200µ, y a 30 cm entre 4 a 8 µT. ", questions.get(0).getContent().getAnswerText());
    }
}