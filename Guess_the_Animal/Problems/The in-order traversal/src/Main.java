import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            int key = Integer.parseInt(line[1]);
            switch (line[0]) {
                case "+":
                    tree.insert(key, Integer.parseInt(line[2]));
                    break;
                case "!":
                    tree.changeValue(key, Integer.parseInt(line[2]));
                    break;
                case "-":
                    tree.remove(key);
                    break;
                default:
                    break;
            }
        }
        for (int k : tree.getValuesSortedByKeys()) {
            System.out.print(k + " ");
        }
    }
}


class Node {
    int key;
    int value;
    Node parent;
    Node left;
    Node right;

    Node(int key, int value, Node parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
    }
}


class BinaryTree {
    Node root = null;

    void insert(int key, int value) {
        root = insert(root, null, key, value);
    }

    private Node insert(Node t, Node parent, int key, int value) {
        if (t == null) {
            return new Node(key, value, parent);
        }
        if (key < t.key) {
            t.left = insert(t.left, t, key, value);
        } else {
            t.right = insert(t.right, t, key, value);
        }
        return t;
    }

    void changeValue(int key, int newValue) {
        Node t = search(root, key);
        t.value = newValue;
    }

    private Node search(Node t, int key) {
        if (t == null || t.key == key) {
            return t;
        }
        if (key < t.key) {
            return search(t.left, key);
        } else {
            return search(t.right, key);
        }
    }

    void remove(int key) {
        remove(root, key);
    }

    private void remove(Node t, int key) {
        if (t == null) {
            return;
        }
        if (key < t.key) {
            remove(t.left, key);
        } else if (key > t.key) {
            remove(t.right, key);
        } else if (t.left == null && t.right == null) {
            replace(t, null);
        } else if (t.left == null) {
            replace(t, t.right);
        } else if (t.right == null) {
            replace(t, t.left);
        } else {
            Node m = t.left;
            while (m.right != null) {
                m = m.right;
            }
            t.key = m.key;
            t.value = m.value;
            replace(m, m.left);
        }
    }

    private void replace(Node a, Node b) {
        if (a.parent == null) {
            root = b;
        } else if (a == a.parent.left) {
            a.parent.left = b;
        } else {
            a.parent.right = b;
        }
        if (b != null) {
            b.parent = a.parent;
        }
    }

    List<Integer> getValuesSortedByKeys() {
        List<Integer> list = new ArrayList<>();
        getValuesSortedByKeys(root, list);
        return list;
    }

    private void getValuesSortedByKeys(Node t, List<Integer> list) {
        if (t == null) {
            return;
        }
        getValuesSortedByKeys(t.left, list);
        list.add(t.value);
        getValuesSortedByKeys(t.right, list);
    }
}
