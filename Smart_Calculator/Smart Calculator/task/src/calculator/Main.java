package calculator;

import java.util.*;
import java.util.regex.Pattern;
import java.math.BigInteger;

public class Main {
    private static final Pattern doubleMinus = Pattern.compile("--");
    private static final Pattern multiplePlus = Pattern.compile("\\+{2,}");
    private static final Pattern plusMinusOrMinusPlus = Pattern.compile("\\+-|-\\+");
    private static final Pattern invalidOperatorCombination = Pattern.compile("[^\\w\\s)]\\s*[*/^]|[*/^]\\s*[^\\w\\s(]");
    private static final Pattern remover = Pattern.compile("\\s+|_");
    private static final Pattern operatorSplitter = Pattern.compile("(?<=[+*/^()-])|(?=[+*/^()-])");
    private static final Pattern knownOperators = Pattern.compile("[+*/^-]");
    private static final Pattern spaceBetweenNumbersOrVariables = Pattern.compile("\\w\\s+\\w");
    private static final Pattern disallowedSymbols = Pattern.compile("[^\\w\\s=+*/^()-]|_");
    private static final Pattern allowedLastSymbols = Pattern.compile("[\\w)]$");
    private static final Pattern allowedIdentifiers = Pattern.compile("[a-zA-Z]+");
    private static final Pattern assignmentOperator = Pattern.compile("=");
    private static final HashMap<String, BigInteger> variables = new HashMap<>();
    private static final Map<String, Integer> operatorPrecedence = Map.of(
            "+", 1, "-", 1, "*", 2, "/", 2, "^", 3
    );

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
                        printHelp();
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
                if (checkAllVariablesAreKnown(elements)) {
                    try {
                        System.out.println(calculate(elements));
                    } catch(IllegalArgumentException e) {
                        System.out.println("Invalid expression");
                    }
                }
            }
        }
    }

    private static void printHelp() {
        System.out.println("The calculator supports addition, subtraction, multiplication,");
        System.out.println("integer division, exponentiation, and variable assignment.");
        System.out.println("Variables are case-sensitive and must only consist of Latin characters.");
        System.out.println("\nThis calculator is just written to practice Java. It does not catch all");
        System.out.println("possible bad combinations of inputs. It also uses BigIntegers for all");
        System.out.println("numbers small and big, which is not ideal for performance. The main goal ");
        System.out.println("is to pass all tests.");
        System.out.println("\nType \"/exit\" to quit.");
    }

    private static boolean checkForInvalidExpression(String line) {
        return !allowedLastSymbols.matcher(line).find()
                || disallowedSymbols.matcher(line).find()
                || spaceBetweenNumbersOrVariables.matcher(line).find()
                || invalidOperatorCombination.matcher(line).find();
    }

    private static String cleanInput(String input) {
        input = remover.matcher(input).replaceAll("");  // remove all whitespace and "_"
        input = doubleMinus.matcher(input).replaceAll("+");  // "--" -> "+"
        input = multiplePlus.matcher(input).replaceAll("+");  // "++" -> "+"
        return plusMinusOrMinusPlus.matcher(input).replaceAll("-");  // "+-" | "-+" -> "-"
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
        if (checkAllVariablesAreKnown(valueElements)) {
            BigInteger value;
            try {
                value = calculate(valueElements);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid assignment");
                return;
            }
            variables.put(identifier, value);
        }
    }

    private static boolean checkAllVariablesAreKnown(String[] elements) {
        for (String element : elements) {
            if (allowedIdentifiers.matcher(element).matches() && !variables.containsKey(element)) {
                // Identifier is valid but refers to unknown variable.
                System.out.println("Unknown variable");
                return false;
            }
        }
        return true;
    }

    private static BigInteger calculate(String[] elements) {
        // System.out.println(Arrays.toString(elements));  // DEBUG
        Queue<String> postfix = toPostFixNotation(elements);
        // System.out.println(postfix);  // DEBUG

        Deque<BigInteger> resultStack = new ArrayDeque<>();
        resultStack.addLast(BigInteger.ZERO);
        while (!postfix.isEmpty()) {
            String element = postfix.remove();
            if (knownOperators.matcher(element).matches()) {
                BigInteger b = resultStack.removeLast();
                BigInteger a = resultStack.removeLast();
                switch (element) {
                    case "+":
                        resultStack.addLast(a.add(b));
                        break;
                    case "-":
                        resultStack.addLast(a.subtract(b));
                        break;
                    case "*":
                        resultStack.addLast(a.multiply(b));
                        break;
                    case "/":
                        resultStack.addLast(a.divide(b));
                        break;
                    case "^":
                        if (b.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0) {
                            resultStack.addLast(a.pow(b.intValue()));
                        } else {
                            // Exponent is too large.
                            throw new IllegalArgumentException();
                        }
                        break;
                }
            } else {
                if (allowedIdentifiers.matcher(element).matches()) {
                    resultStack.addLast(variables.get(element));
                } else {
                    resultStack.addLast(new BigInteger(element));
                }
            }
        }
        return resultStack.removeLast();
    }

    private static Queue<String> toPostFixNotation(String[] elements) {
        Queue<String> postfix = new ArrayDeque<>(elements.length);
        Deque<String> operatorStack = new ArrayDeque<>(elements.length);

        for (String element : elements) {
            if (knownOperators.matcher(element).matches()) {
                String stackLast = operatorStack.peekLast();
                if (!operatorStack.isEmpty()
                        && !"(".equals(stackLast)
                        && !hasHigherPrecedence(element, stackLast)) {
                    while (true) {
                        stackLast = operatorStack.peekLast();
                        if (stackLast == null
                                || "(".equals(stackLast)
                                || hasHigherPrecedence(element, stackLast)) {
                            break;
                        }
                        postfix.add(operatorStack.pollLast());
                    }
                }
                operatorStack.addLast(element);
            } else if ("(".equals(element)) {
                operatorStack.addLast(element);
            } else if (")".equals(element)) {
                while (true) {
                    String stackLast = operatorStack.pollLast();
                    if (stackLast == null) {
                        // Parentheses are not balanced.
                        throw new IllegalArgumentException();
                    } else if ("(".equals(stackLast)) {
                        break;
                    }
                    postfix.add(stackLast);
                }
            } else {
                postfix.add(element);
            }
        }

        while (!operatorStack.isEmpty()) {
            String stackLast = operatorStack.pollLast();
            if ("(".equals(stackLast)) {
                // Parentheses are not balanced.
                throw new IllegalArgumentException();
            }
            postfix.add(stackLast);
        }

        return postfix;
    }

    private static boolean hasHigherPrecedence(String a, String b) {
        return operatorPrecedence.get(a) > operatorPrecedence.get(b);
    }
}
