package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotNull
    @Size(min = 2)
    private String[] options;

    private int[] answer = new int[0];

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
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
