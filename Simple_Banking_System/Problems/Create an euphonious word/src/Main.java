import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'y');
        Scanner scanner = new Scanner(System.in);
        char[] word = scanner.nextLine().toCharArray();
        int vowelCounter = 0;
        int consonantCounter = 0;
        int result = 0;
        for (char c : word) {
            if (vowels.contains(c)) {
                consonantCounter = 0;
                vowelCounter++;
                if (vowelCounter == 3) {
                    vowelCounter = 1;
                    result++;
                }
            } else {
                vowelCounter = 0;
                consonantCounter++;
                if (consonantCounter == 3) {
                    consonantCounter = 1;
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}