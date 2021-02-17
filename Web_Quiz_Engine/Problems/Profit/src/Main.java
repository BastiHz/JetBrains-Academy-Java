import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double m = Double.parseDouble(scanner.next());
        double p = Double.parseDouble(scanner.next()) / 100 + 1;
        int k = scanner.nextInt();
        int years = 0;
        while (m < k) {
            years++;
            m *= p;
        }
        System.out.println(years);
    }
}
