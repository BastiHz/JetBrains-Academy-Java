import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nRow = scanner.nextInt();
        int nCol = scanner.nextInt();
        int[][] seats = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                seats[i][j] = scanner.nextInt();
            }
        }
        int required = scanner.nextInt();
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (seats[i][j] == 0) {
                    int found = 1;
                    for (int k = j + 1; k < j + required && k < nCol; k++) {
                        if (seats[i][k] == 0) {
                            found++;
                        }
                    }
                    if (found == required) {
                        System.out.println(i + 1);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
}