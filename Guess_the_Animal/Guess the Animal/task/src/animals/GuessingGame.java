package animals;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class GuessingGame {

    private final Pattern YES_ANSWER = Pattern.compile(
        TextHelper.getString("pattern.positiveAnswer"),
        Pattern.CASE_INSENSITIVE
    );
    private final Pattern NO_ANSWER = Pattern.compile(
        TextHelper.getString("pattern.negativeAnswer"),
        Pattern.CASE_INSENSITIVE
    );
    private final Pattern FACT = Pattern.compile(
        TextHelper.getString("pattern.fact"),
        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS
    );
    @SuppressWarnings("unchecked")
    private final UnaryOperator<String> getStatementRest =
        (UnaryOperator<String>) TextHelper.getObject("fun.getStatementRest");
    private final KnowledgeTree knowledgeTree;

    GuessingGame(KnowledgeTree knowledgeTree) {
        this.knowledgeTree = knowledgeTree;
    }

    void play() {
        do {
            System.out.println();
            TextHelper.println("game.think");
            TextHelper.println("game.enter");
            TextHelper.scanner.nextLine();
            TreeNode currentTreeNode = knowledgeTree.getRoot();
            TreeNode parent = null;
            while (true) {
                if (currentTreeNode.isAnimal()) {
                    TextHelper.printf("game.guess", currentTreeNode.data);
                    if (getYesNo()) {
                        TextHelper.println("game.win");
                    } else {
                        TextHelper.println("game.giveUp");
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
            TextHelper.println("game.again");
        } while (getYesNo());
    }

    private void createNewFact(TreeNode oldAnimal, TreeNode parent) {
        TreeNode newAnimal = TreeNode.newAnimal();
        TreeNode fact;
        while (true) {
            TextHelper.printf(
                "statement.prompt",
                new String[] {oldAnimal.data, newAnimal.data}
            );
            String answer = TextHelper.nextLine();
            if (FACT.matcher(answer).matches()) {
                fact = TreeNode.newFact(answer);
                break;
            }
            TextHelper.println("statement.error");
        }

        TextHelper.printf("statement.isCorrect", newAnimal.data);
        boolean factIsTrueForNewAnimal = getYesNo();
        knowledgeTree.insert(fact, parent, oldAnimal, newAnimal, factIsTrueForNewAnimal);

        TextHelper.println("statement.learned");
        String positiveRest = getStatementRest.apply(fact.data);
        String negativeRest = getStatementRest.apply(fact.negatedStatement);
        if (factIsTrueForNewAnimal) {
            TextHelper.printf("statement.stateLearned", new String[] {oldAnimal.name, negativeRest});
            TextHelper.printf("statement.stateLearned", new String[] {newAnimal.name, positiveRest});
        } else {
            TextHelper.printf("statement.stateLearned", new String[] {oldAnimal.name, positiveRest});
            TextHelper.printf("statement.stateLearned", new String[] {newAnimal.name, negativeRest});
        }

        TextHelper.print("animal.nice");
        TextHelper.println("animal.learnedMuch");
    }

    private boolean getYesNo() {
        while (true) {
            String answer = TextHelper.nextLine();
            if (YES_ANSWER.matcher(answer).matches()) {
                return true;
            }
            if (NO_ANSWER.matcher(answer).matches()) {
                return false;
            }
            TextHelper.println("ask.again");
        }
    }
}
