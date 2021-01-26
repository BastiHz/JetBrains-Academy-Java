import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int K = scanner.nextInt();
        final int N = scanner.nextInt();
        final double M = scanner.nextDouble();
        Random random = new Random();
        long seed = K - 1;
        boolean found = false;
        while (!found) {
            found = true;
            seed++;
            random.setSeed(seed);
            for (int i = 0; i < N; i++) {
                if (random.nextGaussian() > M) {
                    found = false;
                    break;
                }
            }
        }
        System.out.println(seed);
    }
}