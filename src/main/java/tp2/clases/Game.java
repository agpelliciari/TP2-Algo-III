package tp2.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tp2.clases.exceptions.InvalidNumberOfPlayersException;
import tp2.clases.exceptions.UserNameAlreadyExistsException;

class Game {
    private int maxScore;
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    public Game(ArrayList<Question> questions, int maxScore) {
        this.maxScore = maxScore;
    }

    public Game(int maxScore) {
        this.maxScore = maxScore;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public int getMaxScore() {
        return maxScore;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<Player> selectPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 2)
            throw new InvalidNumberOfPlayersException();

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            String userName = getUserName();
            Player player = new Player(userName, 0);
            registerUser(players, player);
        }

        return players;
    }

    public ArrayList<Player> selectPlayers(int numberOfPlayers, List<String> users) {
        if (numberOfPlayers < 2)
            throw new InvalidNumberOfPlayersException();

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(users.get(i), 0);
            registerUser(players, player);
        }

        return players;
    }

    public String getUserName() {
        System.out.print("Name: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        scanner.close();

        return userName;
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
}