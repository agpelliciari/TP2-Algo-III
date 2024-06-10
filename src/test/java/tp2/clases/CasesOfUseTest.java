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

        TrueOrFalse question = new TrueOrFalse("UBA is the most prestigious university in Argentina", new ClassicMode(), answers, "Education");

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

        TrueOrFalse question = new TrueOrFalse("France is the last World Cup champion", new ClassicMode(), answers, "Sports");

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
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("Brazil","Incorrecta", 1,'a'));
        answers.add(new Answer("England","Correcta", 1,'b'));
        answers.add(new Answer("Egypt","Incorrecta", 1,'c'));
        answers.add(new Answer("Czech Republic","Correcta", 1,'d'));

        MultipleChoice question = new MultipleChoice("Which of the following countries is in europe", new ClassicMode(), answers, "General Knowledge");

        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();

        Player playerOne = new Player("x", 1);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "bd");

        playersAnswers.put(playerOne, answersPlayerOne);

        question.assignScore(playersAnswers);

        assertEquals(2, playerOne.getScore());
    }

    @Test
    public void test04AMultipleChoiceClassicQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("Brazil","Incorrecta", 1,'a'));
        answers.add(new Answer("England","Correcta", 1,'b'));
        answers.add(new Answer("Egypt","Incorrecta", 1,'c'));
        answers.add(new Answer("Czech Republic","Correcta", 1,'d'));

        MultipleChoice question = new MultipleChoice("Which of the following countries is in europe", new ClassicMode(), answers, "General Knowledge");
        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();

        Player playerOne = new Player("x", 1);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "abd");

        playersAnswers.put(playerOne, answersPlayerOne);

        question.assignScore(playersAnswers);

        assertEquals(1, playerOne.getScore());
    }

    @Test
    public void test05ATrueFalseQuestionWithPenaltyReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredCorrectly() {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("Verdadero","Incorrecta", 1,'a'));
        answers.add(new Answer("Falso","Correcta", 1,'b'));

        TrueOrFalse question = new TrueOrFalse("The Earth is flat", new PenaltyMode(), answers, "Science");

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

        TrueOrFalse question = new TrueOrFalse("Java is a functional programming language", new PenaltyMode(), answers, "Programming");

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
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("2014","Correcta", 1,'a'));
        answers.add(new Answer("2021","Correcta", 1,'b'));
        answers.add(new Answer("1942","Incorrecta", 1,'c'));

        MultipleChoice question = new MultipleChoice("In which of the following years was the Football World Cup held?", new PenaltyMode(), answers, "General Knowledge");

        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();
        Player playerOne = new Player("John", 1);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "ab");


        playersAnswers.put(playerOne, answersPlayerOne);

        question.assignScore(playersAnswers);

        assertEquals(3, playerOne.getScore());



    }

    @Test
    public void test08AMultipleChoiceQuestionWithPenaltyReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("Eagle","Correcta", 1,'a'));
        answers.add(new Answer("Bat","Correcta", 1,'b'));
        answers.add(new Answer("Penguin","Incorrecta", 1,'c'));
        answers.add(new Answer("Elephant","Incorrecta", 1,'d'));

        MultipleChoice question = new MultipleChoice("Which of the following animals can fly?", new PenaltyMode(), answers, "General Knowledge");

        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();
        Player playerOne = new Player("Juan", 5);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "cd");

        playersAnswers.put(playerOne, answersPlayerOne);

        question.assignScore(playersAnswers);

        assertEquals(3, playerOne.getScore());
    }
}
