import java.util.Scanner;
import java.time.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        LocalTime earliestReachableClosingTime = LocalTime.of(20, 0);  // 19:30 + 00:30
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            LocalTime closingTime = LocalTime.parse(scanner.next());
            if (closingTime.isAfter(earliestReachableClosingTime)) {
                System.out.println(name);
            }
        }
    }
}