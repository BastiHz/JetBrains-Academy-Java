package engine;

public class Question {

    public final String title;
    public final String text;
    public final String[] options;

    Question(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
    }
}
