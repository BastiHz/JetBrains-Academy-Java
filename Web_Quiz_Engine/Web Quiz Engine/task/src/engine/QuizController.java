package engine;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {

    Question question = new Question(
        "The Java Logo",
        "What is depicted on the Java logo?",
        new String[] {"Robot", "Tea leaf", "Cup of coffee", "Bug"}
    );

    @GetMapping(path = "/api/quiz")
    Question getQuestion() {
        return question;
    }

    @PostMapping(path = "/api/quiz")
    Response checkAnswer(@RequestParam int answer) {
        return Response.get(answer == 2);
    }
}
