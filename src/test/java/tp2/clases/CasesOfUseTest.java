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

        Content content = new Content("UBA is the most prestigious university in Argentina", "Which of the following animals can fly?");
        TrueOrFalse question = new TrueOrFalse(content, new ClassicMode(), answers);

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

        Content content = new Content("Sports", "France is the last World Cup champion");
        TrueOrFalse question = new TrueOrFalse(content, new ClassicMode(), answers);

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

        Content content = new Content("General Knowledge", "Which of the following countries is in europe");
        MultipleChoice question = new MultipleChoice(content, new ClassicMode(), answers);

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

        Content  content = new Content("General Knowledge", "Which of the following countries is in europe");
        MultipleChoice question = new MultipleChoice(content, new ClassicMode(), answers);
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

        Content content = new Content("Science", "The Earth is flat");
        TrueOrFalse question = new TrueOrFalse(content, new PenaltyMode(), answers);

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

        Content content = new Content("Programming", "Java is a functional programming language");
        TrueOrFalse question = new TrueOrFalse(content, new PenaltyMode(), answers);

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

        Content content = new Content("General Knowledge", "In which of the following years was the Football World Cup held?");
        MultipleChoice question = new MultipleChoice(content, new PenaltyMode(), answers);

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

        Content content = new Content("General Knowledge", "Which of the following animals can fly?");
        MultipleChoice question = new MultipleChoice(content, new PenaltyMode(), answers);

        HashMap<Player, ArrayList<Answer>> playersAnswers = new HashMap<>();
        Player playerOne = new Player("Juan", 5);

        ArrayList<Answer> answersPlayerOne = playerOne.answer(question, "cd");

        playersAnswers.put(playerOne, answersPlayerOne);

        question.assignScore(playersAnswers);

        assertEquals(3, playerOne.getScore());
    }
}
