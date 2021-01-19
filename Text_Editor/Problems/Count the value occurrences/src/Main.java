import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        int count1 = 0;
        for (int x : list1) {
            if (x == elem) {
                count1++;
            }
        }
        int count2 = 0;
        for (int x : list2) {
            if (x == elem) {
                count2++;
            }
        }
        return count1 == count2;
    }
}