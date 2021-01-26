import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalTime time1 = LocalTime.parse(scanner.nextLine());
        LocalTime time2 = LocalTime.parse(scanner.nextLine());
        int difference = Math.abs(time2.toSecondOfDay() - time1.toSecondOfDay());
        System.out.println(difference);
    }
}