package engine;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Validated
public class QuizController {

    private final List<Quiz> quizzes = new ArrayList<>();
    private static final String ID_NOT_FOUND_MESSAGE = "Quiz not found.";

    @PostMapping(path = "/api/quizzes", consumes = "application/json")
    Quiz addQuiz(@RequestBody @Valid Quiz quiz) {
        quiz.setId(quizzes.size());
        quizzes.add(quiz);
        return quiz;
    }

    @GetMapping(path = "/api/quizzes/{id}")
    Quiz getQuiz(@PathVariable @Min(0) int id) {
        return quizzes.get(id);
    }

    @GetMapping(path = "/api/quizzes")
    List<Quiz> getAllQuizzes() {
        return quizzes;
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    Response checkAnswer(@PathVariable @Min(0) int id, @RequestBody Answer answer) {
        Quiz quiz = quizzes.get(id);
        return Response.get(Arrays.equals(quiz.getAnswer(), answer.getAnswer()));
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ID_NOT_FOUND_MESSAGE)
    void handleIndexOutOfBoundsException() {
    }
}
