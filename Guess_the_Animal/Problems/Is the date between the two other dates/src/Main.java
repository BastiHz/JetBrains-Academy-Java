import java.util.Scanner;
import java.time.LocalDate;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate x = LocalDate.parse(scanner.next());
        LocalDate m = LocalDate.parse(scanner.next());
        LocalDate n = LocalDate.parse(scanner.next());
        System.out.println(x.isAfter(m) && x.isBefore(n) || x.isAfter(n) && x.isBefore(m));
    }
}