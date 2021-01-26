import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int offset = scanner.nextInt();
        LocalDate date = LocalDate.of(year, month, 1).plusMonths(1).minusDays(offset);
        System.out.println(date);
    }
}