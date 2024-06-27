package tp2.clases.questions;

import java.util.Objects;

public class Content {

    private final String theme;
    private final String prompt;
    private final String answerText;

    public Content(String theme, String prompt, String answerText) {
        this.theme = theme;
        this.prompt = prompt;
        this.answerText = answerText;
    }

    public String getTheme() {
        return theme;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean hasTheme(String searchedTheme) {
        return Objects.equals(theme, searchedTheme);
    }
}
