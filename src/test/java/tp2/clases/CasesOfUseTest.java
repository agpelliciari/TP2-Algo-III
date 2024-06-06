package tp2.clases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CasesOfUseTest {

    @Test
    public void test01ATrueFalseQuestionReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredCorrectly() {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("Verdadero","Correcta", 1,'a'));
        answers.add(new Answer("Falso","Incorrecta", 1,'b'));

        Question question = new Question("UBA is the most prestigious university in Argentina", new TrueOrFalse(), answers, "Education");

        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();

        Player playerOne = new Player("x", 1);
        Player playerTwo = new Player("y", 1);
        Player playerThree = new Player("z", 1);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "a");
        ArrayList<Answer> answersPlayerTwo = playerTwo.answer(question, "b");
        ArrayList<Answer> answersPlayerThree = playerThree.answer(question, "a");

        playersAnswers.put(playerOne, answersPlayerOne);
        playersAnswers.put(playerTwo, answersPlayerTwo);
        playersAnswers.put(playerThree, answersPlayerThree);

        question.assignScore(playersAnswers);

        assertEquals(2, playerOne.getScore());
        assertEquals(1, playerTwo.getScore());
        assertEquals(2, playerThree.getScore()); 
    }

    @Test
    public void test02ATrueFalseQuestionReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredIncorrectly() {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("Verdadero","Incorrecta", 1,'a'));
        answers.add(new Answer("Falso","Correcta", 1,'b'));

        Question question = new Question("France is the last World Cup champion", new TrueOrFalse(), answers, "Sports");

        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();

        Player playerOne = new Player("x", 1);
        Player playerTwo = new Player("y", 1);
        Player playerThree = new Player("z", 1);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "a");
        ArrayList<Answer> answersPlayerTwo = playerTwo.answer(question, "b");
        ArrayList<Answer> answersPlayerThree = playerThree.answer(question, "a");

        playersAnswers.put(playerOne, answersPlayerOne);
        playersAnswers.put(playerTwo, answersPlayerTwo);
        playersAnswers.put(playerThree, answersPlayerThree);

        question.assignScore(playersAnswers);

        assertEquals(1, playerOne.getScore());
        assertEquals(2, playerTwo.getScore());
        assertEquals(1, playerThree.getScore()); 
        
    }

    @Test
    public void test03AMultipleChoiceClassicQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        
    }

    @Test
    public void test04AMultipleChoiceClassicQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        
    }

    @Test
    public void test05ATrueFalseQuestionWithPenaltyReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredCorrectly() {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("Verdadero","Incorrecta", 1,'a'));
        answers.add(new Answer("Falso","Correcta", 1,'b'));

        Question question = new Question("The Earth is flat", new TrueOrFalseWithPenalty(), answers, "Science");

        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();

        Player playerOne = new Player("x", 1);
        Player playerTwo = new Player("y", 1);
        Player playerThree = new Player("z", 1);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "a");
        ArrayList<Answer> answersPlayerTwo = playerTwo.answer(question, "b");
        ArrayList<Answer> answersPlayerThree = playerThree.answer(question, "b");

        playersAnswers.put(playerOne, answersPlayerOne);
        playersAnswers.put(playerTwo, answersPlayerTwo);
        playersAnswers.put(playerThree, answersPlayerThree);

        question.assignScore(playersAnswers);

        assertEquals(0, playerOne.getScore());
        assertEquals(2, playerTwo.getScore());
        assertEquals(2, playerThree.getScore());
    }

    @Test
    public void test06ATrueFalseQuestionWithPenaltyReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredIncorrectly() {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("Verdadero","Incorrecta", 1,'a'));
        answers.add(new Answer("Falso","Correcta", 1,'b'));

        Question question = new Question("Java is a functional programming language", new TrueOrFalseWithPenalty(), answers, "Programming");

        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();

        Player playerOne = new Player("x", 1);
        Player playerTwo = new Player("y", 1);
        Player playerThree = new Player("z", 1);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "a");
        ArrayList<Answer> answersPlayerTwo = playerTwo.answer(question, "a");
        ArrayList<Answer> answersPlayerThree = playerThree.answer(question, "a");

        playersAnswers.put(playerOne, answersPlayerOne);
        playersAnswers.put(playerTwo, answersPlayerTwo);
        playersAnswers.put(playerThree, answersPlayerThree);

        question.assignScore(playersAnswers);

        assertEquals(0, playerOne.getScore());
        assertEquals(0, playerTwo.getScore());
        assertEquals(0, playerThree.getScore()); 
        
    }

    @Test
    public void test07AMultipleChoiceQuestionWithPenaltyReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        
    }

    @Test
    public void test08AMultipleChoiceQuestionWithPenaltyReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        
    }
}
