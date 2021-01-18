class Predicate {

    @FunctionalInterface
    public interface TernaryIntPredicate {
        boolean test(int a, int b, int c);
    }

    public static final TernaryIntPredicate allValuesAreDifferentPredicate =
        (a, b, c) -> a != b && a != c && b != c;

}

// Here is how to call this:
/*
class Main {
    public static void main(String[] args) {
        System.out.println(Predicate.allValuesAreDifferentPredicate.test(1, 2, 3));
        System.out.println(Predicate.allValuesAreDifferentPredicate.test(1, 2, 1));
    }
}
*/
