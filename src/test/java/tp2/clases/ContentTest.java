package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.questions.Content;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContentTest {
    private Content aContent;
    private Content otherContent;

    @Test
    // Prueba si son iguales los temas de dos contenidos con distinto prompt con el método de Content HasTheme
    public void test01ItIsPossibleToKnowIfTwoContentsHaveTheSameTheme() {
        // Arrange
        aContent = new Content("Art", "The painter Leonardo Da Vici painted the painting Las Meninas","");
        otherContent = new Content("Art", "The painter Picaso painted the painting La Monalisa","");

        // Act
        String searchedTheme = otherContent.getTheme();
        boolean obtainedResult = aContent.hasTheme(searchedTheme);

        // Assert
        assertTrue(obtainedResult);
    }
}