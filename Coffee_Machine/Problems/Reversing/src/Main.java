import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int hundreds = n / 100;
        int tens = (n % 100) / 10;
        int ones = n % 10;
        System.out.print(ones * 100 + tens * 10 + hundreds);
    }
}