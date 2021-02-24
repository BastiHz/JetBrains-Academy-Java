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

@RestController
@Validated
public class QuizController {

    @Autowired
    QuizRepository quizRepository;
    private static final ResponseStatusException idNotFoundException =
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found.");

    @PostMapping(path = "/api/quizzes", consumes = "application/json")
    Quiz addQuiz(@RequestBody @Valid Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @GetMapping(path = "/api/quizzes/{id}")
    Quiz getQuiz(@PathVariable @Min(0) int id) {
        return quizRepository.findById(id)
            .orElseThrow(() -> idNotFoundException);
    }

    @GetMapping(path = "/api/quizzes")
    List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    Response checkAnswer(@PathVariable @Min(0) int id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id)
            .orElseThrow(() -> idNotFoundException);
        return Response.get(Arrays.equals(quiz.getAnswer(), answer.getAnswer()));
    }
}
