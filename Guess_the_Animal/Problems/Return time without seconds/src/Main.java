import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputTime = scanner.nextLine();
        LocalTime time = LocalTime.parse(inputTime).withSecond(0);
        System.out.println(time);
    }
}