package tp2.clases;

import tp2.clases.Question;
import tp2.clases.Answer;
import tp2.clases.Correction;
import tp2.clases.Score;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Player {
    private String name;
    private Score score;
    private List<Multiplicator> multiplicators;

    public Player(String name, int initialScore) {
        this.name = name;
        this.score = new Score(initialScore);
        this.multiplicators = new ArrayList<>();
        multiplicators.add(new Multiplicator(2));
        multiplicators.add(new Multiplicator(3));
    }

    public Player(Score score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score.getScore();
    }

    public ArrayList<Answer> answer(Question question) {

        Scanner scanner = new Scanner(System.in);
        String chosenOption = scanner.nextLine();

        return question.choiceOption(chosenOption);
    }

    public ArrayList<Answer> answer(Question question, String chosenOption) {

        return question.choiceOption(question.createAnswers(chosenOption), this);
    }

    public void assignScore(Correction correction, int modification) {

        Multiplicator multiplicator = getActiveMultiplicator();
        if(multiplicator != null){
            int factor = multiplicator.getFactor();
            correction.assignScore(score, modification*factor);
            multiplicator.deactivate();
        }
        else{
            correction.assignScore(score, modification);
        }


    }

    /*public void assignScore(Incorrect correction, int wrongPoints) {
        score.subtractScore(wrongPoints);
    }*/

    public boolean equals(Player aPlayer) {
        return name == aPlayer.getName();
    }

    public void useMultiplicator(int factor) {
        Multiplicator multiplicator = getMultiplicator(factor);
        if (!multiplicator.isActive()) {
            multiplicator.activate();
        }
    }

    public Multiplicator getMultiplicator(int factor) {
        for (Multiplicator multiplicator : multiplicators) {
            if (multiplicator.getFactor() == factor){
                return multiplicator;
            }
        }
        return null;
    }

    public Multiplicator getActiveMultiplicator() {
        for (Multiplicator multiplicator : multiplicators) {
            if (multiplicator.isActive()) {
                return multiplicator;
            }
        }
        return null;
    }
}
