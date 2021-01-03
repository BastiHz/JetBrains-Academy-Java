import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = scanner.nextInt();
        }
        int target = scanner.nextInt();
        boolean targetInArray = false;
        for (int x : array) {
            if (x == target) {
                targetInArray = true;
                break;
            }
        }
        System.out.println(targetInArray);
    }
}