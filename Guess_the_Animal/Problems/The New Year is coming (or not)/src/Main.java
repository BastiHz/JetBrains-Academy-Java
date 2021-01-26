import java.util.Scanner;
import java.time.LocalDate;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate date1 = LocalDate.parse(scanner.next());
        int days = scanner.nextInt();
        LocalDate date2 = date1.plusDays(days);
        System.out.println(date2.getYear() > date1.getYear());
    }
}