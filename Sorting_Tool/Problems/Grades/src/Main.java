import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] gradeCount = new int[6];
        for (int i = 0; i < n; i++) {
            gradeCount[scanner.nextInt()]++;
        }
        for (int i = 2; i < gradeCount.length; i++) {
            System.out.print(gradeCount[i] + " ");
        }
    }
}