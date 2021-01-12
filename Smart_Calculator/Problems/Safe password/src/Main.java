import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        if (password.matches(".*[A-Z].*")         // at least one uppercase letter
                && password.matches(".*[a-z].*")  // at least one lowercase letter
                && password.matches(".*\\d.*")    // at least one digit
                && password.matches(".{12,}")) {  // 12 or more symbols
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}