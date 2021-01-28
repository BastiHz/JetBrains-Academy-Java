import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            tree.insert(scanner.nextInt());
        }
        System.out.println(tree.maxDepth);
    }
}


class BinaryTree {
    Node root = null;
    int maxDepth = 0;

    private static class Node {
        int key;
        int depth;
        Node parent;
        Node left;
        Node right;

        Node(int key, int depth, Node parent) {
            this.key = key;
            this.depth = depth;
            this.parent = parent;
        }
    }

    void insert(int key) {
        root = insert(root, null, key, 0);
    }

    private Node insert(Node t, Node parent, int key, int depth) {
        if (t == null) {
            if (depth > maxDepth) {
                maxDepth = depth;
            }
            return new Node(key, depth, parent);
        }
        if (key < t.key) {
            t.left = insert(t.left, t, key, depth + 1);
        } else {
            t.right = insert(t.right, t, key, depth + 1);
        }
        return t;
    }

}
