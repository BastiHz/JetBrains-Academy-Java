// do not remove imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

class ArrayUtils {
    static <T> T getFirst(T[] array) {
        return array.length == 0 ? null : array[0];
    }
}