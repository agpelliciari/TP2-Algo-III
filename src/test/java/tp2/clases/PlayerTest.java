package tp2.clases;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class PlayerTest {
    
    private Player player;
    @Mock private Question question;
    @Mock private ArrayList<Answer> answers;
    @Mock private Correction correction;
    @Mock private Correct correct;
    @Mock private Incorrect incorrect;
    
    @Test
    public void test01AnsweringAQuestionCorrectlyIncreasesTheScore(){
        //Arrange
        when(player.answer(question, "")).thenReturn(answers);
        when(answers.get(0).getCorrection()).thenReturn(correct);
        when(answers.get(0).getScore()).thenReturn(1);

        int expectedScore = 1;
        player = new Player("name", 0);

        //Act
        ArrayList<Answer> answers = player.answer(question, "");
        player.assignScore(answers.get(0).getCorrection(), answers.get(0).getScore());
        int scoreObtained = player.getScore();
        
        //Assert
        assertEquals(expectedScore, scoreObtained);
        
    }

    @Test
    public void test02AnsweringAQuestionIncorrectlyDecreasesTheScore(){
        //Arrange
        when(player.answer(question)).thenReturn(answers);
        when(answers.get(0).getCorrection()).thenReturn(incorrect);
        when(answers.get(0).getScore()).thenReturn(1);

        int expectedScore = 0;
        player = new Player("name", 1);

        //Act
        ArrayList<Answer> answers = player.answer(question, "");
        player.assignScore(answers.get(0).getCorrection(), answers.get(0).getScore());
        int scoreObtained = player.getScore();

        //Assert
        assertEquals(expectedScore, scoreObtained);

    }
}
