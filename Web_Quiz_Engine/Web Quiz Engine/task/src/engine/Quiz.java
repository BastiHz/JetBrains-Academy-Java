package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Quiz {

    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotNull
    @Size(min = 2)
    private String[] options;
    private int[] answer = new int[0];

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
    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    @JsonIgnore  // Avoid revealing the answer to the client.
    public int[] getAnswer() {
        return answer;
    }
}
