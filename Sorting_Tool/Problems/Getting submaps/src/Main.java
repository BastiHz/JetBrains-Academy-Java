import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = scanner.nextInt();
        int max = scanner.nextInt() + 1;  // +1 because inclusive
        int n = scanner.nextInt();
        SortedMap<Integer, String> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            map.put(scanner.nextInt(), scanner.next());
        }
        for (Map.Entry<Integer, String> entry : map.subMap(min, max).entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
