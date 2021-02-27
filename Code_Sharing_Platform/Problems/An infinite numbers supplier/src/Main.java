import java.util.function.*;

class FunctionUtils {

    public static Supplier<Integer> getInfiniteRange() {
        class Foo implements Supplier<Integer> {
            private Integer i = 0;

            @Override
            public Integer get() {
                return i++;
            }
        }
        return new Foo();
    }
}
