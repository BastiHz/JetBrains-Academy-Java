package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] line = scanner.nextLine().trim().split("\\s+");
            String first = line[0];
            switch (first) {
                case "/exit":
                    System.out.println("Bye!");
                    return;
                case "/help":
                    System.out.println("The program calculates the sum of numbers.");
                    continue;
                case "":
                    continue;
            }
            int result = 0;
            for (String s : line) {
                result += Integer.parseInt(s);
            }
            System.out.println(result);
        }
    }
}
