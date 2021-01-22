import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = Arrays.asList(scanner.nextLine().split(" "));
        int nSwaps = scanner.nextInt();
        for (int i = 0; i < nSwaps; i++) {
            int j = scanner.nextInt();
            int k = scanner.nextInt();
            Collections.swap(list, j, k);
        }
        System.out.println(String.join(" ", list));
    }
}