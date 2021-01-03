import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        s = s.replace('a', 'b');
        System.out.print(s);
    }
}