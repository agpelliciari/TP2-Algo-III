package tp2.clases;

public class Choice {

    public String content;
    public Correction correction;
    private final int id;

    public Choice(String content, String correction, int id) {
        this.content = content;
        this.correction = CorrectionFactory.assignCorrection(correction);
        this.id = id;
    }

    public Correction getCorrection() {
        return correction;
    }

    public String getContent() {
        return content;
    }

    public int getId() { return id;}
}

