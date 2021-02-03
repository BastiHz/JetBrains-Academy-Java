package animals;

import java.util.Map;
import java.util.Scanner;
import java.util.Random;
import java.util.ResourceBundle;


class TextHelper {

    static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final ResourceBundle bundle = ResourceBundle.getBundle("TextResource");
    @SuppressWarnings("unchecked")
    static final Map<String, String> FACT_NEGATION = (Map<String, String>) getObject("itCanHasIs.negate");
    @SuppressWarnings("unchecked")
    static final Map<String, String> FACT_QUESTION = (Map<String, String>) getObject("itCanHasIs.question");

    static String localizeFilename(String basename, String extension) {
        String locale = bundle.getLocale().toString();
        if (!locale.isEmpty()) {
            locale = "_" + locale;
        }
        return basename + locale + extension;
    }

    static String nextLine() {
        return scanner.nextLine().strip().toLowerCase();
    }

    static String capitalizeFirst(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    static void print(String key) {
        System.out.print(getString(key));
    }

    static void println(String key) {
        System.out.println(getString(key));
    }

    static void printf(String key, Object arg) {
        System.out.printf(getString(key), arg);
    }

    static void printf(String key, Object[] args) {
        System.out.printf(getString(key), args);
    }

    static String getString(String key) {
        Object obj = bundle.getObject(key);
        if (obj.getClass().isArray()) {
            String[] arr = (String[]) obj;
            return arr[random.nextInt(arr.length)];
        }
        return (String) obj;
    }

    static Object getObject(String key) {
        return bundle.getObject(key);
    }
}
