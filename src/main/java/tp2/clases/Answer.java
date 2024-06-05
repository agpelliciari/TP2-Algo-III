package tp2.clases;

public class Answer {
    public String content;
    public Correction correction;
    public int score;

    public Answer(String content, String correction, int score) {
        this.content = content;
        this.correction = Correction.assignCorrection(correction);
        this.score = score;
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
}

