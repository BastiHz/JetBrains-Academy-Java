import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Deque<String> stack = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            stack.offerLast(scanner.next());
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pollLast());
        }
    }
}