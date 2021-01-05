import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int nShifts = countShiftsInsertionSortDescending(arr);
        System.out.println(nShifts);
    }

    public static int  countShiftsInsertionSortDescending(int[] array) {
        int nShifts = 0;
        for (int i = 1; i < array.length; i++) {
            int elem = array[i];
            int j = i - 1;

            boolean shifted = false;
            while (j >= 0 && array[j] < elem) {
                array[j + 1] = array[j];
                j--;
                shifted = true;
            }
            nShifts += shifted ? 1 : 0;
            array[j + 1] = elem;
        }
        return nShifts;
    }
}
