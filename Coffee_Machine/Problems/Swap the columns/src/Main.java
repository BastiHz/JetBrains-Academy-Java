import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nRow = scanner.nextInt();
        int nCol = scanner.nextInt();
        int[][] matrix = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        for (int i = 0; i < nRow; i++) {
            int temp = matrix[i][a];
            matrix[i][a] = matrix[i][b];
            matrix[i][b] = temp;
        }
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}