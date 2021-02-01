package animals;

class Node {
    Node trueChild;
    Node falseChild;
    // No parent Node to avoid circular references in json.
    String data;

    Node(String data) {
        this.data = data;
    }

    Node(String article, String name) {
        this.data = article + " " + name;
    }

    boolean isAnimal() {
        return trueChild == null && falseChild == null;
    }
}

class KnowledgeTree {
    Node root;

    void insert(Node fact, Node parent, Node oldAnimal, Node newAnimal, boolean factIsTrueForNewAnimal) {
        if (parent == null) {
            root = fact;
        } else if (oldAnimal == parent.trueChild) {
            parent.trueChild = fact;
        } else {
            parent.falseChild = fact;
        }

        if (factIsTrueForNewAnimal) {
            fact.trueChild = newAnimal;
            fact.falseChild = oldAnimal;
        } else {
            fact.trueChild = oldAnimal;
            fact.falseChild = newAnimal;
        }
    }
}
