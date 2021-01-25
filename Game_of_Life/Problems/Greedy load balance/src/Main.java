import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        int load1 = 0;
        int load2 = 0;
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            int load = scanner.nextInt();
            if (load1 <= load2) {
                load1 += load;
                q1.add(id);
            } else {
                load2 += load;
                q2.add(id);
            }
        }
        System.out.println(q1.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(q2.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}