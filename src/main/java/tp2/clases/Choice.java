package tp2.clases;

public class Choice {
    public String content;
    public Correction correction;
    private final int id;
    private Player player;

    public Choice(String content, String correction, int id) {
        this.content = content;
        this.correction = Correction.assignCorrection(correction);
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

    public int getId() { return id;}

    public void assignPlayer(Player aPlayer) {
        player = aPlayer;
    }
}

