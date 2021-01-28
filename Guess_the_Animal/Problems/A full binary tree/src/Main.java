import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n % 2 != 0) {
            System.out.println("no");
            return;
        }
        Map<Integer, Set<Integer>> nChildren = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            Set<Integer> children;
            if (nChildren.containsKey(a)) {
                children = nChildren.get(a);
            } else {
                children = new HashSet<>();
            }
            children.add(b);
            nChildren.put(a, children);
        }
        for (Set<Integer> children : nChildren.values()) {
            int size = children.size();
            if (!(size == 0 || size == 2)) {
                System.out.println("no");
                return;
            }
        }
        System.out.println("yes");
    }
}