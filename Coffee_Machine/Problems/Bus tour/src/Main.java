import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int busHeight = scanner.nextInt();
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            int bridgeHeight = scanner.nextInt();
            if (bridgeHeight <= busHeight) {
                System.out.print("Will crash on bridge " + i);
                return;
            }
        }
        System.out.print("Will not crash");
    }
}