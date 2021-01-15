import java.util.*;

class Main {
    public static void main(String[] args) {
        Map<String, String> pairedBrackets = Map.of("(", ")", "[", "]", "{", "}");
        Deque<String> stack = new ArrayDeque<>();
        boolean isPaired = true;
        Scanner scanner = new Scanner(System.in);
        String[] brackets = scanner.nextLine().trim().split("");
        for (String b : brackets) {
            if (pairedBrackets.containsKey(b)) {
                stack.addLast(b);
            } else {
                String openingBracket = stack.pollLast();
                if (openingBracket == null || !b.equals(pairedBrackets.get(openingBracket))) {
                    isPaired = false;
                    break;
                }
            }
        }
        System.out.println(isPaired && stack.isEmpty());
    }
}