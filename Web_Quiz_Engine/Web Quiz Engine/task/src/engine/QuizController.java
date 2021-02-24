package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Validated
@RequestMapping(path = "/api/quizzes")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping(consumes = "application/json")
    Quiz addQuiz(@RequestBody @Valid Quiz quiz) {
        // TODO: Get currently logged in user to assign a user to the quiz.
        return quizRepository.save(quiz);
    }

    @GetMapping()
    List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    Quiz getQuiz(@PathVariable @Min(0) int id) {
        return quizRepository.findById(id).orElseThrow();
    }

    @PostMapping(path = "/{id}/solve")
    Response checkAnswer(@PathVariable @Min(0) int id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        return Response.get(Arrays.equals(quiz.getAnswer(), answer.getAnswer()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Id not found.")
    void handleWrongIdException() {}
}
