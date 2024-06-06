package tp2.clases;

import java.util.ArrayList;

class Panel {
    public void show(Question aQuestion) {
//        aQuestion.task();
//        aQuestion.options();

    public void showWelcomeMessage() {
        System.out.print("Bienvenido al juego de preguntas!\n");
    }

    public void showStartMenu() {
        System.out.print("Ingrese la cantidad de jugadores:");
    }

    public void showPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName());
            System.out.println(player.getScore());
        }
    }

    public void showPlayerName(Player player) {
        System.out.println(player.getName());
    }

    public String getLetters(int index) {
        StringBuilder letters = new StringBuilder();
        while (index >= 0) {
            letters.insert(0, (char) ('a' + index % 26));
            index = index / 26 - 1;
        }
        return letters.toString();
    }

    public void showQuestion(Question question) {
        System.out.println(question.getQuestionText());

        int i = 0;
        for (Option option : question.options) {
            System.out.println(getLetters(i) + ". " + option.getOptionText());
            i++;
        }
    }
}
