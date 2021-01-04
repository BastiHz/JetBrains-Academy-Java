import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbersInput = scanner.nextLine().split(" ");
        ArrayList<Integer> numbers = new ArrayList<>(numbersInput.length);
        for (String n : numbersInput) {
            numbers.add(Integer.parseInt(n));
        }
        int x = scanner.nextInt();
        String result = "";
        int low = x;
        int high = x;
        while (!numbers.contains(low) && !numbers.contains(high)) {
            low--;
            high++;
        }
        if (numbers.contains(low)) {
            for (int i = 0; i < Collections.frequency(numbers, low); i++) {
                result += low + " ";
            }
        }
        if (numbers.contains(high) && low != high) {
            for (int i = 0; i < Collections.frequency(numbers, high); i++) {
                result += high + " ";
            }
        }
        System.out.println(result);
    }
}