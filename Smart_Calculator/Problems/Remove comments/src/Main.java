import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String codeWithComments = scanner.nextLine();
        String pattern = "/\\*.*?\\*/|\\s*//.*";
        System.out.println(codeWithComments.replaceAll(pattern, ""));
    }
}