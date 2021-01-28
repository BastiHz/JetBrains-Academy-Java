import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int[] parents = new int[k];
        for (int i = 0; i < k; i++) {
            parents[i] = scanner.nextInt();
        }
        int maxNumberOfNodes = 0;
        for (int i = 0; i < k; i++) {
            int numberOfNodes = 1;
            int parent = parents[i];
            while (parent != -1) {
                numberOfNodes++;
                parent = parents[parent];
            }
            if (numberOfNodes > maxNumberOfNodes) {
                maxNumberOfNodes = numberOfNodes;
            }
        }
        System.out.println(maxNumberOfNodes);
    }
}