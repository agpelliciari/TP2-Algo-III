package tp2.clases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CasesOfUseTest {

    @Test
    public void test01ATrueFalseQuestionReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredCorrectly() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero","correcta", 1));
        choices.add(new Choice("Falso","Incorrecta",2));

        Content content = new Content("UBA is the most prestigious university in Argentina", "Which of the following animals can fly?","");
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();

        Player player1 = new Player("x", 1);
        Player player2 = new Player("y", 1);
        Player player3 = new Player("z", 1);

        //Act
        ArrayList<Choice> answersPlayer1 = player1.setAnswers(question, "1");
        ArrayList<Choice> answersPlayer2 = player2.setAnswers(question, "2");
        ArrayList<Choice> answersPlayer3 = player3.setAnswers(question, "1");

        playersAnswers.put(player1, answersPlayer1);
        playersAnswers.put(player2, answersPlayer2);
        playersAnswers.put(player3, answersPlayer3);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(2, player1.getScore());
        assertEquals(1, player2.getScore());
        assertEquals(2, player3.getScore()); 
    }

    @Test
    public void test02ATrueFalseQuestionReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredIncorrectly() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero","incorrecta", 1));
        choices.add(new Choice("Falso","Correcta",2));

        Content content = new Content("Sports", "France is the last World Cup champion","");
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();

        Player player1 = new Player("x", 1);
        Player player2 = new Player("y", 1);
        Player player3 = new Player("z", 1);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "1");
        ArrayList<Choice> answersplayer2 = player2.setAnswers(question, "2");
        ArrayList<Choice> answersplayer3 = player3.setAnswers(question, "1");

        playersAnswers.put(player1, answersplayer1);
        playersAnswers.put(player2, answersplayer2);
        playersAnswers.put(player3, answersplayer3);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(1, player1.getScore());
        assertEquals(2, player2.getScore());
        assertEquals(1, player3.getScore()); 
        
    }

    @Test
    public void test03AMultipleChoiceClassicQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Brazil","incorrecta", 1));
        choices.add(new Choice("England","Correcta",2));
        choices.add(new Choice("Egypt","Incorrecta",3));
        choices.add(new Choice("Czech Republic","Correcta",4));

        Content content = new Content("General Knowledge", "Which of the following countries is in europe","");
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();

        Player player1 = new Player("x", 1);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "2,4");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(2, player1.getScore());
    }

    @Test
    public void test04AMultipleChoiceClassicQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Brazil","incorrecta", 1));
        choices.add(new Choice("England","Correcta",2));
        choices.add(new Choice("Egypt","Incorrecta",3));
        choices.add(new Choice("Czech Republic","Correcta",4));

        Content  content = new Content("General Knowledge", "Which of the following countries is in europe","");
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);
        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();

        Player player1 = new Player("x", 1);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "1,2,4");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(1, player1.getScore());
    }

    @Test
    public void test05ATrueFalseQuestionWithPenaltyReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredCorrectly() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero","incorrecta", 1));
        choices.add(new Choice("Falso","Correcta",2));

        Content content = new Content("Science", "The Earth is flat","");
        TrueOrFalse question = new TrueOrFalse(1, content, new PenaltyMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();

        Player player1 = new Player("x", 1);
        Player player2 = new Player("y", 1);
        Player player3 = new Player("z", 1);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "1");
        ArrayList<Choice> answersplayer2 = player2.setAnswers(question, "2");
        ArrayList<Choice> answersplayer3 = player3.setAnswers(question, "2");

        playersAnswers.put(player1, answersplayer1);
        playersAnswers.put(player2, answersplayer2);
        playersAnswers.put(player3, answersplayer3);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(0, player1.getScore());
        assertEquals(2, player2.getScore());
        assertEquals(2, player3.getScore());
    }

    @Test
    public void test06ATrueFalseQuestionWithPenaltyReceivesAListOfAnswersAndAssignsPointsToThoseWhoAnsweredIncorrectly() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero","incorrecta", 1));
        choices.add(new Choice("Falso","Correcta",2));

        Content content = new Content("Programming", "Java is a functional programming language","");
        TrueOrFalse question = new TrueOrFalse(1, content, new PenaltyMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();

        Player player1 = new Player("x", 1);
        Player player2 = new Player("y", 1);
        Player player3 = new Player("z", 1);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "1");
        ArrayList<Choice> answersplayer2 = player2.setAnswers(question, "1");
        ArrayList<Choice> answersplayer3 = player3.setAnswers(question, "1");

        playersAnswers.put(player1, answersplayer1);
        playersAnswers.put(player2, answersplayer2);
        playersAnswers.put(player3, answersplayer3);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
        assertEquals(0, player3.getScore());
    }

    @Test
    public void test07AMultipleChoiceQuestionWithPenaltyReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("2014","correcta", 1));
        choices.add(new Choice("2021","Correcta",2));
        choices.add(new Choice("1942","Incorrecta",3));

        Content content = new Content("General Knowledge", "In which of the following years was the Football World Cup held?","");
        MultipleChoice question = new MultipleChoice(1, content, new PenaltyMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();
        Player player1 = new Player("John", 1);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "1,2");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(3, player1.getScore());
    }

    @Test
    public void test08AMultipleChoiceQuestionWithPenaltyReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Eagle","correcta", 1));
        choices.add(new Choice("Bat","Correcta",2));
        choices.add(new Choice("Penguin","Incorrecta",3));
        choices.add(new Choice("Elephant","Incorrecta",4));

        Content content = new Content("General Knowledge", "Which of the following animals can fly?","");
        MultipleChoice question = new MultipleChoice(1, content, new PenaltyMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();
        Player player1 = new Player("Juan", 5);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "3,4");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(3, player1.getScore());
    }

    @Test
    public void test09APartialMultipleChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Eagle","correcta", 1));
        choices.add(new Choice("Bat","Correcta",2));
        choices.add(new Choice("Penguin","Incorrecta",3));
        choices.add(new Choice("Elephant","Incorrecta",4));

        Content content = new Content("General Knowledge", "Which of the following animals can fly?","");
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();
        Player player1 = new Player("Juan", 5);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "2");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(6, player1.getScore());
    }

    @Test
    public void test10APartialMultipleChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Eagle","correcta", 1));
        choices.add(new Choice("Bat","Correcta",2));
        choices.add(new Choice("Penguin","Incorrecta",3));
        choices.add(new Choice("Elephant","Incorrecta",4));

        Content content = new Content("General Knowledge", "Which of the following animals can fly?","");
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();
        Player player1 = new Player("Juan", 5);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "2,4");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(5, player1.getScore());
    }

    @Test
    public void test11AClassicOrderedChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Elephant","Correcta",4));
        choices.add(new Choice("Lion","Correcta",2));
        choices.add(new Choice("Monkey","Correcta",3));
        choices.add(new Choice("Fish","correcta", 1));

        Content content = new Content("General Knowledge", "Order the following animals from biggest to smallest","");
        OrderedChoice question = new OrderedChoice(1, content, new ClassicMode(), choices, new int[]{4, 2, 3, 1});

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();
        Player player1 = new Player("Lucas", 7);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "4,2,3,1");
        playersAnswers.put(player1, answersplayer1);
        question.assignScore(playersAnswers);

        //Assert
        assertEquals(8, player1.getScore());
    }

    @Test
    public void test12AClassicOrderedChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Elephant","Correcta",4));
        choices.add(new Choice("Lion","Correcta",2));
        choices.add(new Choice("Monkey","Correcta",3));
        choices.add(new Choice("Fish","correcta", 1));

        Content content = new Content("General Knowledge", "Order the following animals from biggest to smallest","");
        OrderedChoice question = new OrderedChoice(1, content, new ClassicMode(), choices, new int[]{4, 2, 3, 1});

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();
        Player player1 = new Player("Lucas", 7);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "2,4,3,1");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(7, player1.getScore());
    }

    @Test
    public void test13AClassicGroupChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Kawhi Leonard","incorrecta", 1));
        choices.add(new Choice("Lebron James","Correcta",2));
        choices.add(new Choice("Tristan Thompson","Correcta",3));
        choices.add(new Choice("Emanuel Ginobili","Incorrecta",4));
        choices.add(new Choice("Kyrie Irving","Correcta",5));
        choices.add(new Choice("Tony Parker","Incorrecta",6));

        Content content = new Content("Sports", "Match the players which played together","");
        GroupChoice question = new GroupChoice(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();
        Player player1 = new Player("Manuel", 2);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "2,3,5");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(3, player1.getScore());
    }

    @Test
    public void test14AClassicGroupChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        //Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Kawhi Leonard","incorrecta", 1));
        choices.add(new Choice("Lebron James","Correcta",2));
        choices.add(new Choice("Tristan Thompson","Correcta",3));
        choices.add(new Choice("Emanuel Ginobili","Incorrecta",4));
        choices.add(new Choice("Kyrie Irving","Correcta",'e'));
        choices.add(new Choice("Tony Parker","Incorrecta",'f'));

        Content content = new Content("Sports", "Match the players which played together","");
        GroupChoice question = new GroupChoice(1, content, new ClassicMode(), choices);

        HashMap<Player, ArrayList<Choice>> playersAnswers = new HashMap<>();
        Player player1 = new Player("Manuel", 2);

        //Act
        ArrayList<Choice> answersplayer1 = player1.setAnswers(question, "1,2,3");

        playersAnswers.put(player1, answersplayer1);

        question.assignScore(playersAnswers);

        //Assert
        assertEquals(2, player1.getScore());
    }
}
