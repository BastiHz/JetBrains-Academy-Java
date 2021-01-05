// Solution: https://www.geeksforgeeks.org/counting-inversions

import java.util.Scanner;

class Main {
    static long nInversions = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        mergeSortDesc(array, 0, array.length);
        System.out.println(nInversions);
    }

    public static void mergeSortDesc(int[] array, int leftIncl, int rightExcl) {
        // the base case: if subarray contains <= 1 items, stop dividing because it's sorted
        if (rightExcl <= leftIncl + 1) {
            return;
        }

        // divide: calculate the index of the middle element
        int middle = leftIncl + (rightExcl - leftIncl) / 2;

        mergeSortDesc(array, leftIncl, middle);  // conquer: sort the left subarray
        mergeSortDesc(array, middle, rightExcl); // conquer: sort the right subarray

        // combine: merge both sorted subarrays into sorted one
        mergeDesc(array, leftIncl, middle, rightExcl);
    }

    private static void mergeDesc(int[] array, int left, int middle, int right) {
        int i = left;   // index for the left subarray
        int j = middle; // index for the right subarray
        int k = 0;      // index for the temp subarray

        int[] temp = new int[right - left]; // temporary array for merging

        // get the next lesser element from one of two subarrays
        // and then insert it in the array until one of the subarrays is empty
        while (i < middle && j < right) {
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
                nInversions += middle - i;
            }
            k++;
        }

        if (i < middle) {
            // insert all the remaining elements of the left subarray in the array
            System.arraycopy(array, i, temp, k, middle - i);
        } else if (j < right) {
            // insert all the remaining elements of the right subarray in the array
            System.arraycopy(array, j, temp, k, right - j);
        }

        // effective copying elements from temp to array
        System.arraycopy(temp, 0, array, left, temp.length);
    }
}
