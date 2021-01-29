package animals;


abstract class Node {
    Node parent;
    Node trueChild;
    Node falseChild;
}


class Animal extends Node {
    final String article;
    final String name;

    Animal(String article, String name) {
        this.article = article;
        this.name = name;
    }
}


class Fact extends Node {
    final String fact;
    final String question;

    Fact(String fact, String question) {
        this.fact = fact;
        this.question = question;
    }
}


class KnowledgeTree {
    private Node root;

    Node getRoot() {
        return root;
    }

    void setRoot(Node root) {
        this.root = root;
    }

    void insert(Fact fact, Animal oldAnimal, Animal newAnimal, boolean factIsTrueForNewAnimal) {
        if (oldAnimal.parent == null) {
            root = fact;
        } else if (oldAnimal == oldAnimal.parent.trueChild) {
            oldAnimal.parent.trueChild = fact;
        } else {
            oldAnimal.parent.falseChild = fact;
        }
        fact.parent = oldAnimal.parent;
        oldAnimal.parent = fact;
        newAnimal.parent = fact;

        if (factIsTrueForNewAnimal) {
            fact.trueChild = newAnimal;
            fact.falseChild = oldAnimal;
        } else {
            fact.trueChild = oldAnimal;
            fact.falseChild = newAnimal;
        }
    }
}
