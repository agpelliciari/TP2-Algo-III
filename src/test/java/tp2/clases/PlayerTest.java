package tp2.clases;

import tp2.clases.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PlayerTest {
    
    private Player player;
    @Mock private Question question;
    @Mock private Anwser answer;
    @Mock private Correction correction;
    @Mock private Correct correct;
    @Mock private Incorrect incorrect;
    
    @Test
    public void test01AnsweringAQuestionCorrectlyIncreasesTheScore(){
        //Arrange
        when(player.answer(question)).thenReturn(answer);
        when(answer.getCorrection()).thenReturn(correct);
        when(answer.getScore()).thenReturn(1);

        int expectedScore = 1;
        player = new Player("name", 0);

        //Act
        Answer answer = player.answer(question);
        player.assignScore(answer.getCorrection(), answer.getScore());
        int scoreObtained = player.getScore();
        
        //Assert
        Assert.assertEquals(expectedScore, scoreObtained);
        
    }

    @Test
    public void test02AnsweringAQuestionIncorrectlyDecreasesTheScore(){
        //Arrange
        when(player.answer(question)).thenReturn(answer);
        when(answer.getCorrection()).thenReturn(incorrect);
        when(answer.getScore()).thenReturn(1);

        int expectedScore = 0;
        player = new Player("name", 1);

        //Act
        Answer answer = player.answer(question);
        player.assignScore(answer.getCorrection(), answer.getScore());
        int scoreObtained = player.getScore();

        //Assert
        Assert.assertEquals(expectedScore, scoreObtained);

    }
}
