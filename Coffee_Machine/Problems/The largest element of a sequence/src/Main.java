import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int largest = 0;
        int elem = 0;
        do {
            elem = scanner.nextInt();
            if (elem > largest) {
                largest = elem;
            }
        } while (elem != 0);
        System.out.print(largest);
    }
}