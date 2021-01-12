import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile(String.format("\\b[a-zA-Z]{%s}\\b", size));
        System.out.println(pattern.matcher(line).find() ? "YES" : "NO");
    }
}