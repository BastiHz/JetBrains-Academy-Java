import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        BigInteger result = BigInteger.valueOf(n);
        BigInteger current = BigInteger.valueOf(n - 2);
        while (current.compareTo(BigInteger.ZERO) > 0) {
            result = result.multiply(current);
            current = current.subtract(BigInteger.TWO);
        }
        return result;
    }
}
