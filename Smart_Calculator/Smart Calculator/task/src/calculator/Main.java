package calculator;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern doubleMinus = Pattern.compile("--");
    private static final Pattern multiplePlus = Pattern.compile("\\+{2,}");
    private static final Pattern plusMinusOrMinusPlus = Pattern.compile("\\+-|-\\+");
    private static final Pattern remover = Pattern.compile("\\s+|_");
    private static final Pattern operatorSplitter = Pattern.compile("(?<=[+-])|(?=[+-])");
    private static final Pattern knownOperators = Pattern.compile("[+-]");
    private static final Pattern spaceBetweenNumbersOrVariables = Pattern.compile("[^\\W_]\\s+[^\\W_]");
    private static final Pattern disallowedSymbols = Pattern.compile("[^\\w\\s=+-]");
    private static final Pattern allowedLastSymbols = Pattern.compile("\\w$");
    private static final Pattern allowedIdentifiers = Pattern.compile("[a-zA-Z]+");
    private static final Pattern assignmentOperator = Pattern.compile("=");
    private static final HashMap<String, Long> variables = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isBlank()) {
                continue;
            } else if (line.startsWith("/")) {
                switch (line) {
                    case "/exit":
                        System.out.println("Bye!");
                        return;
                    case "/help":
                        System.out.println("The calculator supports multiplication, subtraction,");
                        System.out.println("and variable assignment using \"=\". Variables are");
                        System.out.println("case-sensitive and must only consist of Latin characters.");
                        System.out.println("Type \"/exit\" to quit.");
                        break;
                    default:
                        System.out.println("Unknown command");
                        break;
                }
                continue;
            }

            if (checkForInvalidExpression(line)) {
                System.out.println("Invalid expression");
                continue;
            }

            line = cleanInput(line);
            if (line.contains("=")) {
                assign(line);
            } else {
                String[] elements = operatorSplitter.split(line);
                // System.out.println(Arrays.toString(elements));  // DEBUG
                try {
                    long result = calculate(elements);
                    System.out.println(result);
                } catch(NumberFormatException e) {
                    System.out.println("Invalid expression");
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown variable");
                }
            }
        }
    }

    private static String cleanInput(String input) {
        input = remover.matcher(input).replaceAll("");  // remove all whitespace and "_"
        input = doubleMinus.matcher(input).replaceAll("+");  // "--" -> "+"
        input = multiplePlus.matcher(input).replaceAll("+");  // "++" -> "+"
        return plusMinusOrMinusPlus.matcher(input).replaceAll("-");  // "+-" | "-+" -> "-"
    }

    private static boolean checkForInvalidExpression(String line) {
        return !allowedLastSymbols.matcher(line).find()
            || spaceBetweenNumbersOrVariables.matcher(line).find()
            || disallowedSymbols.matcher(line).find();
    }

    private static void assign(String input) {
        String[] splitAssignment = assignmentOperator.split(input);
        // System.out.println(Arrays.toString(splitAssignment));  // DEBUG
        if (splitAssignment.length != 2) {
            System.out.println("Invalid assignment");
            return;
        }

        String identifier = splitAssignment[0];
        if (!allowedIdentifiers.matcher(identifier).matches()) {
            System.out.println("Invalid identifier");
            return;
        }

        String[] valueElements = operatorSplitter.split(splitAssignment[1]);
        long value;
        try {
            value = calculate(valueElements);
        } catch (NumberFormatException e) {
            System.out.println("Invalid assignment");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown variable");
            return;
        }
        variables.put(identifier, value);
        // System.out.println(variables);  // DEBUG
    }

    private static long calculate(String[] elements) {
        long result = 0L;
        char operator = '+';
        for (String element : elements) {
            if (knownOperators.matcher(element).matches()) {
                operator = element.charAt(0);
            } else {
                long value;
                if (allowedIdentifiers.matcher(element).matches()) {
                    if (variables.containsKey(element)) {
                        value = variables.get(element);
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    value = Long.parseLong(element);
                }
                switch (operator) {
                    case '+':
                        result += value;
                        break;
                    case '-':
                        result -= value;
                        break;
                }
            }
        }
        return result;
    }
}
