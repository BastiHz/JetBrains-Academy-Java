package animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;


class KnowledgeTree {

    private TreeNode root;
    private final String FILENAME;
    private final ObjectMapper objectMapper;
    private int numberOfAnimals;
    private int numberOfFacts;
    private int treeHeight;  // max num of edges between root and leaf
    private int totalAnimalDepth;  // depth = num of nodes between root and animal + 1
    private int minAnimalDepth;

    KnowledgeTree(String mapperType) {
        switch (mapperType) {
            case "json":
                objectMapper = new JsonMapper();
                FILENAME = "animals.json";
                break;
            case "xml":
                objectMapper = new XmlMapper();
                FILENAME = "animals.xml";
                break;
            case "yaml":
                objectMapper = new YAMLMapper();
                FILENAME = "animals.yaml";
                break;
            default:
                throw new IllegalArgumentException("Invalid type specified.");
        }
    }

    TreeNode getRoot() {
        return root;
    }

    void insert(
            TreeNode fact,
            TreeNode parent,
            TreeNode oldAnimal,
            TreeNode newAnimal,
            boolean factIsTrueForNewAnimal) {
        if (parent == null) {
            root = fact;
        } else if (oldAnimal == parent.yes) {
            parent.yes = fact;
        } else {
            parent.no = fact;
        }

        if (factIsTrueForNewAnimal) {
            fact.yes = newAnimal;
            fact.no = oldAnimal;
        } else {
            fact.yes = oldAnimal;
            fact.no = newAnimal;
        }
    }

    void load() {
        try {
            root = objectMapper.readValue(new File(FILENAME), TreeNode.class);
        } catch (IOException e) {
            root = null;
        }
        if (root == null || root.data == null) {
            System.out.println("I want to learn about animals.");
            System.out.println("Which animal do you like most?");
            root = TreeNode.newAnimal();
            System.out.println("Wonderful! I've learned so much about animals");
        } else {
            System.out.println(root.data);
            root.initOtherFields();
        }
    }

    void save() {
        try {
            objectMapper
                 .writerWithDefaultPrettyPrinter()
                 .writeValue(new File(FILENAME), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printAllAnimalNames() {
        List<String> names = new ArrayList<>();
        getAllAnimalNames(root, names);
        Collections.sort(names);
        System.out.println("Here are the animals I know:");
        for (String name : names) {
            System.out.println(" - " + name);
        }
    }

    private void getAllAnimalNames(TreeNode node, List<String> list) {
        if (node.isAnimal()) {
            list.add(node.name);
        } else {
            getAllAnimalNames(node.yes, list);
            getAllAnimalNames(node.no, list);
        }
    }

    void printAllFactsForAnimal() {
        System.out.println("Enter the animal:");
        TreeNode animal = TreeNode.newAnimal();
        Deque<String> facts = new ArrayDeque<>();
        if (getAllFactsForAnimal(root, facts, animal.data)) {
            System.out.printf("Facts about the %s:%n", animal.name);
            while (!facts.isEmpty()) {
                System.out.println(" - " + facts.pollLast());
            }
        } else {
            System.out.printf("No facts about the %s.%n", animal.name);
        }
    }

    private boolean getAllFactsForAnimal(TreeNode node, Deque<String> facts, String data) {
        if (node.isAnimal()) {
            return node.data.equals(data);
        }
        if (getAllFactsForAnimal(node.yes, facts, data)) {
            facts.addLast(node.data);
        } else if (getAllFactsForAnimal(node.no, facts, data)) {
            facts.addLast(node.negatedStatement);
        } else {
            return false;
        }
        return true;
    }

    void printStatistics() {
        numberOfAnimals = 0;
        numberOfFacts = 0;
        treeHeight = 0;
        totalAnimalDepth = 0;
        minAnimalDepth = Integer.MAX_VALUE;
        getTreeStats(root, 0);

        System.out.println("The Knowledge Tree stats");
        System.out.println();
        System.out.println("- root node                    " + root.data);
        System.out.printf("- total number of nodes        %d%n", numberOfAnimals + numberOfFacts);
        System.out.println("- total number of animals      " + numberOfAnimals);
        System.out.println("- total number of statements   " + numberOfFacts);
        System.out.println("- height of the tree           " + treeHeight);
        System.out.println("- minimum depth                " + minAnimalDepth);
        System.out.printf(
            "- average depth                %.1f%n",
            (float) totalAnimalDepth / numberOfAnimals
        );
    }

    private void getTreeStats(TreeNode node, int depth) {
        if (node.isAnimal()) {
            numberOfAnimals++;
            if (depth < minAnimalDepth) {
                minAnimalDepth = depth;
            }
            totalAnimalDepth += depth;
        } else {
            numberOfFacts++;
            getTreeStats(node.yes, depth + 1);
            getTreeStats(node.no, depth + 1);
        }
        if (depth > treeHeight) {
            treeHeight = depth;
        }
    }

    void print() {
        System.out.println();
        System.out.println(" └ " + root.data);
        if (!root.isAnimal()) {
            printChildNode(root, "  ");
        }
    }

    private void printChildNode(TreeNode node, String prefix) {
        if (node.yes.isAnimal()) {
            System.out.println(prefix + "├ " + node.yes.data);
        } else {
            System.out.println(prefix + "├ " + node.yes.question);
            printChildNode(node.yes, prefix + "│");
        }
        if (node.no.isAnimal()) {
            System.out.println(prefix + "└ " + node.no.data);
        } else {
            System.out.println(prefix + "└ " + node.no.question);
            printChildNode(node.no, prefix + " ");
        }
    }
}
