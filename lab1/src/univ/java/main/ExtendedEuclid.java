package main;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ExtendedEuclid {
    public static BigInteger[] extendedAlgorithm(BigInteger a, BigInteger b) {
        BigInteger[] ans = new BigInteger[3];

        if (b.intValue() == 0) {
            ans[0] = a;
            ans[1] = BigInteger.valueOf(1);
            ans[2] = BigInteger.valueOf(0);
        } else {
            BigInteger q = a.divide(b);
            ans = extendedAlgorithm(b, a.remainder(b));

            BigInteger temp = ans[1].subtract(ans[2].multiply(q));
            ans[1] = ans[2];
            ans[2] = temp;
        }
        return ans;
    }

    public static void main(String[] args) {
        BigInteger a = new BigDecimal("23").toBigInteger();
        BigInteger b = new BigDecimal("17").toBigInteger();

        BigInteger[] ans = extendedAlgorithm(a, b);
        System.out.println("gcd(" + a + ", " + b + ") = " + ans[0]);

        System.out.println(ans[0] + " = " + ans[1].toString() + "*" + a.toString() + " + " + ans[2].toString() + "*" + b.toString() + "");
    }
}
