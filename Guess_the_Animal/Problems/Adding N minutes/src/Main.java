import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime time = LocalDateTime.parse(scanner.next()).plusMinutes(scanner.nextInt());
        System.out.printf("%d %d %s", time.getYear(), time.getDayOfYear(), time.toLocalTime());
    }
}