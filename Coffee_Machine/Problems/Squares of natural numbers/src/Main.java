import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i = 1;
        int square = i * i;
        while (square <= n) {
            System.out.println(square);
            i++;
            square = i * i;
        }
    }
}