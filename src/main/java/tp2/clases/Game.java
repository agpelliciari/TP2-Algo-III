package tp2.clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import tp2.clases.exceptions.InvalidNumberOfPlayersException;
import tp2.clases.exceptions.UserNameAlreadyExistsException;

class Game {
    private int maxScore;
    private ArrayList<Question> questions;

    public Game(ArrayList<Question> questions, int maxScore) {
        this.maxScore = maxScore;
        this.questions = questions;
    }

    public void start(int numberOfPlayers) {
        ArrayList<Player> players = selectPlayers(numberOfPlayers);

        // while(score mas alto != maxScore)
        for (Question question : questions) {

            HashMap<Player, ArrayList<Choice>> choices = new HashMap<>();

            for (Player player : players) {
                ArrayList<Choice> playerAnswers = player.Choice(question);
                choices.put(player, playerAnswers);
            }

            question.assignScore(choices);
        }
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
}