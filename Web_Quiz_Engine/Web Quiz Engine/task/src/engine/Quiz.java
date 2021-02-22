package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {

    private int id;
    private String title;
    private String text;
    private String[] options;
    private int answer;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }

    // Using @JsonProperty for the setter enables setting it with @RequestBody.
    // This is necessary because of the @JsonIgnore above the getter.
    @JsonProperty
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    // Using @JsonIgnore for the getter avoids revealing the answer to the client.
    @JsonIgnore
    public int getAnswer() {
        return answer;
    }
}
