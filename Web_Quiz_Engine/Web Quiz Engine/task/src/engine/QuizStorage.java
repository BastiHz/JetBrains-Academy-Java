package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class QuizStorage {

    private final List<Quiz> quizzes = new ArrayList<>();
    private final ResponseStatusException idNotFoundException = new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Quiz not found. Maybe the id is wrong?"
    );

    void addQuiz(Quiz quiz) {
        quiz.setId(quizzes.size());
        quizzes.add(quiz);
    }

    Quiz getById(int id) {
        try {
            return quizzes.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw idNotFoundException;
        }
    }

    List<Quiz> getAll() {
        return quizzes;
    }

    Response checkAnswer(int id, int answer) {
        try {
            return Response.get(answer == quizzes.get(id).getAnswer());
        } catch (IndexOutOfBoundsException e) {
            throw idNotFoundException;
        }
    }
}
