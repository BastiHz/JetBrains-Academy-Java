package animals;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class GuessTheAnimal {
    private String animal;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    // TODO: Load these lists from files.
    private final List<String> yesAnswers = List.of("y", "yes", "yeah", "yep", "sure", "right",
            "affirmative", "correct", "indeed", "you bet", "exactly", "you said it");
    private final List<String> noAnswers = List.of("n", "no", "no way", "nah", "nope", "negative",
            "i don't think so", "yeah no");
    private final List<String> unclearClarification = List.of(
            "I'm not sure I caught you. Was it yes or no?",
            "Funny, I still don't understand. Is it yes or no?",
            "Oh, it's too complicated for me. Just tell me yes or no.",
            "Could you please simply say yes or no?",
            "Oh no, don't try to confuse me. Please say yes or no."
    );
    private final List<String> goodbeyes = List.of("Bye!", "See you soon!", "Have a nice day!");

    void run() {
        greet();
        getAnimal();
        clarify();
        bye();
    }

    private void greet() {
        int currentHour = LocalTime.now().getHour();
        String greeting;
        if (currentHour < 5) {
            greeting = "Hi, early bird!";
        } else if (currentHour < 12) {
            greeting = "Good morning.";
        } else if (currentHour < 18) {
            greeting = "Good afternoon.";
        } else {
            greeting = "Good evening.";
        }
        System.out.println(greeting);
        System.out.println();
    }

    private void getAnimal() {
        System.out.println("Enter an animal:");
        animal = scanner.nextLine().strip().toLowerCase();
        if (!animal.matches("(a|an)\\s+.*")) {
            String article = animal.matches("[aeiou].*") ? "an " : "a ";
            animal = article + animal;
        }
    }

    private void clarify() {
        System.out.printf("Is it %s?%n", animal);
        String normalizedAnswer;
        while (true) {
            String answer = scanner.nextLine()
                .trim()
                .toLowerCase()
                .replaceAll("[.!]$", "");
            if (yesAnswers.contains(answer)) {
                normalizedAnswer = "Yes";
                break;
            } else if (noAnswers.contains(answer)) {
                normalizedAnswer = "No";
                break;
            } else {
                int i = random.nextInt(unclearClarification.size());
                System.out.println(unclearClarification.get(i));
            }
        }
        System.out.println("You answered: " + normalizedAnswer);
        System.out.println();
    }

    private void bye() {
        int i = random.nextInt(goodbeyes.size());
        System.out.println(goodbeyes.get(i));
    }
}
