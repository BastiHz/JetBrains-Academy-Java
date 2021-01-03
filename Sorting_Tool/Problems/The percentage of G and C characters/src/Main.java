import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] sequence = scanner.nextLine().toLowerCase().toCharArray();
        double gcCount = 0;
        for (char base : sequence) {
            if (base == 'g' || base == 'c') {
                gcCount++;
            }
        }
        System.out.println(gcCount / sequence.length * 100);
    }
}