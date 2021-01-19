import java.util.*;

public class Main {

    public static void processIterator(String[] array) {
        List<String> list = new ArrayList<>(Arrays.asList(array));
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            String elem = iterator.next();
            if (elem.charAt(0) == 'J') {
                iterator.set(elem.substring(1));
            } else {
                iterator.remove();
            }
        }
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        processIterator(scanner.nextLine().split(" "));
    }
}