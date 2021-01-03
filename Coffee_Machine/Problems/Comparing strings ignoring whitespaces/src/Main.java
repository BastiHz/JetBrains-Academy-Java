import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine().strip().replace(" ", "");
        String b = scanner.nextLine().strip().replace(" ", "");
        System.out.print(a.equals(b));
    }
}