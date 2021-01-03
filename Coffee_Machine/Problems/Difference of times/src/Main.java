import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aHour = scanner.nextInt();
        int aMinute = scanner.nextInt();
        int aSecond = scanner.nextInt();
        int bHour = scanner.nextInt();
        int bMinute = scanner.nextInt();
        int bSecond = scanner.nextInt();

        int diff = bHour * 3600 + bMinute * 60 + bSecond - (aHour * 3600 + aMinute * 60 + aSecond);
        System.out.println(diff);
    }
}