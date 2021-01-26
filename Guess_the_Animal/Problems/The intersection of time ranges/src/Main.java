import java.util.Scanner;
import java.time.LocalTime;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalTime start1 = LocalTime.parse(scanner.next());
        LocalTime end1 = LocalTime.parse(scanner.next());
        LocalTime start2 = LocalTime.parse(scanner.next());
        LocalTime end2 = LocalTime.parse(scanner.next());
        if (start1.isBefore(start2)) {
            System.out.println(end1.isAfter(start2) || end1.equals(start2));
        } else {
            System.out.println(end2.isAfter(start1) || end2.equals(start1));
        }
    }
}
