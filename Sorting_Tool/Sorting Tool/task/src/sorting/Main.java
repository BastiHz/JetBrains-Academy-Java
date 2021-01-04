package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nNumbers = 0;
        long biggest = Long.MIN_VALUE;
        int nBiggest = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            nNumbers++;
            if (number > biggest) {
                biggest = number;
                nBiggest = 1;
            } else if (number == biggest) {
                nBiggest++;
            }
        }

        System.out.printf("Total numbers: %d.\n", nNumbers);
        System.out.printf("The greatest number: %d (%d time(s)).", biggest, nBiggest);
    }
}
