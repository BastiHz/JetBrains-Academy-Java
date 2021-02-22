package engine;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {

    QuizStorage quizStorage = new QuizStorage();

    @PostMapping(path = "/api/quizzes", consumes = "application/json")
    Quiz addQuiz(@RequestBody Quiz quiz) {
        quizStorage.addQuiz(quiz);
        return quiz;
    }

    @GetMapping(path = "/api/quizzes/{id}")
    Quiz getQuiz(@PathVariable int id) {
        return quizStorage.getById(id);
    }

    @GetMapping(path = "/api/quizzes")
    List<Quiz> getAllQuizzes() {
        return quizStorage.getAll();
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    Response checkAnswer(@PathVariable int id, @RequestParam int answer) {
        return quizStorage.checkAnswer(id, answer);
    }
}
