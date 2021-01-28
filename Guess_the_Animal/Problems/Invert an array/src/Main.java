// do not remove imports
import java.util.*;
import java.util.function.Function;

class ArrayUtils {
    static <T> T[] invert(T[] array) {
        Collections.reverse(Arrays.asList(array));
        return array;
    }
}