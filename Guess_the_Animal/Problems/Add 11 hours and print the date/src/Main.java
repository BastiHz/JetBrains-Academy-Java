import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime time = LocalDateTime.parse(scanner.next()).plusHours(11);
        System.out.println(time.toLocalDate());
    }
}