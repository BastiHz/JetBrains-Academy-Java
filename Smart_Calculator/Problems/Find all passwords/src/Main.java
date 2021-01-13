import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        // I would use negative lookbehind but repetition is not allowed there
        // and I need it for the variable number of whitespace characters.
        Pattern password = Pattern.compile("password[ :]*", Pattern.CASE_INSENSITIVE);
        Pattern actualPassword = Pattern.compile("[^\\W_]+");  // = "\\w" without "_"
        Matcher matcher = password.matcher(text);
        if (matcher.find()) {
            do {
                Matcher actualPasswordMatcher = actualPassword.matcher(text.substring(matcher.end()));
                if (actualPasswordMatcher.find()) {
                    System.out.println(actualPasswordMatcher.group());
                }
            } while (matcher.find());
        } else {
            System.out.println("No passwords found.");
        }
    }
}