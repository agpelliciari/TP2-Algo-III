package tp2.clases;

public class Content {
    private final String theme;
    private final String prompt;

    public Content(String theme, String prompt) {
        this.theme = theme;
        this.prompt = prompt;
    }

    public String getTheme() {
        return theme;
    }

    public boolean hasTheme(String searchedTheme) {
        return theme == searchedTheme;
    }
}
