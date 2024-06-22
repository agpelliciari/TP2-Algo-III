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

    public static boolean intToBool(int num) {
        return num != 0;
    }

    public Game(ArrayList<Player> players, ArrayList<Question> questions, int maxScore) {
        this.players = players;
        this.questions = questions;
    }

    public Game(ArrayList<Question> questions, int maxScore) {
        this.questions = questions;
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

    public boolean checkIfAllAreCorrectAnswers(int[] playersCorrectAnswers) {
        for (int playerCorrectAnswers : playersCorrectAnswers)
            if (!intToBool(playerCorrectAnswers))
                return false;
        return true;
    }

    public void start(ArrayList<String[]> chosenChoices, ArrayList<boolean[]> chosenExclusivities) {
        for (int i = 0; i < questions.size(); i++) {
            int numberOfExclusivities = 0;
            for (boolean[] bool : chosenExclusivities) {
                if (bool[i]) numberOfExclusivities++;
            }

            int[] playersCorrectAnswers = new int[players.size()];
            ArrayList<Player> playersWhoAnsweredCorrectly = new ArrayList<>();

            for (int j = 0; j < players.size(); j++) {
                players.get(j).assignExclusivity(chosenExclusivities.get(j)[i]);
                playersCorrectAnswers[j] = questions.get(i).getNumberOfCorrectAnswers(players.get(j).setAnswers(questions.get(i), chosenChoices.get(j)[i]));
                if (playersCorrectAnswers[j] > 0) {
                    playersWhoAnsweredCorrectly.add(players.get(j));
                    players.get(j).setNumberOfCorrectAnswers(playersCorrectAnswers[j]);
                }
            }

            if (!(questions.get(i).getMode() instanceof PenaltyMode)) {
                if (this.checkIfOnlyOneCorrectAnswer(playersCorrectAnswers)) {
                    Player onlyCorrectPlayer = playersWhoAnsweredCorrectly.get(0);
                    if (onlyCorrectPlayer.getExclusivity().isActive()) {
                        onlyCorrectPlayer.assignScore(new Correct(), onlyCorrectPlayer.getNumberOfCorrectAnswers() * onlyCorrectPlayer.getExclusivity().getMultiplier() * numberOfExclusivities);
                    }
                } else if (this.checkIfAllAreCorrectAnswers(playersCorrectAnswers)) {
                }
            } else {
                for (Player playerWhoAnsweredCorrectly : playersWhoAnsweredCorrectly) {
                    playerWhoAnsweredCorrectly.assignScore(new Correct(), playerWhoAnsweredCorrectly.getNumberOfCorrectAnswers());
                }
            }
        }
    }
}