package tp2.clases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentTest {
    private Content aContent;
    private Content otherContent;

    @Test
    public void test01ItIsPossibleToKnowIfTwoContentsHaveTheSameTheme(){
        //Arrange
        boolean expectedResult = true;
        aContent = new Content("Art", "The painter Leonardo Da Vici painted the painting Las Meninas","");
        otherContent = new Content("Art", "The painter Picaso painted the painting La Monalisa","");

        //Act
        String searchedTheme = otherContent.getTheme();
        boolean obtainedResult = aContent.hasTheme(searchedTheme);

        //Assert
        assertEquals(expectedResult, obtainedResult);
    }
}