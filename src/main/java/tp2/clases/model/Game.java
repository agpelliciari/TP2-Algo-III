package tp2.clases.model;

import java.util.*;

import tp2.clases.model.player.Player;
import tp2.clases.model.player.powers.Power;
import tp2.clases.model.player.score.Score;
import tp2.clases.model.questions.choice.corrections.types.Correct;
import tp2.clases.exceptions.InvalidNumberOfPlayersException;
import tp2.clases.exceptions.UserNameAlreadyExistsException;
import tp2.clases.model.questions.types.Question;

public class Game {

    int exclusivityCount;
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    private int[] playersScoreWithoutExclusivity;
    int numberOfPlayers = 0;
    Limit limit;
    ArrayList<Integer> selectedQuestionIndexes;
    int questionCount;

    public static boolean intToBool(int num) {
        return num != 0;
    }

    public Game() {}

    public Game(ArrayList<Player> players, ArrayList<Question> questions) {
        this.players = players;
        this.questions = questions;
    }

    public Game(ArrayList<Question> questions) {
        this.questions = questions;
        this.limit = new Limit();
        this.numberOfPlayers = 0;
        this.questionCount = 0;
        this.exclusivityCount = 0;
        this.selectedQuestionIndexes = new ArrayList<>();
    }

    public void setExclusivityCount(int exclusivityCount) {
        this.exclusivityCount = exclusivityCount;
    }

    public void addPlayer(Player player) {
        players.add(player);
        numberOfPlayers++;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> selectPlayers(int numberOfPlayers, ArrayList<String> playerNames) {
        if (numberOfPlayers < 2)
            throw new InvalidNumberOfPlayersException();

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(playerNames.get(i), new Score(0));
            registerUser(players, player);
        }
        return players;
    }

    public void registerUser(ArrayList<Player> players, Player aPlayer) {
        for (Player player : players)
            if (player.equals(aPlayer))
                throw new UserNameAlreadyExistsException();

        players.add(aPlayer);
    }

    public boolean checkIfOnlyOneCorrectAnswer(int[] playersCorrectAnswers) {
        int correctCount = 0;

        for (int playerCorrectAnswers : playersCorrectAnswers) {
            if (playerCorrectAnswers > 0) {
                correctCount++;
            }
            if (correctCount > 1) {
                return false;
            }
        }
        return correctCount == 1;
    }

    public boolean checkIfAllAreCorrectAnswers(int[] playersCorrectAnswers) {

        for (int playerCorrectAnswers : playersCorrectAnswers) {
            if (!intToBool(playerCorrectAnswers)) {
                return false;
            }
        }
        return true;
    }

    public void setNumberOfPlayers(int numberOfPlayersInput) {
        this.numberOfPlayers = numberOfPlayersInput;
        this.playersScoreWithoutExclusivity = new int[numberOfPlayersInput];
    }

    public void setQuestionLimit(int questionLimitInput) {
        this.limit.setQuestionLimit(questionLimitInput);
    }

    public void setPointLimit(int pointLimitInput) {
        this.limit.setPointsLimit(pointLimitInput);
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public void registerUsers(ArrayList<String> playersNames) {
        for (String playerName : playersNames) {
            players.add(new Player(playerName, new Score(0)));
        }
    }

    public int getRandomQuestionIndex() {
        Random random = new Random();
        int numQuestions = questions.size();
        int randomIndex = random.nextInt(numQuestions);
        while (selectedQuestionIndexes.contains(randomIndex) || checkIfRepeatedTheme(randomIndex)) {
            randomIndex = random.nextInt(numQuestions);
        }
        selectedQuestionIndexes.add(randomIndex);
        questionCount++;

        return randomIndex;
    }

    public boolean checkIfRepeatedTheme(int index) {
        if (((selectedQuestionIndexes.size()) == questions.size() - 1) | (selectedQuestionIndexes.isEmpty())) {
            return false;
        }

        String newTheme = questions.get(index).getContent().getTheme();
        String currentTheme = getCurrentQuestion().getContent().getTheme();

        return newTheme.equals(currentTheme);
    }

    public Question getQuestion(int questionIndex) {
        return questions.get(questionIndex);
    }

    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public void setPlayersScoreWithoutExclusivity(int playerIndex, int score) {
        Player player = players.get(playerIndex);
        player.setPreviousScore(player.getScore());
        playersScoreWithoutExclusivity[playerIndex] = score;
    }

    public void registerUsedExclusivity(boolean isSelected) {
        if (isSelected)
            exclusivityCount++;
    }

    public void updatePlayersScoreWithoutExclusivity() {
        for (int i = 0; i < players.size(); i++)
            players.get(i).setScoreChange(players.get(i).addToScore(playersScoreWithoutExclusivity[i]));
    }

    public Question getCurrentQuestion() {
        int currentQuestionIndex;
        if (selectedQuestionIndexes.size() == 1) {
             currentQuestionIndex = selectedQuestionIndexes.get(0);
        }
        else {
            currentQuestionIndex = selectedQuestionIndexes.get(selectedQuestionIndexes.size() - 1);
        }
        return getQuestion(currentQuestionIndex);
    }

    public void updatePlayersScoreWithExclusivity() {
        for (int i = 0; i < players.size(); i++) {
            if (exclusivityCount > 0) {
                if (playersScoreWithoutExclusivity[i] > 0) {
                    if (checkIfOnlyOneCorrectAnswer(playersScoreWithoutExclusivity)) {
                        players.get(i).setScoreChange(players.get(i).addToScore(playersScoreWithoutExclusivity[i] * players.get(i).getExclusivity().getFactor() * exclusivityCount));
                    } else if (checkIfAllAreCorrectAnswers(playersScoreWithoutExclusivity)) {
                    } else {
                        players.get(i).setScoreChange(players.get(i).addToScore(playersScoreWithoutExclusivity[i] * players.get(i).getExclusivity().getFactor()));
                    }
                } else {
                    players.get(i).setScoreChange(players.get(i).addToScore(playersScoreWithoutExclusivity[i]));
                }
            } else {
                players.get(i).setScoreChange(players.get(i).addToScore(playersScoreWithoutExclusivity[i]));
            }
        }
    }

    public boolean isFinished() {
        return ((limit.questionLimitReached(questionCount)) | (limit.pointsLimitReached(getMaxScore())));
    }

    public int getMaxScore() {
        return players.stream().mapToInt(Player::getScore).max().orElse(-9999);
    }

    public void deactivatePowers() {
        for (Player player : players)
            player.disablePowers();
    }

    public void resetExclusivityCount() {
        exclusivityCount = 0;
    }

    public boolean powerWereUsed() {
        for (Player player : players)
            for(Power power : player.getPowers())
                if (power.isActive())
                    return true;
        return false;
    }

    public void assignExclusivity(int playerIndex, boolean exclusivitySelected) {
        players.get(playerIndex).assignExclusivity(exclusivitySelected);
    }
}