package main;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public class BinPow {

    public static BigInteger binPow(BigInteger a, BigInteger b, BigInteger mod) { //a^b
        if (b.equals(ZERO))
            return ONE;
        if (b.equals(ONE))
            return a.mod(mod);

        if (b.mod(TWO).equals(ONE)) {
            return a.multiply(binPow(a, b.subtract(ONE), mod)).mod(mod);
        }

        return binPow(a, b.divide(TWO), mod).pow(2).mod(mod);

    }

    public static void main(String[] args) {
        System.out.println("binPow 99^99= " + binPow(BigInteger.valueOf(99), BigInteger.valueOf(99), BigInteger.valueOf(100)).toString());
        System.out.println("binPow 2^10=" + binPow(TWO, TEN, TWO).toString());
    }

}
