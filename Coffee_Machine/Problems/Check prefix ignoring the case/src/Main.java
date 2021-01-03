import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        char prefix = s.charAt(0);
        System.out.print(prefix == 'J' || prefix == 'j');
    }
}