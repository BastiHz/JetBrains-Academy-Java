package animals;

import java.util.Random;
import java.util.Scanner;


class Helpers {

    static final Scanner scanner = new Scanner(System.in);
    static final Random random = new Random();

    static void printRandomMessage(String[] messages) {
        System.out.println(messages[random.nextInt(messages.length)]);
    }

    static String nextLine() {
        return scanner.nextLine().strip().toLowerCase();
    }

    static String capitalizeFirst(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
