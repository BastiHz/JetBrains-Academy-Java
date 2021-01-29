import java.time.Duration;
import java.util.Scanner;
import java.time.LocalDateTime;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());
        LocalDateTime beginningOfYear = LocalDateTime.of(dateTime.getYear(), 1, 1, 0, 0);
        Duration duration = Duration.between(beginningOfYear, dateTime);
        System.out.println(duration.toHours());
    }
}