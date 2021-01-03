import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        partition(n, n, "");
    }

    public static void partition(int n, int max, String out) {
        if (n == 0) {
            System.out.println(out);
            return;
        }
        for (int i = 1; i <= Math.min(max, n); i++) {
            partition(n - i, i, out + " " + i);
        }
    }
}
