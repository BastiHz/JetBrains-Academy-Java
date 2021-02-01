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
    private final Map<String, String[]> itCanHasIs = Map.of(
        "it can", new String[] {"can", "can't", "Can it"},
        "it has", new String[] {"has", "doesn't have", "Does it have"},
        "it is", new String[] {"is", "isn't", "Is it"}
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
    private final KnowledgeTree knowledge;

    GuessTheAnimal(String mapperType) {
        knowledge = new KnowledgeTree(mapperType);
    }


    void run() {
        greet();
        initKnowledge();
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
        knowledge.save();
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

    private void initKnowledge() {
        knowledge.load();
        if (knowledge.root == null) {
            knowledge.root = askFavoriteAnimal();
        }
    }

    private TreeNode askFavoriteAnimal() {
        System.out.println("I want to learn about animals.");
        System.out.println("Which animal do you like most?");
        TreeNode animal = getAnimal();
        System.out.println("Wonderful! I've learned so much about animals");
        return animal;
    }

    private void play() {
        TreeNode currentTreeNode = knowledge.root;
        TreeNode parent = null;
        while (true) {
            if (currentTreeNode.isAnimal()) {
                System.out.printf("Is it %s?%n", currentTreeNode.data);
                if (getYesNo()) {
                    System.out.println("Awesome! That was fun.");
                } else {
                    System.out.println("I give up. What animal do you have in mind?");
                    createNewFact(currentTreeNode, parent);
                }
                break;
            } else {
                System.out.println(getQuestionFromFact(currentTreeNode));
                parent = currentTreeNode;
                currentTreeNode = getYesNo() ? currentTreeNode.yes : currentTreeNode.no;
            }
        }
    }

    private void bye() {
        printRandomMessage(GOODBYE);
    }

    private TreeNode getAnimal() {
        String name = scanner.nextLine().strip().toLowerCase();
        String article;
        if (ARTICLE_ANIMAL.matcher(name).matches()) {
            String[] articleAndAnimal = SPACE_AFTER_ARTICLE.split(name);
            article = articleAndAnimal[0];
            name = articleAndAnimal[1];
        } else {
            article = START_VOWEL.matcher(name).matches() ? "an" : "a";
        }
        return new TreeNode(article, name);
    }

    private void createNewFact(TreeNode oldAnimal, TreeNode parent) {
        TreeNode newAnimal = getAnimal();
        String factStart;
        String factRest;
        while (true) {
            System.out.printf("Specify a fact that distinguishes %s from %s.%n",
                oldAnimal.data, newAnimal.data
            );
            System.out.println("The sentence should be of the format: 'It can/has/is ...'.");
            String answer = scanner.nextLine().trim().toLowerCase();
            answer = DOT_BANG_END.matcher(answer).replaceAll("");
            String[] answerParts = SECOND_SPACE.split(answer);
            if (answerParts.length == 2) {
                factStart = answerParts[0];
                if (itCanHasIs.containsKey(factStart)) {
                    factRest = answerParts[1];
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

        System.out.printf("Is the statement correct for %s?%n", newAnimal.data);
        boolean factIsTrueForNewAnimal = getYesNo();

        System.out.println("I learned the following facts about animals:");
        String[] sentenceParts = itCanHasIs.get(factStart);
        String oldName = SPACE_AFTER_ARTICLE.split(oldAnimal.data)[1];
        String newName = SPACE_AFTER_ARTICLE.split(newAnimal.data)[1];
        if (factIsTrueForNewAnimal) {
            System.out.printf(" - The %s %s %s.%n", oldName, sentenceParts[1], factRest);
            System.out.printf(" - The %s %s %s.%n", newName, sentenceParts[0], factRest);
        } else {
            System.out.printf(" - The %s %s %s.%n", oldName, sentenceParts[0], factRest);
            System.out.printf(" - The %s %s %s.%n", newName, sentenceParts[1], factRest);
        }

        String statement = String.format("%s%s %s.",
            Character.toUpperCase(factStart.charAt(0)),
            factStart.substring(1),
            factRest
        );
        TreeNode fact = new TreeNode(statement);
        knowledge.insert(fact, parent, oldAnimal, newAnimal, factIsTrueForNewAnimal);

        System.out.println("I can distinguish these animals by asking the question:");
        System.out.println(" - " + getQuestionFromFact(fact));

        System.out.println("Nice! I've learned so much about animals!");
    }

    private String getQuestionFromFact(TreeNode fact) {
        String[] parts = SECOND_SPACE.split(fact.data);
        String questionStart = itCanHasIs.get(parts[0].toLowerCase())[2];
        String questionRest = DOT_BANG_END.matcher(parts[1]).replaceAll("?");
        return  questionStart + " " + questionRest;
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
