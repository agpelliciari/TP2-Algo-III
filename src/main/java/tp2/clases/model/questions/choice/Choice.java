package tp2.clases.model.questions.choice;

import tp2.clases.model.questions.choice.corrections.types.CorrectionFactory;
import tp2.clases.model.questions.choice.corrections.types.Correction;

public class Choice {

    public String content;
    public Correction correction;
    private int id;

    public Choice(int id) {
        this.id = id;
    }

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

