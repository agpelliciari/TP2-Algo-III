package tp2.clases;

public class Limit {
    private int pointsLimit;
    private int questionLimit;

    public Limit() {
        this.questionLimit = 0;
        this.pointsLimit = 0;
    }

    public void setPointsLimit(int limit) {
        this.pointsLimit = limit;
    }

    public void setQuestionLimit(int limit) {
        this.questionLimit = limit;
    }

    public boolean questionLimitReached(int questionCount) {
        return questionLimit == questionCount;
    }

    public boolean pointsLimitReached(int maxScore) {
        return pointsLimit == maxScore;
    }
}
