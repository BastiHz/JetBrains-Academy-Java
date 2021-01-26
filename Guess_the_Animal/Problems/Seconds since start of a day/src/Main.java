import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        LocalTime time = LocalTime.ofSecondOfDay(seconds);
        System.out.println(time);
    }
}