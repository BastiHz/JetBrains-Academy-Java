import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = scanner.nextInt();
        }
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean nextToEachOther = false;
        for (int i = 0; i < len - 1; i++) {
            int a = array[i];
            int b = array[i + 1];
            if (a == n && b == m || a == m && b == n) {
                nextToEachOther = true;
                break;
            }
        }
        System.out.println(nextToEachOther);
    }
}