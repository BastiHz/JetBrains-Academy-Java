import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstList = new ArrayList<>();
        for (String s : scanner.nextLine().split(" ")) {
            firstList.add(Integer.parseInt(s));
        }
        List<Integer> secondList = new ArrayList<>();
        for (String s : scanner.nextLine().split(" ")) {
            secondList.add(Integer.parseInt(s));
        }
        System.out.print(Collections.indexOfSubList(firstList, secondList) + " ");
        System.out.println(Collections.lastIndexOfSubList(firstList, secondList));
    }
}