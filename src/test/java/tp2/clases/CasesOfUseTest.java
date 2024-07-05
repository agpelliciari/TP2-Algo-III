package tp2.clases;

import org.junit.jupiter.api.Test;
import tp2.clases.model.player.Player;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.Choice;
import tp2.clases.model.questions.Content;
import tp2.clases.model.questions.modes.ClassicMode;
import tp2.clases.model.questions.modes.PartialMode;
import tp2.clases.model.questions.modes.PenaltyMode;
import tp2.clases.model.questions.types.GroupChoice;
import tp2.clases.model.questions.types.MultipleChoice;
import tp2.clases.model.questions.types.OrderedChoice;
import tp2.clases.model.questions.types.TrueOrFalse;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasesOfUseTest {

    @Test
    // Una pregunta True or False clásica recibe respuestas de tres jugadores y asigna los puntos correctamente
    public void test01ATrueFalseQuestionReceivesAnswersAndAssignsPointsCorrectly() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero", "correcta", 1));
        choices.add(new Choice("Falso", "Incorrecta", 2));

        Content content = new Content("UBA is the most prestigious university in Argentina", "Which of the following animals can fly?", "");
        TrueOrFalse question = new TrueOrFalse(1, content, new ClassicMode(), choices);

        Player player1 = new Player("x", new Score(1));
        Player player2 = new Player("y", new Score(1));
        Player player3 = new Player("z", new Score(1));

        // Act
        ArrayList<Choice> chosenAnswers1 = player1.setAnswers(question, "1");
        ArrayList<Choice> chosenAnswers2 = player2.setAnswers(question, "2");
        ArrayList<Choice> chosenAnswers3 = player3.setAnswers(question, "1");

        question.assignScore(player1, chosenAnswers1);
        question.assignScore(player2, chosenAnswers2);
        question.assignScore(player3, chosenAnswers3);

        // Assert
        assertEquals(2, player1.getScore());
        assertEquals(1, player2.getScore());
        assertEquals(2, player3.getScore());
    }

    @Test
    // Una pregunta Multiple Choice clásica recibe una lista de opciones como respuesta y asigna un punto
    public void test02AMultipleChoiceClassicQuestionReceivesAListOfAnswersOfAPlayerAndAssignsTheScoreCorrectly() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Brazil", "incorrecta", 1));
        choices.add(new Choice("England", "Correcta", 2));
        choices.add(new Choice("Egypt", "Incorrecta", 3));
        choices.add(new Choice("Czech Republic", "Correcta", 4));

        Content content = new Content("General Knowledge", "Which of the following countries is in europe", "");
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        Player player1 = new Player("x", new Score(1));

        // Act
        ArrayList<Choice> chosenAnswers = player1.setAnswers(question, "2,4");

        question.assignScore(player1, chosenAnswers);

        // Assert
        assertEquals(2, player1.getScore());
    }

    @Test
    // Una pregunta Multiple Choice clásica recibe una lista de opciones como respuesta y no suma puntos por ser incorrecta
    public void test03AMultipleChoiceClassicQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndDoesntAssignsTheScore() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Brazil", "incorrecta", 1));
        choices.add(new Choice("England", "Correcta", 2));
        choices.add(new Choice("Egypt", "Incorrecta", 3));
        choices.add(new Choice("Czech Republic", "Correcta", 4));

        Content content = new Content("General Knowledge", "Which of the following countries is in europe", "");
        MultipleChoice question = new MultipleChoice(1, content, new ClassicMode(), choices);

        Player player1 = new Player("x", new Score(1));

        // Act
        ArrayList<Choice> chosenAnswers = player1.setAnswers(question, "1,2,4");

        question.assignScore(player1, chosenAnswers);

        // Assert
        assertEquals(1, player1.getScore());
    }

    @Test
    // Una pregunta True or False con penalidad recibe respuestas de jugadores y asigna puntos correctamente
    public void test04ATrueFalseQuestionWithPenaltyReceivesAnswersAndAssignsPointsCorrectly() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Verdadero", "incorrecta", 1));
        choices.add(new Choice("Falso", "Correcta", 2));

        Content content = new Content("Science", "The Earth is flat", "");
        TrueOrFalse question = new TrueOrFalse(1, content, new PenaltyMode(), choices);

        Player player1 = new Player("x", new Score(1));
        Player player2 = new Player("y", new Score(1));
        Player player3 = new Player("z", new Score(1));

        // Act
        ArrayList<Choice> chosenAnswers1 = player1.setAnswers(question, "1");
        ArrayList<Choice> chosenAnswers2 = player2.setAnswers(question, "2");
        ArrayList<Choice> chosenAnswers3 = player3.setAnswers(question, "2");

        question.assignScore(player1, chosenAnswers1);
        question.assignScore(player2, chosenAnswers2);
        question.assignScore(player3, chosenAnswers3);

        // Assert
        assertEquals(0, player1.getScore());
        assertEquals(2, player2.getScore());
        assertEquals(2, player3.getScore());
    }

    @Test
    // Una pregunta Multiple Choice con penalidad recibe una lista de opciones correctas y asigna correctamente el puntaje
    public void test05AMultipleChoiceQuestionWithPenaltyReceivesAnswerOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("2014", "correcta", 1));
        choices.add(new Choice("2021", "Correcta", 2));
        choices.add(new Choice("1942", "Incorrecta", 3));

        Content content = new Content("General Knowledge", "In which of the following years was the Football World Cup held?", "");
        MultipleChoice question = new MultipleChoice(1, content, new PenaltyMode(), choices);

        Player player1 = new Player("John", new Score(1));

        // Act
        ArrayList<Choice> chosenAnswers = player1.setAnswers(question, "1,2");

        question.assignScore(player1, chosenAnswers);

        // Assert
        assertEquals(3, player1.getScore());
    }

    @Test
    // Una pregunta Multiple Choice con penalidad recibe una lista de opciones incorrectas y resta puntaje por cada opción incorrecta
    public void test06AMultipleChoiceQuestionWithPenaltyReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Eagle", "correcta", 1));
        choices.add(new Choice("Bat", "Correcta", 2));
        choices.add(new Choice("Penguin", "Incorrecta", 3));
        choices.add(new Choice("Elephant", "Incorrecta", 4));

        Content content = new Content("General Knowledge", "Which of the following animals can fly?", "");
        MultipleChoice question = new MultipleChoice(1, content, new PenaltyMode(), choices);

        Player player1 = new Player("Juan", new Score(5));

        // Act
        ArrayList<Choice> chosenAnswers = player1.setAnswers(question, "3,4");

        question.assignScore(player1, chosenAnswers);

        // Assert
        assertEquals(3, player1.getScore());
    }

    @Test
    // Una pregunta Multiple Choice parcial recibe una respuesta correcta y asigna un punto
    public void test07APartialMultipleChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Eagle", "correcta", 1));
        choices.add(new Choice("Bat", "Correcta", 2));
        choices.add(new Choice("Penguin", "Incorrecta", 3));
        choices.add(new Choice("Elephant", "Incorrecta", 4));

        Content content = new Content("General Knowledge", "Which of the following animals can fly?", "");
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        Player player1 = new Player("Juan", new Score(5));

        // Act
        ArrayList<Choice> chosenAnswers = player1.setAnswers(question, "2");

        question.assignScore(player1, chosenAnswers);

        // Assert
        assertEquals(6, player1.getScore());
    }

    @Test
    // Una pregunta Multiple Choice con puntaje parcial recibe una respuesta incorrecta no asigna puntos
    public void test08APartialMultipleChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Eagle", "correcta", 1));
        choices.add(new Choice("Bat", "Correcta", 2));
        choices.add(new Choice("Penguin", "Incorrecta", 3));
        choices.add(new Choice("Elephant", "Incorrecta", 4));

        Content content = new Content("General Knowledge", "Which of the following animals can fly?", "");
        MultipleChoice question = new MultipleChoice(1, content, new PartialMode(), choices);

        Player player1 = new Player("Juan", new Score(5));

        // Act
        ArrayList<Choice> chosenAnswers = player1.setAnswers(question, "2,4");

        question.assignScore(player1, chosenAnswers);

        // Assert
        assertEquals(5, player1.getScore());
    }

    @Test
    // Una pregunta Ordered Choice clásica recibe una respuesta correcta y asigna un punto
    public void test09AClassicOrderedChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Fish", "correcta", 1));
        choices.add(new Choice("Lion", "Correcta", 2));
        choices.add(new Choice("Monkey", "Correcta", 3));
        choices.add(new Choice("Elephant", "Correcta", 4));

        Content content = new Content("General Knowledge", "Order the following animals from biggest to smallest", "");
        OrderedChoice question = new OrderedChoice(1, content, new ClassicMode(), choices, new int[] {4, 2, 3, 1});

        Player player1 = new Player("Lucas", new Score(7));

        // Act
        ArrayList<Choice> chosenAnswers = player1.setAnswers(question, "4,2,3,1");

        question.assignScore(player1, chosenAnswers);

        // Assert
        assertEquals(8, player1.getScore());
    }

    @Test
    // Una pregunta Ordered Choice clásica recibe una respuesta incorrecta y no asigna puntos
    public void test10AClassicOrderedChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
        // Arrange
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Elephant", "Correcta", 4));
        choices.add(new Choice("Lion", "Correcta", 2));
        choices.add(new Choice("Monkey", "Correcta", 3));
        choices.add(new Choice("Fish", "correcta", 1));

        Content content = new Content("General Knowledge", "Order the following animals from biggest to smallest", "");
        OrderedChoice question = new OrderedChoice(1, content, new ClassicMode(), choices, new int[] {4, 2, 3, 1});

        Player player1 = new Player("Lucas", new Score(7));

        // Act
        ArrayList<Choice> chosenAnswers = player1.setAnswers(question, "2,4,3,1");

        question.assignScore(player1, chosenAnswers);

        // Assert
        assertEquals(7, player1.getScore());
    }

    @Test
    // Una pregunta Group Choice recibe una respuesta correcta y asigna un punto
    public void test11AClassicGroupChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredCorrectlyAndAssignsTheScore() {
        // Arrange
        ArrayList<GroupChoice.Group> groups = new ArrayList<>();
        groups.add(GroupChoice.createGroup("", new int[] {2, 3, 5}));
        groups.add(GroupChoice.createGroup("", new int[] {1, 2, 6}));

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Kawhi Leonard", "Correcta", 1));
        choices.add(new Choice("Lebron James", "Correcta", 2));
        choices.add(new Choice("Tristan Thompson", "Correcta", 3));
        choices.add(new Choice("Emanuel Ginobili", "Correcta", 4));
        choices.add(new Choice("Kyrie Irving", "Correcta", 5));
        choices.add(new Choice("Tony Parker", "Correcta", 6));

        Content content = new Content("Sports", "Match the players which played together", "");
        GroupChoice question = new GroupChoice(1, content, new ClassicMode(), choices);
        question.addGroups(groups);

        Player player1 = new Player("Manuel", new Score(2));

        // Act
        ArrayList<Choice> chosenAnswersGroupA = player1.setAnswers(question, "2,3,5");
        ArrayList<Choice> chosenAnswersGroupB = player1.setAnswers(question, "1,2,6");

        ArrayList<ArrayList<Choice>> groupChosenAnswers = new ArrayList<>();
        groupChosenAnswers.add(chosenAnswersGroupA);
        groupChosenAnswers.add(chosenAnswersGroupB);

        int score = question.calculateTotalScore(groupChosenAnswers);

        player1.addToScore(score);

        // Assert
        assertEquals(3, player1.getScore());
    }

        @Test
        // Una pregunta Group Choice recibe una respuesta incorrecta y no asigna puntos
        public void test12AGroupChoiceQuestionReceivesAListOfAnswersOfAPlayerThatAnsweredIncorrectlyAndAssignsTheScore() {
            // Arrange
            ArrayList<GroupChoice.Group> groups = new ArrayList<>();
            groups.add(GroupChoice.createGroup("", new int[] {2, 3, 5}));
            groups.add(GroupChoice.createGroup("", new int[] {1, 2, 6}));

            ArrayList<Choice> choices = new ArrayList<>();
            choices.add(new Choice("Kawhi Leonard", "incorrecta", 1));
            choices.add(new Choice("Lebron James", "Correcta", 2));
            choices.add(new Choice("Tristan Thompson", "Correcta", 3));
            choices.add(new Choice("Emanuel Ginobili", "Incorrecta", 4));
            choices.add(new Choice("Kyrie Irving", "Correcta", 5));
            choices.add(new Choice("Tony Parker", "Incorrecta", 6));

            Content content = new Content("Sports", "Match the players which played together", "");
            GroupChoice question = new GroupChoice(1, content, new ClassicMode(), choices);
            question.addGroups(groups);

            Player player2 = new Player("Manuel", new Score(0));

            // Act
            ArrayList<Choice> chosenAnswersGroupA = player2.setAnswers(question, "1,3,6");
            ArrayList<Choice> chosenAnswersGroupB = player2.setAnswers(question, "2,4,5");

            ArrayList<ArrayList<Choice>> groupChosenAnswers = new ArrayList<>();
            groupChosenAnswers.add(chosenAnswersGroupA);
            groupChosenAnswers.add(chosenAnswersGroupB);

            int score = question.calculateTotalScore(groupChosenAnswers);
            player2.addToScore(score);

            assertEquals(0, player2.getScore());
        }
}