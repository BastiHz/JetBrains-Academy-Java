import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int d = scanner.nextInt();
        Set<String> knownWords = new HashSet<>();
        for (int i = 0; i < d; i++) {
            knownWords.add(scanner.next().toLowerCase());
        }
        int l = scanner.nextInt();
        scanner.nextLine();
        Set<String> erroneousWords = new HashSet<>();
        for (int i = 0; i < l; i++) {
            List<String> line = Arrays.asList(scanner.nextLine().toLowerCase().split(" "));
            erroneousWords.addAll(line);
        }
        erroneousWords.removeAll(knownWords);
        for (String word : erroneousWords) {
            System.out.println(word);
        }
    }
}