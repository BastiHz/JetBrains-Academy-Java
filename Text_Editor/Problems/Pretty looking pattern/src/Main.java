import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] array = new char[4][4];
        for (int i = 0; i < 4; i++) {
            array[i] = scanner.nextLine().toCharArray();
        }
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                char c = array[y][x];
                if (c == array[y][x + 1]
                        && c == array[y + 1][x]
                        && c == array[y + 1][x + 1]) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}
