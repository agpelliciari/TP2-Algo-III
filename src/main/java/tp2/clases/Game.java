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

    public void start(ArrayList<String[]> chosenChoices, ArrayList<boolean[]> chosenBooleans) {
        for (int i = 0; i < questions.size(); i++) {
            int[] playersCorrectAnswers = new int[players.size()];
            ArrayList<Player> playersWhoAnsweredCorrectly = new ArrayList<>();
            for (int j = 0; j < players.size(); j++) {
                players.get(j).assignExclusivity(chosenBooleans.get(j)[i]);
                if (intToBool(playersCorrectAnswers[j] = questions.get(i).getNumberOfCorrectAnswers(players.get(j).setAnswers(questions.get(i), chosenChoices.get(j)[i])))) {
                    playersWhoAnsweredCorrectly.add(players.get(j));
                    players.get(j).setNumberOfCorrectAnswers(playersCorrectAnswers[j]);
                }
            }
            if (this.checkIfOnlyOneCorrectAnswer(playersCorrectAnswers) && !(questions.get(i).getMode() instanceof PenaltyMode)) {
                assert playersWhoAnsweredCorrectly.get(0) != null;
                if (playersWhoAnsweredCorrectly.get(0).getExclusivity().getBool()) {
                    playersWhoAnsweredCorrectly.get(0).assignScore(new Correct(), playersWhoAnsweredCorrectly.get(0).getNumberOfCorrectAnswers() * playersWhoAnsweredCorrectly.get(0).getExclusivity().getMultiplier());
                }
            } else {
                for (Player playerWhoAnsweredCorrectly : playersWhoAnsweredCorrectly) {
                    playerWhoAnsweredCorrectly.assignScore(new Correct(), playerWhoAnsweredCorrectly.getNumberOfCorrectAnswers());
                }
            }
        }
    }
}