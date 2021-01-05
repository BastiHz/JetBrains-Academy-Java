import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] result = new int[0];
        int nSequences = scanner.nextInt();
        for (int i = 0; i < nSequences; i++) {
            int arrayLength = scanner.nextInt();
            int[] arr = new int[arrayLength];
            for (int j = 0; j < arrayLength; j++) {
                arr[j] = scanner.nextInt();
            }
            // Grow the result array by appending the new array to the end.
            int[] temp = result.clone();
            result = new int[result.length + arrayLength];
            System.arraycopy(temp, 0, result, 0, temp.length);
            System.arraycopy(arr, 0, result, temp.length, arr.length);
        }
        mergeSort(result);
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.print(result[i] + " ");
        }
    }

    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length);
    }

    public static void mergeSort(int[] array, int leftIncl, int rightExcl) {
        // the base case: if subarray contains <= 1 items, stop dividing because it's sorted
        if (rightExcl <= leftIncl + 1) {
            return;
        }

        // divide: calculate the index of the middle element
        int middle = leftIncl + (rightExcl - leftIncl) / 2;

        mergeSort(array, leftIncl, middle);  // conquer: sort the left subarray
        mergeSort(array, middle, rightExcl); // conquer: sort the right subarray

        // combine: merge both sorted subarrays into sorted one
        merge(array, leftIncl, middle, rightExcl);
    }

    private static void merge(int[] array, int left, int middle, int right) {
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