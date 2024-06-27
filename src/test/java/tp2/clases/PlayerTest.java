package tp2.clases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tp2.clases.player.Player;
import tp2.clases.questions.choice.Choice;
import tp2.clases.questions.choice.corrections.types.Correct;
import tp2.clases.questions.choice.corrections.types.Incorrect;
import tp2.clases.exceptions.InvalidNumberOfChosenChoicesException;
import tp2.clases.exceptions.InvalidChoiceIndexException;
import tp2.clases.questions.types.Question;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class PlayerTest {
    private Player player;
    @Mock private Question questionMock;
    @Mock private Choice answerMock;
    private Correct correct;
    private Incorrect incorrect;

    private AutoCloseable closeable;

    private ArrayList<Choice> chosenAnswers;

    //mocks are initialized
    @BeforeEach
    public void beforeEach() throws InvalidNumberOfChosenChoicesException, InvalidChoiceIndexException {
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.reset(questionMock, answerMock);

        chosenAnswers = new ArrayList<>();
        chosenAnswers.add(answerMock);

        when(questionMock.assignChosenChoicesToPlayer("")).thenReturn(chosenAnswers);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
    
    @Test
    public void test01AnsweringAQuestionCorrectlyIncreasesTheScore() {
        //Arrange
        correct = new Correct();
        when(chosenAnswers.get(0).getCorrection()).thenReturn(correct);

        int expectedScore = 1;
        player = new Player("name", 0);

        //Act
        ArrayList<Choice> choices = player.setAnswers(questionMock, "");
        choices.add(answerMock);
        player.assignScore(choices.get(0).getCorrection(), 1);
        int scoreObtained = player.getScore();

        //Assert
        assertEquals(scoreObtained, expectedScore);
    }

    @Test
    public void test02AnsweringAQuestionIncorrectlyDecreasesTheScore() {
        //Arrange
        incorrect = new Incorrect();
        when(chosenAnswers.get(0).getCorrection()).thenReturn(incorrect);
        int expectedScore = 0;
        player = new Player("name", 1);

        //Act
        ArrayList<Choice> choices = player.setAnswers(questionMock, "");
        choices.add(answerMock);
        player.assignScore(choices.get(0).getCorrection(), 1);
        int scoreObtained = player.getScore();

        //Assert
        assertEquals(expectedScore, scoreObtained);
    }

    @Test
    public void test03IfThereIsAnActiveNullifierIfAnotherPlayerAnswersCorrectlyNoneOfThemReceivePoints() {
        //Arrange
        correct = new Correct();
        when(chosenAnswers.get(0).getCorrection()).thenReturn(correct);
        Player player1 = new Player("ABC", 0);
        Player player2 = new Player("DEF", 0);

        //Act
        player1.useNullifier();
        if(!player2.nullifierIsActive()) {
            player2.aNullifierisActivated();
        }

        ArrayList<Choice> choices = player2.setAnswers(questionMock, "");
        choices.add(answerMock);
        player2.assignScore(choices.get(0).getCorrection(), 1);
        player1.assignScore(choices.get(0).getCorrection(), 1);

        //Assert
        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
    }
}
