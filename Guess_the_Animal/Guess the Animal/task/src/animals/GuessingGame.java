package animals;

import java.util.regex.Pattern;


public class GuessingGame {

    private final Pattern YES_ANSWER = Pattern.compile(
        "(y|yes|yeah|yep|sure|right|affirmative|correct|indeed|you bet|exactly|you said it)[.!]?",
        Pattern.CASE_INSENSITIVE
    );
    private final Pattern NO_ANSWER = Pattern.compile(
        "(n|no|no way|nah|nope|negative|i don't think so|yeah no)[.!]?",
        Pattern.CASE_INSENSITIVE
    );
    private final Pattern FACT = Pattern.compile(
        "(it can|it has|it is)\\s\\w+.*",
        Pattern.CASE_INSENSITIVE
    );
    private final String[] UNCLEAR = {
        "I'm not sure I caught you. Was it yes or no?",
        "Funny, I still don't understand. Is it yes or no?",
        "Oh, it's too complicated for me. Just tell me yes or no.",
        "Could you please simply say yes or no?",
        "Oh no, don't try to confuse me. Please say yes or no."
    };
    private final KnowledgeTree knowledgeTree;

    GuessingGame(KnowledgeTree knowledgeTree) {
        this.knowledgeTree = knowledgeTree;
    }

    void play() {
        do {
            System.out.println();
            System.out.println("You think of an animal, and I guess it.");
            System.out.println("Press enter when you're ready.");
            Helpers.scanner.nextLine();
            TreeNode currentTreeNode = knowledgeTree.getRoot();
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
                    System.out.println(currentTreeNode.question);
                    parent = currentTreeNode;
                    currentTreeNode = getYesNo() ? currentTreeNode.yes : currentTreeNode.no;
                }
            }
            System.out.println();
            System.out.println("Would you like to play again?");
        } while (getYesNo());
    }

    private void createNewFact(TreeNode oldAnimal, TreeNode parent) {
        TreeNode newAnimal = TreeNode.newAnimal();
        TreeNode fact;
        while (true) {
            System.out.printf("Specify a fact that distinguishes %s from %s.%n",
                oldAnimal.data, newAnimal.data
            );
            System.out.println("The sentence should be of the format: 'It can/has/is ...'.");
            String answer = Helpers.nextLine();
            if (FACT.matcher(answer).matches()) {
                fact = TreeNode.newFact(answer);
                break;
            }
            System.out.println("The examples of a statement:");
            System.out.println(" - It can fly.");
            System.out.println(" - It has horns.");
            System.out.println(" - It is a mammal.");
        }

        System.out.printf("Is the statement correct for %s?%n", newAnimal.data);
        boolean factIsTrueForNewAnimal = getYesNo();
        knowledgeTree.insert(fact, parent, oldAnimal, newAnimal, factIsTrueForNewAnimal);
        System.out.println("Wonderful! I've learned so much about animals!");
    }

    private boolean getYesNo() {
        while (true) {
            String answer = Helpers.nextLine();
            if (YES_ANSWER.matcher(answer).matches()) {
                return true;
            }
            if (NO_ANSWER.matcher(answer).matches()) {
                return false;
            }
            Helpers.printRandomMessage(UNCLEAR);
        }
    }
}
