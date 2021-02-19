package engine;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Response {

    SUCCESS(true, "Congratulations, you're right!"),
    FAIL(false, "Wrong answer! Please, try again.");

    public boolean success;
    public String feedback;

    Response(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    static Response get(boolean answerIsCorrect) {
        return answerIsCorrect ? SUCCESS : FAIL;
    }
}
