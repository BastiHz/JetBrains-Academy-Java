package animals;

import java.util.*;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class GuessTheAnimal {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final Pattern SECOND_SPACE = Pattern.compile("(?<=^\\w{1,3}\\s\\w{1,3})\\s");
    private final Pattern REST_OF_FACT = Pattern.compile("\\w+.*");
    private final Pattern ARTICLE_ANIMAL = Pattern.compile("^(a|an)\\s+.+");
    private final Pattern SPACE_AFTER_ARTICLE = Pattern.compile("(?<=^(a|an))\\s");
    private final Pattern START_VOWEL = Pattern.compile("^[aeiou].*");
    private final Pattern DOT_BANG_END = Pattern.compile("[.!]$");
    private final Pattern YES_ANSWER = Pattern.compile(
        "(y|yes|yeah|yep|sure|right|affirmative|correct|indeed|you bet|exactly|you said it)[.!]?",
        Pattern.CASE_INSENSITIVE
    );
    private final Pattern NO_ANSWER = Pattern.compile(
        "(n|no|no way|nah|nope|negative|i don't think so|yeah no)[.!]?",
        Pattern.CASE_INSENSITIVE
    );

    private final Map<String, String[]> itCanHasIs = new HashMap<>();
    {
        itCanHasIs.put("it can", new String[] {"can", "can't", "Can it"});
        itCanHasIs.put("it has", new String[] {"has", "doesn't have", "Does it have"});
        itCanHasIs.put("it is", new String[] {"is", "isn't", "Is it"});
    }
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
    private final KnowledgeTree knowledge = new KnowledgeTree();

    void run() {
        greet();
        askFavoriteAnimal();
        System.out.println("Let's play a game!");
        do {
            System.out.println();
            System.out.println("You think of an animal, and I guess it.");
            System.out.println("Press enter when you're ready.");
            scanner.nextLine();
            play();
            System.out.println();
            System.out.println("Would you like to play again?");
        } while (getYesNo());
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

    private void askFavoriteAnimal() {
        System.out.println("I want to learn about animals.");
        System.out.println("Which animal do you like most?");
        knowledge.setRoot(getAnimal());
        System.out.println("Wonderful! I've learned so much about animals");
    }

    private void play() {
        Node currentNode = knowledge.getRoot();
        while (true) {
            if (currentNode instanceof Fact) {
                Fact fact = (Fact) currentNode;
                System.out.println(fact.question);
                currentNode = getYesNo() ? currentNode.trueChild : currentNode.falseChild;
            } else {
                Animal animal = (Animal) currentNode;
                System.out.printf("Is it %s %s?%n", animal.article, animal.name);
                if (getYesNo()) {
                    System.out.println("Awesome! That was fun.");
                } else {
                    System.out.println("I give up. What animal do you have in mind?");
                    createNewFact(animal, getAnimal());
                }
                break;
            }
        }
    }

    private void bye() {
        printRandomMessage(GOODBYE);
    }

    private Animal getAnimal() {
        String name = scanner.nextLine().strip().toLowerCase();
        String article;
        if (ARTICLE_ANIMAL.matcher(name).matches()) {
            String[] articleAndAnimal = SPACE_AFTER_ARTICLE.split(name);
            article = articleAndAnimal[0];
            name = articleAndAnimal[1];
        } else {
            article = START_VOWEL.matcher(name).matches() ? "an" : "a";
        }
        return new Animal(article, name);
    }

    private void createNewFact(Animal oldAnimal, Animal newAnimal) {
        String statement;
        String factStart;
        String factRest;
        while (true) {
            System.out.printf(
                "Specify a fact that distinguishes %s %s from %s %s.%n",
                oldAnimal.article, oldAnimal.name, newAnimal.article, newAnimal.name
            );
            System.out.println("The sentence should be of the format: 'It can/has/is ...'.");
            statement = scanner.nextLine().trim().toLowerCase();
            statement = DOT_BANG_END.matcher(statement).replaceAll("");
            String[] statementParts = SECOND_SPACE.split(statement);
            if (statementParts.length == 2) {
                factStart = statementParts[0];
                if (itCanHasIs.containsKey(factStart)) {
                    factRest = statementParts[1];
                    if (REST_OF_FACT.matcher(factRest).matches()) {
                        break;
                    }
                }
            }
            System.out.println("The examples of a statement:");
            System.out.println(" - It can fly.");
            System.out.println(" - It has horns.");
            System.out.println(" - It is a mammal.");
        }

        System.out.printf("Is the statement correct for %s %s?%n",
            newAnimal.article, newAnimal.name
        );
        boolean factIsTrueForNewAnimal = getYesNo();

        System.out.println("I learned the following facts about animals:");
        String[] sentenceParts = itCanHasIs.get(factStart);
        if (factIsTrueForNewAnimal) {
            System.out.printf(" - The %s %s %s.%n", oldAnimal.name, sentenceParts[1], factRest);
            System.out.printf(" - The %s %s %s.%n", newAnimal.name, sentenceParts[0], factRest);
        } else {
            System.out.printf(" - The %s %s %s.%n", oldAnimal.name, sentenceParts[0], factRest);
            System.out.printf(" - The %s %s %s.%n", newAnimal.name, sentenceParts[1], factRest);
        }

        System.out.println("I can distinguish these animals by asking the question:");
        String question = String.format("%s %s?", sentenceParts[2], factRest);
        System.out.println(" - " + question);

        statement = Character.toUpperCase(statement.charAt(0)) + statement.substring(1) + ".";
        Fact fact = new Fact(statement, question);
        knowledge.insert(fact, oldAnimal, newAnimal, factIsTrueForNewAnimal);

        System.out.println("Nice! I've learned so much about animals!");
    }

    private boolean getYesNo() {
        while (true) {
            String answer = scanner.nextLine().trim();
            if (YES_ANSWER.matcher(answer).matches()) {
                return true;
            }
            if (NO_ANSWER.matcher(answer).matches()) {
                return false;
            }
            printRandomMessage(UNCLEAR);
        }
    }

    private void printRandomMessage(String[] strings) {
        System.out.println(strings[random.nextInt(strings.length)]);
    }
}
