package calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern doubleMinus = Pattern.compile("--");
    private static final Pattern multiplePlus = Pattern.compile("\\+{2,}");
    private static final Pattern plusMinusOrMinusPlus = Pattern.compile("\\+-|-\\+");
    private static final Pattern whiteSpace = Pattern.compile("\\s+");
    private static final Pattern aroundOperators = Pattern.compile("(?<=[+-])|(?=[+-])");
    private static final Pattern operators = Pattern.compile("[+-]");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine().trim();
            switch (line) {
                case "":
                    continue;
                case "/help":
                    System.out.println("The program does multiplication and subtraction.");
                    continue;
                case "/exit":
                    System.out.println("Bye!");
                    return;
            }

            String[] elements = extractElements(line);
            long result = calculate(elements);
            System.out.println(result);
        }
    }

    private static String[] extractElements(String input) {
        input = whiteSpace.matcher(input).replaceAll("");             // remove all whitespace
        input = doubleMinus.matcher(input).replaceAll("+");           // "--" -> "+"
        input = multiplePlus.matcher(input).replaceAll("+");          // "++...+" -> "+"
        input = plusMinusOrMinusPlus.matcher(input).replaceAll("-");  // "+-" | "-+" -> "-"
        return aroundOperators.split(input);  // split before and after operators
    }

    private static long calculate(String[] elements) {
        long result = 0L;
        String nextOperator = "+";
        for (String element : elements) {
            if (operators.matcher(element).matches()) {
                nextOperator = element;
            } else {
                long number = Long.parseLong(element);
                switch (nextOperator) {
                    case "+":
                        result += number;
                        break;
                    case "-":
                        result -= number;
                        break;
                }
            }
        }
        return result;
    }
}
