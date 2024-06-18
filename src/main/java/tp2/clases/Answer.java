package tp2.clases;

public class Answer {
    public String content;
    public Correction correction;
    public int score;
    private char id;
    private Player player;

    public Answer(String content, String correction, int score, char id) {
        this.content = content;
        this.correction = Correction.assignCorrection(correction);
        this.score = score;
        this.id = id;
    }

//    public void assignScore(Player player) {
//        correccion.asignarPuntaje(unJugador);
//        player.setScore(player.getScore() + score);
//    }

    public Correction getCorrection() {
        return correction;
    }

    public String getContent() {
        return content;
    }

    public int getScore() {
        return score;
    }

    public char getId() { return id;}

    public boolean equals(char searchedId){
        return getId() == searchedId;
    }

    public boolean equals(Answer otherAnswer){
        return id == otherAnswer.getId();
    }

    public void assignPlayer(Player aPlayer) {
        player = aPlayer;
    }
}

