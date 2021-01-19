import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        List<String> result = new ArrayList<>();
        for (int i = 1; i < input.length; i += 2) {
            result.add(0, input[i]);
        }
        for (String x : result) {
            System.out.print(x + " ");
        }
    }
}