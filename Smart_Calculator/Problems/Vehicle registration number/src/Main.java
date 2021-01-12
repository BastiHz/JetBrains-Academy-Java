import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine();
        String pattern = "[ABCEHKMOPTYX]\\d{3}[ABCEHKMOPTYX]{2}";
        System.out.println(regNum.matches(pattern));
    }
}