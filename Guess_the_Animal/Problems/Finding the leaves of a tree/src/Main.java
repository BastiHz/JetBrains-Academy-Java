import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(scanner.nextInt());
            b.add(scanner.nextInt());
        }
        List<Integer> leaves = new ArrayList<>();
        for (int x : b) {
            if (!a.contains(x)) {
                leaves.add(x);
            }
        }
        System.out.println(leaves.size());
        Collections.sort(leaves);
        for (int x : leaves) {
            System.out.print(x + " ");
        }
    }
}