package animals;

import java.util.*;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class GuessTheAnimal {
    // TODO: Cleanup: Make a Pattern when a regex is used more than once!

    private final String[] animals = new String[2];
    private final String[] articles = new String[2];
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final Pattern SECOND_SPACE = Pattern.compile("(?<=^\\w{1,3}\\s\\w{1,3})\\s");
    private final Pattern REST_OF_FACT = Pattern.compile("\\w+.*");
    private final Map<String, String[]> itCanHasIs = new HashMap<>();
    {
        itCanHasIs.put("it can", new String[] {"can", "can't", "Can it"});
        itCanHasIs.put("it has", new String[] {"has", "doesn't have", "Does it have"});
        itCanHasIs.put("it is", new String[] {"is", "isn't", "Is it"});
    }
    private String startOfFact;
    private String restOfTheFact;
    private int factIsTrueForIndex;

    // I would like to load these lists from files but then the tests break.
    private final Pattern YES_ANSWER = Pattern.compile(
        "(y|yes|yeah|yep|sure|right|affirmative|correct|indeed|you bet|exactly|you said it)[.!]?",
        Pattern.CASE_INSENSITIVE
    );
    private final Pattern NO_ANSWER = Pattern.compile(
        "(n|no|no way|nah|nope|negative|i don't think so|yeah no)[.!]?",
        Pattern.CASE_INSENSITIVE
    );
    private final String[] UNCLEAR = {
        "I'm not sure I caught you. Was it yes or no?",
        "Funny, I still don't understand. Is it yes or no?",
        "Oh, it's too complicated for me. Just tell me yes or no.",
        "Could you please simply say yes or no?",
        "Oh no, don't try to confuse me. Please say yes or no."
    };
    private final String[] GOODBYE = {
        "Bye!", "See you soon!", "Have a nice day!",
        "Talk to you later!", "Thank you and goodbye!", "See you later!"
    };

    void run() {
        greet();
        getAnimals();
        askFact();
        stateLearned();
        bye();
    }

    private void greet() {
        int currentHour = LocalTime.now().getHour();
        if (currentHour < 5) {
            System.out.println("Hi, early bird!");
        } else if (currentHour < 12) {
            System.out.println("Good morning.");
        } else if (currentHour < 18) {
            System.out.println("Good afternoon.");
        } else {
            System.out.println("Good evening.");
        }
        System.out.println();
    }

    private void getAnimals() {
        String[] ordinalWords = new String[] {"first", "second"};
        for (int i = 0; i < 2; i++) {
            System.out.printf("Enter the %s animal:%n", ordinalWords[i]);
            String input = scanner.nextLine().strip().toLowerCase();
            if (input.matches("^(a|an)\\s+.+")) {
                String[] articleAndAnimal = input.split("(?<=^(a|an))\\s");
                articles[i] = articleAndAnimal[0];
                animals[i] = articleAndAnimal[1];
            } else {
                articles[i] = input.matches("^[aeiou].*") ? "an" : "a";
                animals[i] = input;
            }
        }
    }

    private void askFact() {
        String fact;
        while (true) {
            System.out.printf(
                "Specify a fact that distinguishes %s %s from %s %s.%n",
                articles[0], animals[0], articles[1], animals[1]
            );
            System.out.println("The sentence should be of the format: 'It can/has/is ...'.");
            fact = scanner.nextLine().trim().toLowerCase();
            String[] factParts = SECOND_SPACE.split(fact);
            if (factParts.length == 2) {
                startOfFact = factParts[0];
                if (itCanHasIs.containsKey(startOfFact)) {
                    restOfTheFact = factParts[1].replaceAll("[.!]$", "");
                    if (REST_OF_FACT.matcher(restOfTheFact).matches()) {
                        break;
                    }
                }
            }
            System.out.println("The examples of a statement:");
            System.out.println(" - It can fly.");
            System.out.println(" - It has horns.");
            System.out.println(" - It is a mammal.");
        }
        System.out.printf("Is it correct for %s %s?%n", articles[1], animals[1]);
        factIsTrueForIndex = getYesNo().equals("Yes") ? 1 : 0;
    }

    private void stateLearned() {
        System.out.println("I learned the following facts about animals:");
        String positiveCanHasIs = itCanHasIs.get(startOfFact)[0];
        String negativeCanHasIs = itCanHasIs.get(startOfFact)[1];
        for (int i = 0; i < 2; i++) {
            String canHasIs = i == factIsTrueForIndex ? positiveCanHasIs : negativeCanHasIs;
            String fact = String.format(" - The %s %s %s.", animals[i], canHasIs, restOfTheFact);
            System.out.println(fact);
        }
        System.out.println("I can distinguish these animals by asking the question:");
        System.out.printf(" - %s %s?%n", itCanHasIs.get(startOfFact)[2], restOfTheFact);
    }

    private String getYesNo() {
        while (true) {
            String answer = scanner.nextLine().trim();
            if (YES_ANSWER.matcher(answer).matches()) {
                return "Yes";
            }
            if (NO_ANSWER.matcher(answer).matches()) {
                return "No";
            }
            System.out.println(UNCLEAR[random.nextInt(UNCLEAR.length)]);
        }
    }

    private void bye() {
        System.out.println(GOODBYE[random.nextInt(GOODBYE.length)]);
    }
}
